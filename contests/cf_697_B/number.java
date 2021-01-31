import java.io.*;
import java.util.*;

public class number {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(System.out);
		for (int x = 0; x < t; x++) {
		    int n = Integer.parseInt(br.readLine());
		    int remain = n % 2020;
		    int quot = n / 2020;
		    pw.println(remain <= quot ? "YES" : "NO");
		}
		pw.close();
	}
}