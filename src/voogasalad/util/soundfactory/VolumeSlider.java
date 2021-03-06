package voogasalad.util.soundfactory;

import javafx.scene.control.Slider;

/**
 * This class represents and place-able, slide-able slider
 * When adjusted, it will set volume in the instance of SoundFactory it was created from
 * @author benauriemma
 *
 */
public class VolumeSlider extends Slider {

    private static final Integer MIN_VOLUME = 0;
    private static final Integer MAX_VOLUME = 100;
    private static final Integer DEFAULT_VOLUME = 110;
    
    private SoundFactory mySoundFactory;

    /**
     * This constructor is called from SoundFactory.createVolumeSlider()
     * @param soundFactory is the soundFactory which this button will affect the volume of
     */
    public VolumeSlider(SoundFactory soundFactory) {
	super(MIN_VOLUME, MAX_VOLUME, DEFAULT_VOLUME);
	mySoundFactory = soundFactory;
	this.valueProperty().addListener(e -> setVolume());
	this.mySoundFactory.getVolume().addListener(e -> setPosition());
    }
    
    /**
     * Makes a direct call to its SoundFactory's setVolume(Integer) method
     */
    private void setVolume() {
	mySoundFactory.setVolume((int) this.getValue());
    }
    
    /**
     * This method sets the position of the slider to the current volume of the SoundFactory it was created from
     */
    private void setPosition() {
	this.adjustValue(mySoundFactory.getVolume().get()*100);
    }
    
}
