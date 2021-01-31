import java.io.*;
import java.util.*;

public class stick {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("stick.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stick.out")));
		pw.println("solution");
		pw.close();
	}
}