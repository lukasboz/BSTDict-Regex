/**
 * @author Lukas Bozinov
 * Description of Class: A class that holds a Key for each element of the inputfile
 */

public class Key {

	//declare private instance variables
	private String label;
	private int type;

	/**
	 * Constructor for Key class
	 * 
	 * @param theLabel
	 * @param theType
	 */
	public Key(String theLabel, int theType) {
		label = theLabel; //set label as label
		type = theType; //set type as type

	}

	//get method for label
	public String getLabel() {
		return label;
	}

	//get method for type
	public int getType() {
		return type;
	}

	// returns true if str1 precedes str2 lexicographically
	public int compareTo(Key k) {
		String label2 = k.getLabel(); //get the label of k and store it in label2
		int type2 = k.getType(); //get the type of k and store it in type2

		//set corresponding return values depending on what's return by the built-in java compareTo string
		if (label.compareTo(label2) == 0) {
			if (type == type2)
				return 0;
			else if (type < type2)
				return -1;
			else
				return 1;
		} else if (label.compareTo(label2) < 0)
			return -1;
		else
			return 1;
	}

}