import java.io.*;
import java.util.*;

public class puzzle {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(System.out);
		for (int x = 0; x < t; x++) {
		    int n = Integer.parseInt(br.readLine());
		    String b = br.readLine();
		    StringBuilder sb = new StringBuilder();
		    int prevD = -1;
		    for (int i = 0; i < n; i++) {
		        int bNum = (int) b.charAt(i) - 48;
//		        System.out.println("bnum" + bNum);
		        if (prevD == -1) {
		            sb.append('1');
		            prevD = bNum + 1;
		        } else {
		            int addOne = bNum + 1;
		            if (addOne == prevD) {
		                sb.append('0');
		                prevD = bNum;
		            } else {
		                sb.append('1');
		                prevD = addOne;
		            }
		        }
//		        System.out.println("printing" + sb + " " +  prevD);
		    }
		    pw.println(sb.toString());
		}
		
		
		pw.close();
	}
}