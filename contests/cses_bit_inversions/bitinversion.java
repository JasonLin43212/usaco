import java.io.*;
import java.util.*;

public class bitinversion {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("bitinversion.in"));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		String bits = br.readLine();
		char prevNum = bits.charAt(0);;
		for (int i = 1; i < bits.length(); i++) {
	        if (prevNum != bits) {
	            
	        }
		}
		
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bitinversion.out")));
		pw.println("solution");
		pw.close();
	}
}