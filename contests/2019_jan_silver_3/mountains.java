import java.io.*;
import java.util.*;

class mountain implements Comparable<mountain>{
    public int x;
    public int y;
    
    public mountain(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int compareTo(mountain other) {
        return this.x - other.x;
    }
    
    // requires this mountain to be higher than the other
    public boolean covers(mountain other) {
        return this.y - other.y >= Math.abs(this.x - other.x);
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

public class mountains {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
		int N = Integer.parseInt(br.readLine());
		mountain[] mountains = new mountain[N];
		
		for (int i = 0; i < N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    mountains[i] = new mountain(Integer.parseInt(st.nextToken()),
		                                Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(mountains, (a, b) -> b.y - a.y);
		
//		System.out.println(Arrays.toString(mountains));
		
		TreeSet<mountain> seen = new TreeSet<>();
		
		for (int i = 0; i < N; i++) {
		    mountain curMount = mountains[i];
		    mountain lowerMountain = seen.lower(curMount);
		    mountain higherMountain = seen.higher(curMount);
		    if (lowerMountain != null) {
		        if (lowerMountain.covers(curMount)) {
		            continue;
		        }
		    }
		    if (higherMountain != null) {
                if (higherMountain.covers(curMount)) {
                    continue;
                }
            }
		    seen.add(curMount);
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
		pw.println(seen.size());
		pw.close();
	}
}