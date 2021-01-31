import java.io.*;
import java.util.*;


class cow implements Comparable<cow> {
    public int loc;
    public int weight;
    public int dir;
    
    public cow(int loc, int weight, int dir) {
        this.loc = loc;
        this.weight = weight;
        this.dir = dir;
    }
    
    public int compareTo(cow other) {
        return this.loc - other.loc;
    }
    
    public String toString() {
        return "(x: " + loc + ", w: " + weight + ", dir: " + dir + ")"; 
    }
}

class weight implements Comparable<weight>{
    public int w;
    public int t;
    
    public weight(int w, int t) {
        this.w = w;
        this.t = t;
    }
    
    public int compareTo(weight other) {
        return this.t - other.t;
    }
}

public class meetings {
    
    public static int N;
    public static int L;
    public static cow[] cows;
    
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("meetings.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		cows = new cow[N];
		List<Integer> leftX = new ArrayList<>();
		List<Integer> rightX = new ArrayList<>();
		int totalWeight = 0;
		
		for (int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            totalWeight += w;
		    cows[i] = new cow(x, w, d);
		    if (d == -1) {
		        leftX.add(x);
		    } else {
		        rightX.add(L - x);
		    }
		}
		Arrays.sort(cows);
		leftX.sort((a, b) -> a - b);
		rightX.sort((a, b) -> b - a);
		
		weight[] weights = new weight[N];
		for (int i = 0; i < leftX.size(); i++) {
		    weights[i] = new weight(cows[i].weight, leftX.get(i));
		}
		for (int i = leftX.size(); i < N; i++) {
		    weights[i] = new weight(cows[i].weight, rightX.get(i - leftX.size()));
		}
		
		Arrays.sort(weights);
		
		if (totalWeight % 2 == 0) {
		    totalWeight /= 2;
		} else {
		    totalWeight /= 2;
		    totalWeight++;
		}
		
		int curWeight = 0;
		int T = 0;
		for (weight cur : weights) {
		    curWeight += cur.w;
		    if (curWeight >= totalWeight) {
		        T = cur.t;
		        break;
		    }
		}
		
//		System.out.println(T);
		Deque<cow> forwardCows = new ArrayDeque<>();
		int numMeets = 0;
		for (int i = 0; i < N; i++) {
		    if (cows[i].dir == 1) {
		        forwardCows.addFirst(cows[i]);
		    } else {
		        for (cow aCow : forwardCows) {
		            if (cows[i].loc - aCow.loc <= 2 * T) {
		                numMeets++;
		            } else {
		                break;
		            }
		        }
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		pw.println(numMeets);
		pw.close();
	}
}