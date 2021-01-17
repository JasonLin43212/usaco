import java.io.*;
import java.util.*;

public class trafficlights {
    
    public static int N;
    public static int X;
    public static TreeSet<Integer> set = new TreeSet<>();
    public static PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		set.add(0);
		set.add(X);
		pq.add(X);
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
		    int light = Integer.parseInt(st.nextToken());
		    int lo = set.lower(light);
		    int hi = set.higher(light);
		    
		    set.add(light);
		    pq.remove(hi - lo);
		    pq.add(light - lo);
		    pq.add(hi - light);
		    System.out.print(pq.peek());
		    
		    if (i < N - 1) {
		        System.out.print(" ");
		    }
		}
		
		System.out.print("\n");
	}
}