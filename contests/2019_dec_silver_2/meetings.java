import java.io.*;
import java.util.*;

public class meetings {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("meetings.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		pw.println("solution");
		pw.close();
	}
}