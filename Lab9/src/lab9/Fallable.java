package lab9;


/**
 * Interface for fallable objects such as Coins and Bombs.
 * @author Takeshi Hashimoto
 *
 */
public interface Fallable {
	double getSpeed();
	double getLayoutY();
	void setLayoutY(double l);
}
