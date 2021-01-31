import java.io.*;
import java.util.*;


class multiset {
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

public class cowdance {
    
    public static int N;
    public static int T_MAX;
    public static int[] cows;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T_MAX = Integer.parseInt(st.nextToken());
		
		cows = new int[N];
		for (int i = 0; i < N; i++) {
		    cows[i] = Integer.parseInt(br.readLine());
		}
		
		int lo = 0;
		int hi = cows.length;
		for (hi++; lo < hi;) {
		    int mid = (lo + hi) / 2;
		    if (f(mid)) {
		        hi = mid;
		    } else {
		        lo = mid + 1;
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		pw.println(lo);
		pw.close();
	}
	
	public static boolean f(int k) {
	    int time = 0;
	    multiset set = new multiset();
	    int numCows = Math.min(k, cows.length);
	    for (int i = 0; i < numCows; i++) {
	        set.add(cows[i]);
	    }
	    while (set.map.size() > 0) {
	        int lowTime = set.map.firstKey();
            set.remove(lowTime);
            time = lowTime;
	        if (numCows != cows.length) {
	            set.add(lowTime + cows[numCows]);
	            numCows++;
	        }
	    }
	    return time <= T_MAX;
	}
}