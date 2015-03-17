package others;
import javax.swing.JOptionPane;


public class TwoCharacters {//for ch 3 GZ#2, did it just because I could

	public static void main(String[] args) {
		MyCharacter charOne = new MyCharacter("Beel-Zebub", (short) 100, (short) 200, (short) 250); //I hate that I have to specify these are shorts, not ints by default. I'm tempted to add a new constructor so I don't have to
		MyCharacter charTwo = new MyCharacter("黒井 深", (short) 10, (short) 100, (short) 0);//character cannot run. it would be undignified.
		//re-save as UTF-8 so we don't have to butcher any names around here. really just testing how java handles non-romanji characters, to see if an idea I have for a simple game engine would be viable. it seems to be working fine.
		//MyCharacter charNull = new MyCharacter("", (short) 0, (short) 0, (short) 0); //used for testing the conditional string making.
		
		JOptionPane.showMessageDialog(null,"I made 2 characters: \n" + charOne.toString() + "\nand \n" + charTwo.toString());

	}

}
