import java.io.*;
import java.util.*;

class rect implements Comparable<rect> {
    public long x;
    public long y;
    
    public rect(long x, long y) {
        this.x = x;
        this.y = y;
    }
    
    public String toString() {
        return "( x: " + x + ", y: " + y + " )";
    }
    
    public void transpose() {
        long temp = this.x;
        this.x = this.y;
        this.y = temp;
    }
    
    public int compareTo(rect other) {
        return Long.compare(this.x, other.x);
    }
}

class multiset {
    public TreeMap<Long, List<rect>> map;
    
    public multiset() {
        map = new TreeMap<>();
    }
    
    public void add(rect a) {
        if (map.containsKey(a.y)) {
            map.get(a.y).add(a);
        } else {
            List<rect> rectList = new ArrayList<>();
            rectList.add(a);
            map.put(a.y, rectList);
        }
    }
    
    public void remove(rect a) {
        List<rect> rectList = map.get(a.y);
        rectList.remove(a);
        if (rectList.size() == 0) {
            map.remove(a.y);
        }
    }
}

public class split {
    
    public static int N;
    public static rect[] rects;
    public static long minX = Long.MAX_VALUE;
    public static long maxX = Long.MIN_VALUE;
    public static long minY = Long.MAX_VALUE;
    public static long maxY = Long.MIN_VALUE;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("split.in"));
		N = Integer.parseInt(br.readLine());
		rects = new rect[N];
		
		for (int i = 0; i < N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    long x = Long.parseLong(st.nextToken());
		    long y = Long.parseLong(st.nextToken());
		    minX = Math.min(x, minX);
		    maxX = Math.max(x, maxX);
		    minY = Math.min(y, minY);
		    maxY = Math.max(y, maxY);
		    rects[i] = new rect(x, y);
		}
		
		Arrays.sort(rects);
//        System.out.println(Arrays.toString(rects));
        
        long origArea = (maxY - minY) * (maxX - minX);
        long minArea = getMinArea();
        
        for (int i = 0; i < N; i++) {
            rects[i].transpose();
        }
        long tempMin = minX;
        long tempMax = maxX;
        minX = minY;
        maxX = maxY;
        minY = tempMin;
        maxY = tempMax;
        Arrays.sort(rects);
        minArea = Math.min(minArea, getMinArea());
        
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
		pw.println(origArea - minArea);
        pw.close();
	}
	
	public static long getMinArea() {
	    multiset left = new multiset();
	    multiset right = new multiset();
//	    System.out.println("RECTS: " + Arrays.toString(rects));
	    for (int i = 0; i < N; i++) {
	        right.add(rects[i]);
	    }
//	    System.out.println(N);
	    long minArea = Long.MAX_VALUE;
	    int rectIndex = 0;
	    while (rectIndex < N - 1) {
	        
	        rect nextRect = rects[rectIndex];
	        while (rectIndex < N && rects[rectIndex].x <= nextRect.x) {
	            right.remove(rects[rectIndex]);
	            left.add(rects[rectIndex]);
	            rectIndex++;
	        }
	        long leftArea = (nextRect.x - minX) * (left.map.lastKey() - left.map.firstKey());
	        long rightArea = 0;
	        if (rectIndex < N) {
//	            System.out.println("RECT INDEX: " + rectIndex);
//	            System.out.println(left + "\n " + right);
	            rightArea = (maxX - rects[rectIndex].x) * (right.map.lastKey() - right.map.firstKey());
//	            System.out.println("maxrightX" + maxX + " " + rects[rectIndex] + "\n" +
//	            "maxX: " + maxX + "lowerX: " + rects[rectIndex].x + " y big:" + right.last().y + " y small: " + right.first().y);
	        }
	        minArea = Math.min(minArea, leftArea + rightArea);
	        
//	        System.out.println(minArea + " " + left + " " + leftArea + "\n" + right + rightArea + "\n");
	    }
	    return minArea;
	}
}