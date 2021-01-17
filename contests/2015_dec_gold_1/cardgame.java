import java.io.*;
import java.util.*;

public class cardgame {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cardgame.in"));
		int n = Integer.parseInt(br.readLine());
		int[] elsie = new int[n];
		for (int i = 0; i < n; i++) {
		    elsie[i] = Integer.parseInt(br.readLine());
		}
		int[] elsieHigh = new int[n/2];
		int[] elsieLow = new int[n/2];
		for (int i = 0; i < n/2; i++) {
		    elsieHigh[i] = elsie[i];
		}
		for (int i = 0; i < n/2; i++) {
            elsieLow[i] = elsie[i + n/2];
        }
		Arrays.sort(elsie);
		int elIndex = 0;
		int[] bessie = new int[n];
		int bessCards = 0;
		for (int i = 1; i <= 2*n; i++) {
		    if (elIndex < n && elsie[elIndex] == i) {
		        elIndex++;
		    } else {
		        bessie[bessCards] = i;
		        bessCards++;
		    }
		}
		Arrays.sort(bessie);
		Arrays.sort(elsieHigh);
		Arrays.sort(elsieLow);
		
//		System.out.println(Arrays.toString(elsieHigh) + " " + Arrays.toString(elsieLow) + " "+ Arrays.toString(bessie));
		
		int numPoints = 0;
		int elsLow = n/2 - 1;
		for (int i = n/2 - 1; i >= 0; i--) {
//		    System.out.println("LOW GAME: elsie- " + elsieLow[elsLow] + " bess- " + bessie[i] );
		    if (bessie[i] < elsieLow[elsLow]) {
		        numPoints++;
		        elsLow--;
		    } 
		    if (elsLow == -1) {
                break;
            }
		}
		int elsHigh = 0;
		for (int i = 0; i < n/2; i++) {
            if (bessie[i+n/2] > elsieHigh[elsHigh]) {
                numPoints++;
                elsHigh++;
            }
            if (elsHigh == n/2) {
                break;
            }
        }
//		System.out.println(numPoints);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
		pw.println(numPoints);
		pw.close();
	}
}