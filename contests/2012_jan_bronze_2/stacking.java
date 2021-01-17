import java.io.*;
import java.util.*;

public class stacking {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("stacking.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] deltaHaybale = new int[N+1];
		for (int i = 0 ; i < K; i++) {
		    st = new StringTokenizer(br.readLine());
		    int start = Integer.parseInt(st.nextToken()) - 1;
		    int end = Integer.parseInt(st.nextToken()) - 1;
		    deltaHaybale[start]++;
		    deltaHaybale[end + 1]--;
		}
		
//		System.out.println(Arrays.toString(deltaHaybale));
		int[] numHays = new int[K+1];
		int addingHay = 0;
		for (int i = 0; i < N; i++) {
		    
		    addingHay += deltaHaybale[i];
		    numHays[addingHay]++;
//		    System.out.println("adding index " + i + " " + Arrays.toString(numHays));
		}
		
//		System.out.println(Arrays.toString(numHays));
		
		int out = 0;
		int totalHay = 0;
		for (int i = 0; i < K + 1; i++) {
		    totalHay += numHays[i];
		    if (totalHay >= (N / 2) + 1) {
                out = i;
                break;
            }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stacking.out")));
		pw.println(out);
		pw.close();
	}
}