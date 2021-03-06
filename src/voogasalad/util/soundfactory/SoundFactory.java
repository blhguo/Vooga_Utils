package voogasalad.util.soundfactory;

import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.beans.property.DoubleProperty;

/**
 * This serves as the API for our Sound Factory utility
 * Currently, it provides basic functionality such as playing mp3 files, setting volume, and generating basic buttons
 * @author benauriemma
 *
 */
public interface SoundFactory {
    
    /**
     * This method can be called to play a specified sound once
     * The sound will be played to completion and cannot be paused
     * The volume of the sound effect will be the volume that the SoundFactory was set to at the time of this method call
     * 
     * @param soundEffectName is the name of the sound, which should map to a file path in the properties file
     * 			      specified within the implementation of this interface
     * @throws FileNotFoundException if soundEffectName is not found in the properties file
     */
    public void playSoundEffect(String soundEffectName) throws FileNotFoundException;
    
    /**
     * This method can be called to set a song as background music
     * The background song of SoundFactory is affected by the following methods:
     * playBackgroundMusic()
     * pauseBackgroundMusic()
     * muteBackgroundMusic()
     * setVolume(Integer)
     * 
     * @param musicName is the name of the sound, which should map to a file path in the properties file
     * 			specified within the implementation of this interface
     * @throws FileNotFoundException if musicName is not found in the properties file
     */
    public void setBackgroundMusic(String musicName) throws FileNotFoundException;
    
    /**
     * This method can be called to play the song set as background music
     */
    public void playBackgroundMusic();
    
    /**
     * This method can be called to pause the song set as background music
     */
    public void pauseBackgroundMusic();
    
    /**
     * This method can be used to set the volume of background music being played currently and all sounds played in the future
     * Note, this only affects sounds played through this instance of SoundFactory
     * As stated in the README, it is recommended to create only one instance of SoundFactory for the above reason
     * @param percentVolume is an integer between 0 and 100. 0 is muted, and 100 is full volume
     */
    public void setVolume(Integer percentVolume);
    
    /**
     * This method sets volume to 0
     * Note, this only affects sounds played through this instance of SoundFactory
     * As stated in the README, it is recommended to create only one instance of SoundFactory for the above reason
     */
    public void mute();
    
    /**
     * This method is used to generate a button which plays a SoundFactory's background music when clicked
     * The setBackgroundMusic method must be invoked on SoundFactory before this button is used
     * @return the PlaySoundButton which can be added to a screen and clicked
     */
    public Button createPlayBackgroundMusicButton();
    
    /**
     * This method is used to generate a button which pauses the SoundFactory's background music when clicked
     * @return the PauseSoundButton which can be added to a screen and clicked
     */
    public Button createPauseBackgroundMusicButton();
    
    /**
     * This method is used to create a volume slider which can be used to set the volume of all sounds played from this SoundFactory
     * @return the volume slider which can be added to the screen and interacted with
     */
    public Slider createVolumeSlider();
    
    /**
     * This method is used to create a button which, when clicked, mutes all sounds played from this instance of SoundFactory
     * @return the mute button which can be added to a screen and clicked
     */
    public Button createMuteButton();
    
    /**
     * This method is used to be called by VolumeSlider objects
     * It allows a VolumeSlider to listen to the volume property and adjust the slider position is volume is change elsewhere
     * @return a DoubleProperty representing the volume which can be listened to
     */
    public DoubleProperty getVolume();
    
}
