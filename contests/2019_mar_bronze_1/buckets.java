import java.io.*;
import java.util.*;

public class buckets {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("buckets.in"));
		
		int Br=0;
		int Bc=0;
		int Lr=0;
		int Lc=0;
		int Rr=0;
		int Rc=0;
		
		for (int i = 0; i < 10; i++) {
	        String line = br.readLine();
	        for (int j = 0; j < 10; j++) {
	            if (line.charAt(j) == 'B') {
	                Br = i;
	                Bc = j;
	            }
	            if (line.charAt(j) == 'L') {
                    Lr = i;
                    Lc = j;
                }
	            if (line.charAt(j) == 'R') {
                    Rr = i;
                    Rc = j;
                }
	        }
		}
		
		int out = 0;
		if (Br == Lr) {
		    if (Rr == Br &&
		        ((Rc > Bc && Rc < Lc) ||
		         (Rc < Bc && Rc > Lc))) {
		        out = Math.abs(Lc - Bc) + 1;
		    } else {
		        out = Math.abs(Lc - Bc) - 1;
		    }
		} else if (Bc == Lc &&
		          ((Rr > Br && Rr < Lr) ||
		          (Rr < Br && Rr > Lr))) {
		    if (Rc == Bc) {
		        out = Math.abs(Lr - Br) + 1;
		    } else {
		        out = Math.abs(Lr - Br) - 1;
		    }
		} else {
		    out = Math.abs(Lr - Br) + Math.abs(Lc - Bc) - 1;
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("buckets.out")));
		pw.println(out);
		pw.close();
	}
}