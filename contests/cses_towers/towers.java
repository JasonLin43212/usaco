import java.io.*;
import java.util.*;

class multiset {
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
        if (map.get(n) <= 0) {
            map.remove(n);
        }
    }
    
    public String toString() {
        return map.toString();
    }
}

public class towers {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		multiset multi = new multiset();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
		    int num = Integer.parseInt(st.nextToken());
		    Integer higher = multi.map.higherKey(num);
		    if (higher == null) {
		        multi.add(num);
		    } else {
		        multi.add(num);
		        multi.remove(higher);
		    }
//		    System.out.println(multi);
		}
		
		int numTowers = 0;
		for (int k : multi.map.keySet()) {
		    numTowers += multi.map.get(k);
		}
		System.out.println(numTowers);
	}
}