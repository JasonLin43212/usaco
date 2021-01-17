import java.io.*;
import java.util.*;

public class rut {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("rut.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rut.out")));
		pw.println("solution");
		pw.close();
	}
}