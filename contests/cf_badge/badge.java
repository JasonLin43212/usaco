import java.io.*;
import java.util.*;

public class badge {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("badge.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("badge.out")));
		pw.println("solution");
		pw.close();
	}
}