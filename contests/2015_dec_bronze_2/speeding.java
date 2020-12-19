import java.io.*;
import java.util.*;

public class speeding {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("speeding.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] roadSpeed = new int[100];
		int[] cowSpeed = new int[100];
		
		int curRoad = 0;
		for (int n = 0; n < N; n++) {
		    st = new StringTokenizer(br.readLine());
		    int roadLength = Integer.parseInt(st.nextToken());
		    int speed = Integer.parseInt(st.nextToken());
		    for (int r = 0; r < roadLength; r++) {
		        roadSpeed[curRoad + r] = speed;
		    }
		    curRoad += roadLength;
		}
		
		int curCow = 0;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int roadLength = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            for (int r = 0; r < roadLength; r++) {
                cowSpeed[curCow + r] = speed;
            }
            curCow += roadLength;
        }
        
//        System.out.println(Arrays.toString(roadSpeed) + "\n" + Arrays.toString(cowSpeed));
		
        int maxDifference = 0;
        for (int i = 0; i < 100; i++) {
            maxDifference = Math.max(maxDifference, cowSpeed[i] - roadSpeed[i]);
        }
        
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));
		pw.println(maxDifference);
		pw.close();
	}
}