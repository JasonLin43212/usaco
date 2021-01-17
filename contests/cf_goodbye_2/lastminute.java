import java.io.*;
import java.util.*;

public class lastminute {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
		    int n = Integer.parseInt(br.readLine());
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int[] notes = new int[n];
		    for (int j = 0; j < n; j++) {
		        notes[j] = Integer.parseInt(st.nextToken());
		    }
		    System.out.println(getNotes(n, notes));
		}
		
	}
	
	public static int getNotes(int n, int[] notes) {
	    boolean[] newNotes = new boolean[2*n+2];
	    int numNotes = 0;
	    for (int i = notes.length - 1; i >= 0; i--) {
	        if (!newNotes[notes[i] + 1]) {
	            newNotes[notes[i] + 1] = true;
	            numNotes++;
	        } else if (!newNotes[notes[i]]){
	            newNotes[notes[i]] = true;
	            numNotes++;
	        }
	    }
	    return numNotes;
	}
}