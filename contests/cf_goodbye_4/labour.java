import java.io.*;
import java.util.*;

public class labour {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("labour.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("labour.out")));
		pw.println("solution");
		pw.close();
	}
}