/*
ID: jasonli7
LANG: JAVA
TASK: concom
*/
import java.io.*;
import java.util.*;

public class concom {

	public static int n;
	public static int[][] owns = new int[101][101];
	public static boolean[][] controls = new boolean[101][101];

	public static void addOwner(int a, int b, int p) {

		// Every company k that controls a should also
		// get some control of b by p percent
		for (int k = 0; k < 101; k++) {
			if (controls[k][a]) {
				owns[k][b] += p;
			}
		}

		// Every company k that owns more than 50% of b
		// now controls b
		for (int k = 0; k < 101; k++) {
			if (owns[k][b] > 50) {
				addController(k, b);
			}
		}
	}

	public static void addController(int a, int b) {
		// Don't do anything if a already controls b
		if (controls[a][b]) {
			return;
		}

		controls[a][b] = true; // Now a controls b

		// if a controls b, then we know that we can add all the things
		// that b controls to a
		for (int k = 0; k < 101; k++) {
			owns[a][k] += owns[b][k];
		}

		// we know that a controls > 50% of b
		// so any company that controls a, also controls b
		for (int k = 0; k < 101; k++) {
			if (controls[k][a]) {
				addController(k, b);
			}
		}

		// check again now that you added everything from b to a
		// to see if anything changed about a's control
		for (int k = 0; k < 101; k++) {
			if (owns[a][k] > 50) {
				addController(a, k);
			}
		}

	}

	public static void main (String [] args) throws IOException {
		String fileName = "concom";
		BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < 101; i++) {
			controls[i][i] = true; // a alwasy controls itself
		}

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			addOwner(a,b,p);
		}

		for (int a = 0; a < 101; a++){
			for (int b = 0; b < 101; b++) {
				if (a != b && controls[a][b]) {
					pw.println(a + " " + b);
				}
			}
		}

		pw.close();
	}
}
