import java.io.*;
import java.util.*;

public class highcard {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("highcard.in"));
		int n = Integer.parseInt(br.readLine());
		int[] elsie = new int[n];
		for (int i = 0; i < n; i++) {
		    elsie[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(elsie);
		
		int[] bessie = new int[n];
		int elsieIndex = 0;
		int bessieIndex = 0;
		for (int i = 1; i <= 2*n; i++) {
		    if (elsieIndex < n && i == elsie[elsieIndex]) {
		        elsieIndex++;
		    } else {
		        bessie[bessieIndex] = i;
		        bessieIndex++;
		    }
		}
		
//		System.out.println(Arrays.toString(elsie) + " " + Arrays.toString(bessie));
		
		int bi = 0;
		int ei = 0;
		int points = 0;
		while (bi < n && ei < n) {
		    if (bessie[bi] > elsie[ei]) {
		        points++;
		        bi++;
		        ei++;
		    } else {
		        bi++;
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
		pw.println(points);
		pw.close();
	}
}