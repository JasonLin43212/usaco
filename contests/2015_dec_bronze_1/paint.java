import java.io.*;
import java.util.*;

public class paint {
    
	public static void main (String [] args) throws IOException {
		String fileName = "paint";
		BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
//		System.out.println(a + " " + b + " " + c + " " + d);
		
		int out = 0;
		if (c >= b || a >= d) {
		    out = (b-a) + (d-c);
		} else if (b >= c && b <= d) {
		    if (a >= c) {
		        out = d - c;
		    } else {
		        out = (b-a) + (d - c) -(b -c);
		    }
		} else if (d >= a && d < b) {
		    if (c >= a) {
		        out= b -a;
		    } else {
		        out = (b-a) + (d-c) - (d-a);
		    }
		}
		
		
		pw.println(out);
		pw.close();
	}
}