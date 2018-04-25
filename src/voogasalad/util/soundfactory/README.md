# Documentation

## `public class ITRTSoundFactory implements SoundFactory`

This class represents IfTrueReturnTrue's implementation of the SoundFactory interface Sound is implemented using javafx.scene.media.Media and javafx.scene.media.MediaPlayer This implementation is basic. It does not style any UI components

This class can be extended and its methods overridden if additional functionality is desired For example:

 * **Author:** benauriemma

     <p>

## `public ITRTSoundFactory()`

This public constructor initializes an ITRTSoundFactory Its volume is by default set to full volume

## `@Override public void playSoundEffect(String soundName) throws FileNotFoundException`

Implements method of the same signature in SoundFactory. The usage of this method is described in interface documentation. Implementation details described below:

Sound is played by invoking the play() method on a MediaPlayer

## `@Override public void setBackgroundMusic(String musicName) throws FileNotFoundException`

Implements method of the same signature in SoundFactory. The usage of this method is described in interface documentation. Implementation details described below:

Song is set by creating a new Media object and passing it to the constructor of a MediaPlayer

## `@Override public void playBackgroundMusic()`

Implements method of the same signature in SoundFactory. The usage of this method is described in interface documentation. Implementation details described below:

Song is played by invoking the play() method on a MediaPlayer

## `@Override public void pauseBackgroundMusic()`

Implements method of the same signature in SoundFactory. The usage of this method is described in interface documentation. Implementation details described below:

Sound is paused by invoking the pause() method on a MediaPlayer

## `@Override public void setVolume(Integer percentVolume)`

Implements method of the same signature in SoundFactory. The usage of this method is described in interface documentation. Implementation details described below:

Volume is set by invoking the setVolume() method on a MediaPlayer

## `@Override public void mute()`

Implements method of the same signature in SoundFactory. The usage of this method is described in interface documentation. Implementation details described below:

Volume is muted by invoking the setVolume() method on a MediaPlayer with an argument of 0.0

## `@Override public Button createPlayBackgroundMusicButton()`

Implements method of the same signature in SoundFactory. The usage of this method is described in interface documentation. Implementation details described below:

The button return is of type PlayBackgroundMusicButton

## `@Override public Button createPauseBackgroundMusicButton()`

Implements method of the same signature in SoundFactory. The usage of this method is described in interface documentation. Implementation details described below:

The button return is of type PauseBackgroundMusicButton

## `@Override public Slider createVolumeSlider()`

Implements method of the same signature in SoundFactory. The usage of this method is described in interface documentation. Implementation details described below:

The slider return is of type VolumeSlider

## `@Override public Button createMuteButton()`

Implements method of the same signature in SoundFactory. The usage of this method is described in interface documentation. Implementation details described below:

The button return is of type MuteButton