package stringManipulation;

import java.nio.file.*;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;
import java.util.Scanner;

public class RandomRead {// this is stolen from the book, wanted to see if I
							// could randomly read a word list of varying
							// length, but I can only set the position
							// accurately if I know the length of the lines
	public static void main(String[] args) {
		Scanner keyBoard = new Scanner(System.in);
		Path file = Paths.get("F:\\Zac Muerle Backup\\Java\\Game Zone\\src\\stringManipulation\\phrases.txt");
		String s = "000, ,00.00" + System.getProperty("line.separator");
		final int RECSIZE = s.length();
		byte[] data = s.getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(data);
		FileChannel fc = null;
		String idString;
		int id;
		final String QUIT = "999";
		try {
			fc = (FileChannel) Files.newByteChannel(file, READ, WRITE);
			System.out.print("Enter employee ID number or " + QUIT
					+ " to quit >> ");
			idString = keyBoard.nextLine();
			while (!idString.equals(QUIT)) {
				id = Integer.parseInt(idString);
				buffer = ByteBuffer.wrap(data);
				fc.position(id * RECSIZE);
				fc.read(buffer);
				s = new String(data);
				System.out.println("ID #" + id + " " + s);
				System.out.print("Enter employee ID number or " + QUIT
						+ " to quit >> ");
				idString = keyBoard.nextLine();
			}
			fc.close();
			keyBoard.close();
		} catch (Exception e) {
			System.out.println("Error message: " + e);
		}
	}
}
