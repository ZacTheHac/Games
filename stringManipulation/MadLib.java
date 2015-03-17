package stringManipulation;
import javax.swing.JOptionPane;


public class MadLib {

	public static void main(String[] args) {
		//jack be nimble, jack be quick, jack failed to jump over the candlestick because he had crippling asthma that made him move away from his parents and so he became depressive and aloof while striving for his parents affection but ending up turning to baking sweets as a coping mechanism.
		String name = JOptionPane.showInputDialog("Choose a name");
		String noun = JOptionPane.showInputDialog("Choose a noun");
		String adjective1 = JOptionPane.showInputDialog("Choose an adjective");
		String verb = JOptionPane.showInputDialog("Choose a past-tense verb");
		String adjective2 = JOptionPane.showInputDialog("Choose an adjective"); //input 5 different things to be inserted into the string

		
		JOptionPane.showMessageDialog(null, name + " be " + adjective1 + ",\n" 
			+ name + " be " + adjective2 + ",\n" 
				+ name + " " + verb + " over the " + noun); //build the string.

	}

}
