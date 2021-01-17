import java.io.*;
import java.util.*;

public class books {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[] books = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
		    books[i] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.toString(books));
		int curTime = 0;
		int p1 = 0;
		int p2 = -1;
		int maxBooks = 0;
		while (p2 != N - 1) {
//		    System.out.println(p1 + " " + p2 + " " + curTime);
		    if (curTime < T) {
		        p2++;
		        curTime += books[p2];
		        if (curTime <= T) {
		            maxBooks = Math.max(maxBooks, p2 - p1 + 1);
		        }
		        
		    } else if (curTime >= T) {
		        p1++;
		        curTime -= books[p1-1];
		    }
		}
		System.out.println(maxBooks);
		
	}
}