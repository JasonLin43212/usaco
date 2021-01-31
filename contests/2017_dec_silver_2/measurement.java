import java.io.*;
import java.util.*;

class milkchange implements Comparable<milkchange> {
    public int time;
    public int cow;
    public int change;
    
    public milkchange(int time, int cow, int change) {
        this.time = time;
        this.cow = cow;
        this.change = change;
    }
    
    public int compareTo(milkchange other) {
        return this.time - other.time;
    }
    
    public String toString() {
        return "( time: " + time + ", cow: " + cow + ", change: " + change + ")";  
    }
}

class multiset {
    public TreeMap<Integer, Integer> map;
    
    public multiset(int g, int n) {
        map = new TreeMap<>();
        map.put(g, n);
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
    
    public String toString() {
        return map.toString();
    }
}

public class measurement {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		milkchange[] records = new milkchange[N];
		
		Map<Integer, Integer> milkProd = new HashMap<>();
		for (int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int cow = Integer.parseInt(st.nextToken());
            int delta = Integer.parseInt(st.nextToken());
		    records[i] = new milkchange(time, cow, delta);
		    if (!milkProd.containsKey(cow)) {
		        milkProd.put(cow, G);
		    }
		}
		multiset set = new multiset(G, milkProd.size());
		
		Arrays.sort(records);
//		System.out.println(Arrays.toString(records));
//		System.out.println(set);
//		System.out.println(milkProd);
		
		int numChange = 0;
		int prevMax = 0;
		int prevNum = 0;
		for (int i = 0; i < N; i++) {
		    milkchange curRecord = records[i];
//		    System.out.println(curRecord);
		    
		    int prevMilk = milkProd.get(curRecord.cow);
		    int newMilk = curRecord.change + prevMilk;
		    milkProd.put(curRecord.cow, newMilk);
		    set.remove(prevMilk);
		    set.add(newMilk);
		    int mostMilk = set.map.lastKey();
		    int numCows = set.map.get(mostMilk);
		    
		    if (prevNum != numCows) {
//		        System.out.println("different num cows");
                numChange++;
            } else if (mostMilk != prevMax) {
                // Was at the top but not isn't
		        if ((prevMilk == prevMax && mostMilk > newMilk) ||
		            (prevMilk != prevMax && mostMilk == newMilk)) {
//		            System.out.println("now cow moves away");
		            numChange++;
		        }
		    }
		    prevMax = mostMilk;
            prevNum = numCows;
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
		pw.println(numChange);
		pw.close();
	}
}