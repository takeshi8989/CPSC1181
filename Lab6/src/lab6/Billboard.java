/**
 * 
 */
package lab6;

/**
 * @author takes
 * This class implements ASCIIDrawable interface.
 * It has message and its setter and getter.
 *
 */
public class Billboard implements ASCIIDrawable {
	private String message;
	/**
	 * Constructor with message
	 * @param m
	 */
	public Billboard(String m) {
		this.message = m;
	}
	
	/**
	 * getter for message
	 * @return message
	 */
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * setter for message
	 * @param m
	 */
	public void setMessage(String m) {
		this.message = m;
	}

	/**
	 * This method returns the message surrounded by a rectangle
	 * @return shape
	 */
	@Override
	public String drawAsASCII() {
		String str = "";
		for (int i = 0; i < this.message.length()+4; i++) {
			str += "#";
		}
		str += "\n";
		for (int i = 0; i < this.message.length()+4; i++) {
			if(i == 0 || i == this.message.length()+3)
				str += "#";
			else
				str += " ";
		}
		str += "\n# " + this.message + " #\n";
		for (int i = 0; i < this.message.length()+4; i++) {
			if(i == 0 || i == this.message.length()+3)
				str += "#";
			else
				str += " ";
		}
		str += "\n";
		for (int i = 0; i < this.message.length()+4; i++) {
			str += "#";
		}
		return str;
	}
	
	/**
	 * toString method returning the message
	 * @return message
	 */
	public String toString() {
		return "Message: " + this.getMessage();
	}
}
