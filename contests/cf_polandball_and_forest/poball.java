import java.io.*;
import java.util.*;

public class poball {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("poball.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("poball.out")));
		pw.println("solution");
		pw.close();
	}
}