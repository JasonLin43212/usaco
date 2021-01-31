import java.io.*;
import java.util.*;

public class balls {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(System.out);

		for (int z = 0; z < t; z++) {
		    int n = Integer.parseInt(br.readLine());
		    int maxSame = 0;
		    int num = -1;
		    int curSame = 0;
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    for (int i = 0; i < n; i++) {
		        int a = Integer.parseInt(st.nextToken());
		        if (a == num) {
		            curSame++;
		        } else {
		            maxSame = Math.max(maxSame, curSame);
		            num = a;
		            curSame = 1;
		        }
		    }
		    maxSame = Math.max(maxSame, curSame);
		    pw.println(maxSame);
		}
		pw.close();
	}
}