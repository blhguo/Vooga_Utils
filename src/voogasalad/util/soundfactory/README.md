# Sound Factory
This is team IfTrueReturnTrue's Sound Factory utility, which provides easy to integrate sound functionality for JavaFX Applications

An example usage of this utility is shown in `SoundTestDriver.java`

## Version 1.1

##### New in version 1.1
Since version 1.0, the constructor of ITRTSoundFactory has been deprecated and replaced with one which takes a properties file path as a parameter.  This allows for use of your own properties file, to which you can add and remove sounds at will without affecting the utility.

##### Known Bugs
* Sound will not play on macOS HighSierra, version 10.13.*

##### Upcoming Releases
* Reimplement sound playing with technology that works on all modern operating systems.  This will not affect interface or usage
* Interface with Spotify to stream any song
* Likely will deprecate specific button methods and replace with a more generic method that takes a lambda parameter

##### Feedback/Bug Reports
Please report any bugs or feedback to benjamin.auriemma@duke.edu