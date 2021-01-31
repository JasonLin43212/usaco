import java.io.*;
import java.util.*;

public class concerttickets {
    
    static class multiset {
        public TreeMap<Integer, Integer> map;
        
        public multiset() {
            this.map = new TreeMap<>();
        }
        
        public void add(int n) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }
        
        public void remove(int n) {
            map.put(n, map.get(n) - 1);
            if (map.get(n) == 0) {
                map.remove(n);
            }
        }
    }
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		multiset set = new multiset();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
		    set.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(System.out);
		for (int i = 0; i < M; i++) {
		    int priceMax = Integer.parseInt(st.nextToken());
//		    System.out.println(priceMax);
//		    System.out.println(set.map);
		    Integer ticket = set.map.floorKey(priceMax);
		    if (ticket == null) {
		        pw.println("-1");
		    } else {
		        pw.println(ticket);
		        set.remove(ticket);
		    }
		}
		
		pw.close();
	}
}