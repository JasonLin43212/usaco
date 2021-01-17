import java.io.*;
import java.util.*;

public class daisy {
    
    public static int[] flowers;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		flowers = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
		    flowers[i] = Integer.parseInt(st.nextToken());
		}
		
		int numPhotos = 0;
		for (int i = 0; i < N; i++) { 
		    for (int j = i; j < N; j++) {
		        int totalPetal = 0;
		        for (int p = i; p <= j; p++) {
		            totalPetal += flowers[p];
		        }
		        if (totalPetal % (j - i + 1) == 0) {
		            int average = totalPetal / (j - i + 1);
		            for (int p = i; p <= j; p++) {
		                if (flowers[p] == average) {
		                    numPhotos++;
		                    break;
		                }
		            }
		        }
		    }
		}
		System.out.println(numPhotos);
	}
}