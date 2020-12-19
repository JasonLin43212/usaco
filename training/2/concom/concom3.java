/*
ID: jasonli7
LANG: JAVA
TASK: concom
*/
import java.io.*;
import java.util.*;

public class concom {

	public static int n;
	public static int[][] percents = new int[101][101];
	public static int[][] ownedByPercent = new int[101][101];
	public static int[][] seenAOwnsB = new int[101][101];

	public static void main (String [] args) throws IOException {
		String fileName = "concom";
		BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			percents[a][b] = p;
			ownedByPercent[b][a] = p;
			ownedByPercent[a][a] = 100;
			ownedByPercent[b][b] = 100;
			seenAOwnsB[a][b] = 1;
		}

		for (int i = 0; i < 100; i++) {
			boolean updated = false;

			for (int b = 1; b < 101; b++) {
				for (int b = 1; b < 101; b++) {
					for (int k = 1; k < 101; k++) {

					}
				}
			}

			if (!updated) {
				break;
			}
		}

		// solution.sort((a, b) -> {
		// 	if (a.get(0) == b.get(0)) {
		// 		return a.get(1) - b.get(1);
		// 	} else {
		// 		return a.get(0) - b.get(0);
		// 	}
		// });
		//
		// for (List<Integer> entry: solution) {
		// 	pw.println(entry.get(0) + " " + entry.get(1));
		// }
		pw.close();
	}
}
