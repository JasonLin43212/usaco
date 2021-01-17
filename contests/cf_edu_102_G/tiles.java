import java.io.*;
import java.util.*;

public class tiles {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		int[] b = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
		    a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
		
		System.out.println(Arrays.toString(a) + " " + Arrays.toString(b));
		PrintWriter pw = new PrintWriter(System.out);
		pw.println("solution");
		pw.close();
	}
}