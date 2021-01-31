import java.io.*;
import java.util.*;

public class restuarant {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] val = new int[2*N];
		int[] num = new int[2*N];
		Integer[] pos = new Integer[2*N];
		for (int i = 0; i < N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    num[2*i] = Integer.parseInt(st.nextToken());
		    num[2*i + 1] = Integer.parseInt(st.nextToken());
		    val[2*i] = 1;
		    val[2*i + 1] = -1;
		    pos[2*i] = 2*i;
		    pos[2*i + 1] = 2*i + 1;
		}
//		System.out.println("POS BEFORE: " + Arrays.toString(pos));
		Arrays.sort(pos, (a, b) -> num[a] - num[b]);
		int[] newVal = new int[2*N];
		for (int i = 0; i < 2*N; i++) {
		    newVal[i] = val[pos[i]];
		}
//		System.out.println(Arrays.toString(num));
//		System.out.println(Arrays.toString(val));
//		System.out.println(Arrays.toString(pos));
		
//		System.out.println(Arrays.toString(newVal));
		
		int[] p = new int[2*N+1];
		int maxPeople = 0;
		for (int i = 1; i < 2*N + 1; i++) {
		    p[i] = p[i-1] + newVal[i-1];
		    maxPeople = Math.max(maxPeople, p[i]);
		}
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(maxPeople);
		pw.close();
	}
}