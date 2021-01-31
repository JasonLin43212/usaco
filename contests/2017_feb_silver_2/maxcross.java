import java.io.*;
import java.util.*;

public class maxcross {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        boolean[] lights = new boolean[N];
        Arrays.fill(lights, true);
        for (int i = 0; i < B; i++) {
            lights[Integer.parseInt(br.readLine()) - 1] = false;
        }
        
        int[] broke = new int[N + 1];
        int[] work = new int[N + 1];
//        System.out.println(Arrays.toString(lights));
        for (int i = 1; i < N + 1; i++) {
            boolean isWork = lights[i-1];
            broke[i] = broke[i-1] + (isWork ? 0 : 1);
            work[i] = work[i-1] + (isWork ? 1 : 0);
        }
        
//        System.out.println(Arrays.toString(broke) + "\n" + Arrays.toString(work));
        
        int minFix = K + 1;
        int p1 = 0;
        int p2 = 0;
        while (p2 < N) {
//            System.out.println(p1 + " " + p2);
//            System.out.println("num size = " + (p2 - p1 + 1) );
//            System.out.println("cur min: " + minFix + "\n");
            if (p2 + 1 - p1 == K) {
                minFix = Math.min(minFix, broke[p2 + 1] - broke[p1]);
                p1++;
            } else {
                p2++;
            }
        }
        
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
		pw.println(minFix);
		pw.close();
	}
}