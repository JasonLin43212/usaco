import java.io.*;
import java.util.*;

public class canine {
    
    public static int[] freq;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
		    System.out.println(numChange(br.readLine()));
		}
		
	}
	
	public static int numChange(String s) {
	    
	    int change = badPalim(s);
	    int numChange = 0;
	    while (change != -1) {
	        numChange++;
	        s = s.substring(0, change) + getLeastFreq() + s.substring(change + 1);
//	        System.out.println(s);
	        change = badPalim(s);
	    }
	    return numChange;
	}
	
	public static String getLeastFreq() {
	    int letterNum = -1;
	    int leastFreq = Integer.MAX_VALUE;
//	    System.out.println("letter freq: " + Arrays.toString(freq));
	    for (int i = 0; i < freq.length; i++) {
	        if (freq[i] < leastFreq) {
	            leastFreq = freq[i];
	            letterNum = i;
	        }
	    }
	    return Character.toString((char)('a' + letterNum));
	}
	
	public static boolean isPalim(String s) {
	    for (int i = 0; i < s.length() / 2; i++) {
	        if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static int badPalim(String s) {
	    freq = new int[26];
        int[] hasPalim = new int[s.length()];
        for (int i = 2; i <= s.length(); i++) {
            for (int j = 0; j <= s.length() - i; j++) {
                if (isPalim(s.substring(j, j+i))) {
                    for (int k = j; k < j+i; k++) {
                        if (i % 2 == 0 || k != j+(i/2)) {
                            hasPalim[k]++;
                        }
                        
                    }
                }
            }
        }
        
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        
//        System.out.println(Arrays.toString(hasPalim));
        int mostPalim = 0;
        int index = -1;
        for (int i = 0; i < hasPalim.length; i++) {
            if (hasPalim[i] > mostPalim) {
                mostPalim = hasPalim[i];
                index = i;
            } else if (hasPalim[i] == mostPalim && 
                      Math.abs(hasPalim.length/2 - i) > Math.abs(hasPalim.length/2 - index)) {
                mostPalim = hasPalim[i];
                index = i;
            }
        }
        return index;
	}
}