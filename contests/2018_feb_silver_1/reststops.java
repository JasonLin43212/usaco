import java.io.*;
import java.util.*;

public class reststops {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("reststops.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
        int r_f = Integer.parseInt(st.nextToken());
        int r_b = Integer.parseInt(st.nextToken());
        
        int[] restStopNum = new int[N];
        int[] restStopTasty = new int[N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            restStopNum[i] = Integer.parseInt(st.nextToken());
            restStopTasty[i] = Integer.parseInt(st.nextToken());
        }
        
//        System.out.println(Arrays.toString(restStopNum) + " " + Arrays.toString(restStopTasty));

        List<Integer> goodStops = new ArrayList<>();
        List<Integer> goodTasty = new ArrayList<>();

        int curMax = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (restStopTasty[i] > curMax) {
                curMax = restStopTasty[i];
                goodStops.add(0, restStopNum[i]);
                goodTasty.add(0, restStopTasty[i]);
            }
        }
        
//        System.out.println(goodStops + " " + goodTasty);
        
        long maxTasty = 0;
        long bt = 0;
        long ft = 0;
        long lastStop = 0;
        for (int i = 0; i < goodStops.size(); i++) {
            // This is the time that it takes bessie to
            // get from the previous stop to the next restStop
            bt += (goodStops.get(i) - lastStop) * (long) r_b;
            
            // This is the time that it takes farmer to
            // get from previous stop to next restStop
            ft += (goodStops.get(i) - lastStop) * (long) r_f;
            
            // bt < ft from problem assumption
            // The time difference ft - bt is the amount of
            // time that bessie has to eat grass.
            maxTasty +=  ((long)(ft - bt) * goodTasty.get(i));
            
            bt = ft;
            lastStop = goodStops.get(i);
        }
        
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
		pw.println(maxTasty);
		pw.close();
	}
}