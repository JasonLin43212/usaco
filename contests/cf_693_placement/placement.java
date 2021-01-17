import java.io.*;
import java.util.*;

class rect implements Comparable<rect>{
    public long w;
    public long h;
    
    public rect(int w, int h) {
        this.w = (long)w;
        this.h = (long)h;
    }
    
    public boolean canCover(rect other) {
        return (this.w > other.w && this.h > other.h) ||
               (this.w > other.h && this.h > other.w);
    }
    
    public int compareTo(rect other) {
        return Long.compare(other.w*other.h, this.w * this.h);
    }
    
    public String toString() {
        return "w: " +  w + " h: " + h;
    }
}

public class placement {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int q = 0; q < t; q++) {
		    int n = Integer.parseInt(br.readLine());
		    rect[] rects = new rect[n];
		    for (int i = 0; i < n; i++) {
		        StringTokenizer st = new StringTokenizer(br.readLine());
		        int h = Integer.parseInt(st.nextToken());
		        int w = Integer.parseInt(st.nextToken());
		        rects[i] = new rect(w, h);
		    }
	        Arrays.sort(rects);
	        System.out.println(Arrays.toString(rects));
	        
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < n; i) {
	            int covers = -1;
	            for (int j = n-1; j > i; j--) {
	                if (rects[i].canCover(rects[j])) {
	                    covers = j+1;
	                    break;
	                }
	            }
	            sb.append(covers + "");
	            if (i != n-1) {
	                sb.append(" ");
	            }
	        }
	        System.out.println(sb.toString());
		}
	}
}