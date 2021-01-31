import java.io.*;
import java.util.*;

public class phone {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		PrintWriter pw = new PrintWriter(System.out);
		for (int x = 0; x < t; x++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int n = Integer.parseInt(st.nextToken());
		    int m = Integer.parseInt(st.nextToken());
		    int[] a = new int[n];
		    
		    st = new StringTokenizer(br.readLine());
		    for (int i = 0; i < n; i++) {
		        a[i] = Integer.parseInt(st.nextToken());
		    }
		    st = new StringTokenizer(br.readLine());
		    List<Integer> one = new ArrayList<>();
		    List<Integer> two = new ArrayList<>();
		    for (int i = 0; i < n; i++) {
		        int imp = Integer.parseInt(st.nextToken());
		        if (imp == 1) {
		            one.add(a[i]);
		        } else {
		            two.add(a[i]);
		        }
		    }
            one.sort((p, q) -> q - p);
            two.sort((p, q) -> q - p);
//            System.out.println(one + " " + two);
            
            int[] prefix1 = new int[one.size() + 1];
            int[] prefix2 = new int[two.size() + 1];
            for (int i = 1; i < prefix1.length; i++) {
                prefix1[i] = prefix1[i-1] + one.get(i - 1);
            }
            for (int i = 1; i < prefix2.length; i++) {
                prefix2[i] = prefix2[i-1] + two.get(i - 1);
            }
            
            
            int p2 = two.size();
            int minPoints = Integer.MAX_VALUE;
            for (int p1 = 0; p1 < one.size() + 1; p1++) {
                while (p2 >= 0 && prefix1[p1] + prefix2[p2] >= m) {
                    minPoints = Math.min(p1 + (2 * p2), minPoints);
                    p2--;
                }
            }
            pw.println(minPoints == Integer.MAX_VALUE ? -1 : minPoints);
		}
		
		
		
		pw.close();
	}
}