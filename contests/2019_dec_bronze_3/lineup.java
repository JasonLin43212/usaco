import java.io.*;
import java.util.*;

public class lineup {
    
    public static int NUMCOWS = 8;
    public static Map<String, List<String>> milkedBeside = new HashMap<>();
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lineup.in"));
		
		/**
		 * generate all permutations of cows
		 * check them all
		 * sort solutions
		 * output alphabetically sorted one
		 */
		
		int N = Integer.parseInt(br.readLine());
		
		String[] cows = new String[] {
		        "Bessie", "Buttercup", "Belinda", "Beatrice",
		        "Bella", "Blue", "Betsy", "Sue"
		};
		
		NUMCOWS = cows.length;
		
		for (int i = 0; i < NUMCOWS; i++) {
		    milkedBeside.put(cows[i], new ArrayList<>());
		}
		
		for (int n = 0; n < N; n++) {
		    String line = br.readLine();
		    String[] cowNames = line.split(" must be milked beside ");
		    milkedBeside.get(cowNames[0]).add(cowNames[1]);
		}
		
//		System.out.println(milkedBeside);
		List<List<String>> permutations = generatePermutations(cows);
//		System.out.println(permutations);
		
		List<List<String>> correct = new ArrayList<>();
		for (int i = 0; i < permutations.size(); i++) {
		    if (isCorrect(permutations.get(i))) {
		        correct.add(permutations.get(i));
		    }
		}
		
		correct.sort((a, b) -> {
		    for (int i = 0; i < a.size(); i++) {
		        int compareVal = a.get(i).compareTo(b.get(i));
		        if (compareVal != 0) {
		            return compareVal;
		        }
		    }
		    return 0;
		});
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lineup.out")));
		for (int i = 0; i < NUMCOWS; i++) {
		    pw.println(correct.get(0).get(i));
		}
		pw.close();
	}
	
	public static List<List<String>> generatePermutations(String[] names) {
	    List<List<String>> currentPermutations = new ArrayList<>();
	    List<String> cows = new ArrayList<>(Arrays.asList(names));
	    List<String> perm = new ArrayList<>();
	    helper(cows, perm, currentPermutations);
	    
	    return currentPermutations;
	}
	
	public static void helper(List<String> cows, List<String> perm, List<List<String>> perms) {
	    // Have a permutation
	    if (perm.size() == NUMCOWS) {
	        perms.add(List.copyOf(perm));
	        return;
	    }
	    
	    int cowSize = cows.size();
	    for (int i = 0; i < cowSize; i++) {
	        perm.add(cows.get(i));
	        String addedCow = cows.remove(i);
	        helper(cows, perm, perms);
	        cows.add(i, addedCow);
	        perm.remove(NUMCOWS - cowSize);
	    }
	}
	
	public static boolean isCorrect(List<String> perm) {
	    for (String cow1 : milkedBeside.keySet()) {
	        for (String cow2 : milkedBeside.get(cow1)) {
	            int cow1Index = perm.indexOf(cow1);
	            int cow2Index = perm.indexOf(cow2);
	            if (Math.abs(cow2Index - cow1Index) != 1) {
	                return false;
	            }
	        }
	    }
	    return true;
	}
}