import java.io.*;
import java.util.*;

public class program {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int x = 0; x < t; x++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int n = Integer.parseInt(st.nextToken());
		    int m = Integer.parseInt(st.nextToken());
		    String change = br.readLine();
		    
		}
		
		PrintWriter pw = new PrintWriter(System.out);
		pw.println("solution");
		pw.close();
	}
}