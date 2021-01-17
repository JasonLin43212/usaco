import java.io.*;
import java.util.*;

import org.w3c.dom.css.Rect;

class point implements Comparable<point> {
    public long x;
    public long y;
    public int index;
    
    public point (long x, long y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }
    
    public String toString() {
        return "(" + x + ", " + y + ")"; 
    }
    
    public int compareTo(point other) {
        return Long.compare(this.x, other.x);
    }
}

public class triangles {
    
    public static int N;
    public static point[] points;
    public static long[] xSum;
    public static long[] ySum;
    public static long divide = (long) (1e9 + 7);
    public static Map<Long, List<point>> sameX = new HashMap<>();
    public static Map<Long, List<point>> sameY = new HashMap<>();
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
		N = Integer.parseInt(br.readLine());
		points = new point[N];
		xSum = new long[N];
		ySum = new long[N];
		
		for (int i = 0; i < N; i++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        long x = Long.parseLong(st.nextToken());
	        long y = Long.parseLong(st.nextToken());
	        points[i] = new point(x, y, i);
	        
	        if (sameX.containsKey(x)) {
	            sameX.get(x).add(points[i]);
	        } else {
	            List<point> pointList = new ArrayList<>();
	            pointList.add(points[i]);
	            sameX.put(x, pointList);
	        }
	        
	        if (sameY.containsKey(y)) {
                sameY.get(y).add(points[i]);
            } else {
                List<point> pointList = new ArrayList<>();
                pointList.add(points[i]);
                sameY.put(y, pointList);
            }
		}
		
		for (long x : sameX.keySet()) {
		    List<point> rectList = sameX.get(x);
		    rectList.sort((a, b) -> Long.compare(a.y, b.y));
		    
		    long sum = 0;
		    for (int i = 1; i < rectList.size(); i++) {
		        sum += rectList.get(i).y - rectList.get(0).y;
		    }
		    ySum[rectList.get(0).index] = sum % divide;
		    
		    for (int i = 1; i < rectList.size(); i++) {
		        long diff = rectList.get(i).y - rectList.get(i-1).y;
		        sum -= diff * (rectList.size() - (i * 2));
		        ySum[rectList.get(i).index] = sum % divide;
		    }
		}
		
		for (long y : sameY.keySet()) {
            List<point> rectList = sameY.get(y);
            rectList.sort((a, b) -> Long.compare(a.x, b.x));
            
            long sum = 0;
            for (int i = 1; i < rectList.size(); i++) {
                sum += rectList.get(i).x - rectList.get(0).x;
            }
            xSum[rectList.get(0).index] = sum % divide;
            
            for (int i = 1; i < rectList.size(); i++) {
                long diff = rectList.get(i).x - rectList.get(i-1).x;
                sum -= diff * (rectList.size() - (i * 2));
                xSum[rectList.get(i).index] = sum % divide;
            }
        }
		
//		System.out.println(Arrays.toString(points) + "\nX: " + sameX + "\nY: " + sameY);
//		System.out.println("xsum: " + Arrays.toString(xSum) + "\nysum: " + Arrays.toString(ySum));
		
		long totalArea = 0;
		for (point curPoint : points) {
            long newArea = xSum[curPoint.index] * ySum[curPoint.index];
            newArea %= divide;
            totalArea += newArea;
            totalArea %= divide;
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
		pw.println(totalArea);
		pw.close();
	}
}