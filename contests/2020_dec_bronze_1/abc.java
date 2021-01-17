import java.io.*;
import java.util.*;

public class abc {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<Integer> nums = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 7; i++) {
		    nums.add(Integer.parseInt(st.nextToken()));
		}
		
		int allSum = 0;
		int maxIndex = -1;
		// numbers only range from 1 - 10^9
		// A+B+C has to be the max element
		for (int i = 0; i < 7; i++) {
		    if (nums.get(i) > allSum) {
		        allSum = nums.get(i);
	            maxIndex = i;
		    }
		}
		
		nums.remove(maxIndex);
		
		
		// two smallest numbers have to be A, B
		// we know A <= B <= C
		// cant find C immediately cuz A+B < C might happen
		
		int A = Integer.MAX_VALUE;
		int B = Integer.MAX_VALUE;
		int indexA = -1;
		for (int i = 0; i < 6; i++) {
		    if (nums.get(i) < A) {
		        A = nums.get(i);
		        indexA = i;
		    }
		}
		nums.remove(indexA);
		for (int i = 0; i < 5; i++) {
            if (nums.get(i) < B) {
                B = nums.get(i);
            }
        }
		
		int AandB = A + B;
		int C = allSum - AandB;
		
		System.out.println(A + " " + B + " " + C);
	}
}