import java.io.*;
import java.util.*;

public class teleport {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("teleport.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int minStart = Math.min(a, b);
		int maxStart = Math.max(a, b);
		int minTele = Math.min(x, y);
		int maxTele = Math.max(x, y);
		
//		System.out.println(a + " " + b + " " + x + " " + y );
		int solution = Math.min(Math.abs(minStart - maxStart),
		        Math.abs(minStart - minTele) + Math.abs(maxStart - maxTele));
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
		pw.println(solution);
		pw.close();
	}
}