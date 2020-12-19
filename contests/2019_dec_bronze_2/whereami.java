import java.io.*;
import java.util.*;

public class whereami {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("whereami.in"));
		
		int N = Integer.parseInt(br.readLine());
		String colors = br.readLine();
		
		int solution = 0;
		
		for (int i = 1; i <= N; i++) {
		    if (isValid(colors, i)) {
		        solution = i;
		        break;
		    }
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("whereami.out")));
		pw.println(solution);
		pw.close();
	}
	
	public static boolean isValid(String colors, int k) {
	    Set<String> seen = new HashSet<>();
	    for (int i = 0; i < colors.length() - k + 1; i++) {
	        String substring = colors.substring(i, i+k);
	        if (seen.contains(substring)) {
	            return false;
	        }
	        seen.add(substring);
	        
 	    }
	    return true;
	}
}