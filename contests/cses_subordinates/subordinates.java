import java.io.*;
import java.util.*;

public class subordinates {
    
    public static int N;
    public static List<List<Integer>> subords = new ArrayList<>();
    public static int[] numSubords;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numSubords = new int[N];
		for (int i = 0; i < N; i++) {
		    subords.add(new ArrayList<>());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N; i++) {
		    int boss = Integer.parseInt(st.nextToken()) - 1;
		    subords.get(boss).add(i);
		}
		
//		System.out.println(subords);
		
		countSubs(0);
		
		String out = "";
		for (int i = 0; i < N - 1; i++) {
		    out += numSubords[i] + " ";
		}
		out += numSubords[N-1];
		
		System.out.println(out);
	}
	
	public static int countSubs(int employee) {
	    int numSubs = 0;
	    for (int sub : subords.get(employee)) {
	        numSubs += countSubs(sub) + 1;
	    }
	    numSubords[employee] = numSubs;
	    return numSubs;
	}
	
}