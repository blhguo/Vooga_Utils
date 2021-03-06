package voogasalad.util.soundfactory;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import voogasalad.util.soundfactory.propertiesfiletools.MissingPropertiesException;
import voogasalad.util.soundfactory.propertiesfiletools.PropertiesReader;


/**
 * This class represents IfTrueReturnTrue's implementation of the SoundFactory interface
 * Sound is implemented using javafx.scene.media.Media and javafx.scene.media.MediaPlayer
 * This implementation is basic. It does not style any UI components
 * 
 * This class can be extended and its methods overridden if additional functionality is desired
 * 	For example:
 * 
 * 	@Override
 * 	public MuteButton createMuteButton() {
 * 		MuteButton mb = super();
 * 		mb.setGraphic(someImage);
 * 		return mb;
 * 	}
 * 
 * 
 * @author benauriemma
 *
 */
public class ITRTSoundFactory implements SoundFactory {

    private static final double FULL_VOLUME = 1.0;
    private static final String DEFAULT_PROPERTIES_FILE_PATH = "src/voogasalad/util/soundfactory/resources/soundFiles.properties";

    private String myPropertiesFilePath;
    private MediaPlayer myMediaPlayer;
    private DoubleProperty myVolume;
    private Duration myCurrentMusicPosition;

    /**
     * This method has been deprecated
     * The version to replace it is public ITRTSoundFactory(String propertiesFilePath)
     * This is to reflect the fact that properties files can and should be saved in your own project, rather than in the utility\
     * Then, your sounds can be added to your properties file without affecting the utility
     */
    @Deprecated
    public ITRTSoundFactory() {
	this(DEFAULT_PROPERTIES_FILE_PATH);
    }

    /**
     * This public constructor initializes an ITRTSoundFactory
     * Its volume is by default set to full volume
     * @param propertiesFilePath is the file path to a properties file which maps sound names to mp3 filepaths
     */
    public ITRTSoundFactory(String propertiesFilePath) {
	this.myPropertiesFilePath = propertiesFilePath;
	this.myMediaPlayer = null;
	this.myVolume = new SimpleDoubleProperty(FULL_VOLUME);
	myCurrentMusicPosition = new Duration(0);
    }

    /**
     * Implements method of the same signature in SoundFactory.
     * The usage of this method is described in interface documentation.
     * Implementation details described below:
     * 
     * Sound is played by invoking the play() method on a MediaPlayer
     * 
     */
    @Override
    public void playSoundEffect(String soundName) throws FileNotFoundException {
	String fileName;
	try {
	    fileName = new PropertiesReader().findVal(myPropertiesFilePath, soundName);
	} catch (MissingPropertiesException e) {
	    throw new FileNotFoundException();
	}
	File file = new File(fileName);
	Media sound = new Media(file.toURI().toString());
	MediaPlayer soundPlayer = new MediaPlayer(sound);
	soundPlayer.setVolume(myVolume.get());
	soundPlayer.play();
    }

    /**
     * Implements method of the same signature in SoundFactory.
     * The usage of this method is described in interface documentation.
     * Implementation details described below:
     * 
     * Song is set by creating a new Media object and passing it to the constructor of a MediaPlayer
     * 
     */
    @Override
    public void setBackgroundMusic(String musicName) throws FileNotFoundException {
	String fileName;
	try {
	    fileName = new PropertiesReader().findVal(myPropertiesFilePath, musicName);
	} catch (MissingPropertiesException e) {
	    throw new FileNotFoundException();
	}
	File file = new File(fileName);
	Media sound = new Media(file.toURI().toString());
	this.myMediaPlayer = new MediaPlayer(sound);
    }

    /**
     * Implements method of the same signature in SoundFactory.
     * The usage of this method is described in interface documentation.
     * Implementation details described below:
     * 
     * Song is played by invoking the play() method on a MediaPlayer
     * 
     */
    @Override
    public void playBackgroundMusic() {
	this.myMediaPlayer.setStartTime(myCurrentMusicPosition);
	this.myMediaPlayer.play();
    }

    /**
     * Implements method of the same signature in SoundFactory.
     * The usage of this method is described in interface documentation.
     * Implementation details described below:
     * 
     * Sound is paused by invoking the pause() method on a MediaPlayer
     * 
     */
    @Override
    public void pauseBackgroundMusic() {
	this.myCurrentMusicPosition = this.myMediaPlayer.getCurrentTime();
	this.myMediaPlayer.pause();
    }

    /**
     * Implements method of the same signature in SoundFactory.
     * The usage of this method is described in interface documentation.
     * Implementation details described below:
     * 
     * Volume is set by invoking the setVolume() method on a MediaPlayer
     * 
     */
    @Override
    public void setVolume(Integer percentVolume) {
	this.myVolume.set(percentVolume/100.0); //this hard-coded value exists because the parameter is on a percentage scale, 
	//but is used as a value between 0 and 1. This value should never be changed
	this.myMediaPlayer.setVolume(myVolume.get());
    }

    /**
     * Implements method of the same signature in SoundFactory.
     * The usage of this method is described in interface documentation.
     * Implementation details described below:
     * 
     * Volume is muted by invoking the setVolume() method on a MediaPlayer with an argument of 0.0
     * 
     */
    @Override
    public void mute() {
	this.myVolume.set(0.0);
	this.myMediaPlayer.setVolume(myVolume.get());
    }

    /**
     * Implements method of the same signature in SoundFactory.
     * The usage of this method is described in interface documentation.
     * Implementation details described below:
     * 
     * The button return is of type PlayBackgroundMusicButton
     * 
     */
    @Override
    public Button createPlayBackgroundMusicButton() {
	return new PlayBackgroundMusicButton(this);
    }

    /**
     * Implements method of the same signature in SoundFactory.
     * The usage of this method is described in interface documentation.
     * Implementation details described below:
     * 
     * The button return is of type PauseBackgroundMusicButton
     * 
     */
    @Override
    public Button createPauseBackgroundMusicButton() {
	return new PauseBackgroundMusicButton(this);
    }

    /**
     * Implements method of the same signature in SoundFactory.
     * The usage of this method is described in interface documentation.
     * Implementation details described below:
     * 
     * The slider return is of type VolumeSlider
     * 
     */
    @Override
    public Slider createVolumeSlider() {
	return new VolumeSlider(this);
    }

    /**
     * Implements method of the same signature in SoundFactory.
     * The usage of this method is described in interface documentation.
     * Implementation details described below:
     * 
     * The button return is of type MuteButton
     * 
     */
    @Override
    public Button createMuteButton() {
	return new MuteButton(this);
    }

    /**
     * Implements method of the same signature in SoundFactory.
     * The usage of this method is described in interface documentation.
     * 
     */
    @Override
    public DoubleProperty getVolume() {
	return myVolume;
    }

}
