package voogasalad.util.soundfactory.propertiesfiletools;

public class MissingPropertiesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MissingPropertiesException(String missingPropertiesName) {
		super(missingPropertiesName+" was not found.");
	}

}
