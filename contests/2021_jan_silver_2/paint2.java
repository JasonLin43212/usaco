import java.io.*;
import java.util.*;

public class paint {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		String paint = br.readLine();
		
		char[] paintList = new char[N];
		for (int i = 0; i < N; i++) {
		    paintList[i] = paint.charAt(i);
		}
		int[] forward = new int[N+1];
		int[] back = new int[N+1];
		for (int i = 1; i < N+1; i++) {
		    forward[i] = forward[i-1] + (i == 1 || paintList[i-1] > paintList[i-2] ? 1 : 0);
		}
		for (int i = N-1; i >= 0; i--) {
            back[i] = back[i+1] + (i == N-1 || paintList[i] > paintList[i + 1] ? 1 : 0);
        }
//		
		System.out.println(Arrays.toString(paintList));
		System.out.println(Arrays.toString(forward));
		System.out.println(Arrays.toString(back));
		PrintWriter pw = new PrintWriter(System.out);
		for (int i = 0; i < Q; i++) {
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken()) - 1;
		    int b = Integer.parseInt(st.nextToken());
		    System.out.println(a + " " + b);
		    int leftPaint = 0;
		    if (a > 0) {
		        leftPaint = Math.max(forward[a] - forward[0], back[0] - back[a-1] + 1);
		    }
		    int rightPaint = 0;
		    if (b < N - 1) {
                rightPaint = Math.max(forward[N] - forward[b+1] + 1, back[b] - back[N]);
            }
		    

		    System.out.println("LEFT: " + leftPaint + " RIGHT: " + rightPaint);
		    pw.println(leftPaint + rightPaint);
		}
		
		pw.close();
	}
}