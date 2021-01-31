import java.io.*;
import java.util.*;

public class destruct {
    
    static class multiset {
        public TreeMap<Integer, Integer> map;
        
        public multiset() {
            map = new TreeMap<>();
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
    
    public static int n;
    public static PrintWriter pw = new PrintWriter(System.out);
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int z = 0; z < t; z++) {
		    n = Integer.parseInt(br.readLine());
		    multiset set = new multiset();
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int maxNum = Integer.MIN_VALUE;
		    for (int i = 0; i < 2*n; i++) {
		        int a = Integer.parseInt(st.nextToken());
		        set.add(a);
		        maxNum = Math.max(a, maxNum);
		    }
		    
		    set.remove(maxNum);
		    List<Integer> possible = new ArrayList<>(set.map.keySet());
//		    System.out.println(set.map);
		    for (int key : possible) {
//		        System.out.println(key);
		        set.remove(key);
		        int curX = maxNum;
		        List<Integer> removed = new ArrayList<>();
		        
		        for (int i = 0; i < n - 1; i++) {
		            int nextMax = set.map.lastKey();
		            set.remove(nextMax);
		            int secondRemove = curX - nextMax;
		            if (set.map.containsKey(secondRemove)) {
		                set.remove(secondRemove);
		                curX = nextMax;
		                removed.add(nextMax);
		                removed.add(secondRemove);
		            } else {
		                removed.add(nextMax);
		                break;
		            }
		        }
		        
		        if (set.map.size() == 0) {
		            pw.println("YES");
		            pw.println(maxNum + key);
		            pw.println(maxNum + " " + key);
		            for (int i = 0; i < n - 1; i++) {
		                pw.println(removed.get(2*i) + " " + removed.get(2*i + 1));
		            }
		            break;
		        }
		        
	            for (int y : removed) {
	                set.add(y);
	            }
		        set.add(key);
//		        System.out.println("ADDED BACK: " + set.map);
		    }
		    if (set.map.size() == 0) {
		        continue;
		    }
		    pw.println("NO");
		}
		pw.close();
	}
}