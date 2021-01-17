import java.io.*;
import java.util.*;

public class monkey {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("monkey.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("monkey.out")));
		pw.println("solution");
		pw.close();
	}
}