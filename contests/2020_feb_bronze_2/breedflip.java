import java.io.*;
import java.util.*;

public class breedflip {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("breedflip.in"));
		br.readLine();
		String s = br.readLine();
		String solution = br.readLine();
		
		int numTimes = 0;
		boolean transforming = false;
		
		for (int i = 0; i < s.length(); i++) {
		    if (!transforming) {
		        if (s.charAt(i) != solution.charAt(i)) {
		            numTimes++;
		            transforming = true;
		        }
		    } else {
		        if (s.charAt(i) == solution.charAt(i)) {
		            transforming = false;
		        }
		    }
		}
		
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("breedflip.out")));
		pw.println(numTimes);
		pw.close();
	}
}