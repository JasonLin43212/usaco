import java.io.*;
import java.util.*;

public class spaced {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] beauty = new long[n][n];
		
		for (int i = 0; i < n; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    for (int j = 0; j < n; j++) {
		        beauty[i][j] = Long.parseLong(st.nextToken());
		    }
		}
		
		long rowBeauty = 0;
		for (int r = 0; r < n; r++) {
		    long beautyOne = 0;
		    long beautyTwo = 0;
		    for (int c = 0; c < n; c++) {
		        if (c % 2 == 0) {
		            beautyOne += beauty[r][c];
		        } else {
		            beautyTwo += beauty[r][c];
		        }
		    }
//            System.out.println("ROW: " + r + " " + beautyOne + " " + beautyTwo);
		    rowBeauty += Math.max(beautyOne, beautyTwo);
		}
//        System.out.println("ROW BEAUTY: " + rowBeauty);
		
		long colBeauty = 0;
		for (int c = 0; c < n; c++) {
            long beautyOne = 0;
            long beautyTwo = 0;
            for (int r = 0; r < n; r++) {
                if (r % 2 == 0) {
                    beautyOne += beauty[r][c];
                } else {
                    beautyTwo += beauty[r][c];
                }
            }
//            System.out.println("COL: " + c + " " + beautyOne + " " + beautyTwo);

            colBeauty += Math.max(beautyOne, beautyTwo);
        }
//      System.out.println("COL BEAUTY: " + colBeauty);

		
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(Math.max(colBeauty, rowBeauty));
		pw.close();
	}
}