import java.io.*;
import java.util.*;

public class paint {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		String paint = br.readLine();
		
//		System.out.println(N + " " + Q + " " + paint);
		
		int[] forward = new int[N + 1];
		int[] forwardMin = new int[26];
		Arrays.fill(forwardMin, 26);
		
		for (int i = 0; i < N; i++) {
		    int color = (int) (paint.charAt(i) - 'A');
		    forward[i + 1] = forward[i];
		    if (i == 0 || forwardMin[color] != color) {
		        forward[i + 1] += 1;
		    }
		    
		    
		    for (int j = 0; j < 26; j++) {
		        if (j == color || forwardMin[j] > color) {
		            forwardMin[j] = color;
		        }
		    }
		}
		
		int[] backward = new int[N + 1];
        int[] backwardMin = new int[26];
        Arrays.fill(backwardMin, 26);
        
        for (int i = N - 1; i >= 0; i--) {
            int color = (int) (paint.charAt(i) - 'A');
            backward[i] = backward[i + 1];
            if (i == N - 1 || backwardMin[color] != color ) {
                backward[i] += 1;
            }
            
            
            for (int j = 0; j < 26; j++) {
                if (j == color || backwardMin[j] > color) {
                    backwardMin[j] = color;
                }
            }
        }
//        System.out.println(Arrays.toString(forward));
//        System.out.println(Arrays.toString(backward));
		
		PrintWriter pw = new PrintWriter(System.out);
		for (int i = 0; i < Q; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    pw.println(forward[a - 1] + backward[b]);
		}
		pw.close();
	}
}