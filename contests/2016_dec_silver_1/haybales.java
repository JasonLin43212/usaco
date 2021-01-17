import java.io.*;
import java.util.*;

public class haybales {
    
    public static List<Integer> baleLocs= new ArrayList<>();
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
		    baleLocs.add(Integer.parseInt(st.nextToken()));
		}
		baleLocs.sort(null);
//		System.out.println(baleLocs);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
        
		for (int i = 0; i < Q; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    pw.println(getNumHaybales(a, b));
		}
		
		pw.close();
	}
	
	public static int getNumHaybales(int a, int b) {
	    // Special case for interval not having any haybales
	    if (b < baleLocs.get(0) || a > baleLocs.get(baleLocs.size() - 1)) {
	        return 0;
	    }
	    
	    int aIndex = Collections.binarySearch(baleLocs, a);
	    int bIndex = Collections.binarySearch(baleLocs, b);
//	    System.out.println(aIndex + " " + bIndex);
	    // In case it doesn't exist
	    if (aIndex < 0) {
	        aIndex = -1 * (aIndex + 1);
	    }
	    if (bIndex < 0) {
            bIndex = -1 * (bIndex + 1) - 1;
        }
//	    System.out.println(aIndex + " " + bIndex);
	    return bIndex - aIndex + 1;
	}
}