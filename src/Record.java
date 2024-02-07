/**
 * @author Lukas Bozinov
 * Description of Class: A class that holds a Record for each element of the inputfile (uses Key class)
 */

public class Record {
	
	//create private instance variables
	private Key theKey;
	private String data;
	
	/**
	 * The constructor for the Record class
	 * 
	 * @param k
	 * @param theData
	 */
	public Record(Key k, String theData) {
		theKey = k;
		data = theData;
	}

	//gets the key object stored in the record
	public Key getKey() {
		return theKey;
	}
	
	//gets the data item stored in the record
	public String getDataItem() {
		return data;
	}


}