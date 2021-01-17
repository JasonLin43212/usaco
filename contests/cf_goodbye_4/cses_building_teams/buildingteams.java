import java.io.*;
import java.util.*;

public class buildingteams {
	
    public static int N;
    public static int M;
    public static int[] color;
    public static List<List<Integer>> edges = new ArrayList<>();
    public static boolean[] seen;
    
    public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		seen = new boolean[N];
		color = new int[N];
		for (int i = 0; i < N; i++) {
		    edges.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
		    int b = Integer.parseInt(st.nextToken()) - 1;
		    
		    edges.get(a).add(b);
		    edges.get(b).add(a);
		}
		
//		System.out.println(edges);
		boolean canColor = true;
		for (int i = 0; i < N; i++) {
		    if (!seen[i]) {
		        if (!colorPupil(i, 1)) {
		            canColor = false;
		            break;
		        }
		    }
		}
		
		if (!canColor) {
		    System.out.println("IMPOSSIBLE");
		} else {
		    String out = "";
		    for (int i = 0; i < N - 1; i++) {
		        out += color[i] + " ";
		    }
		    out += color[N-1];
		    System.out.println(out);
		}
	}
    
    public static boolean colorPupil(int pupil, int colorNum) {
        if (!seen[pupil]) {
            color[pupil] = colorNum;
            seen[pupil] = true;
            
            int nextColor = colorNum == 1 ? 2 : 1;

            for (int other : edges.get(pupil)) {
                if (!colorPupil(other, nextColor)) {
                    return false;
                }
            }
            return true;
        } else {
            return color[pupil] == colorNum;
        }
    }
}