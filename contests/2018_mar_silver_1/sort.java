import java.io.*;
import java.util.*;

public class sort {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sort.in"));
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		Integer[] indexs = new Integer[n];
		for (int i = 0; i < n; i++) {
		    nums[i] = Integer.parseInt(br.readLine());
		    indexs[i] = i;
		}
		Arrays.sort(indexs, (a, b) -> nums[a] - nums[b]);
//		System.out.println(Arrays.toString(nums) + " " + Arrays.toString(indexs));
		int totalMoves = 0;
		for (int i = 0; i < n; i++) {
		    int moves = Math.max(0, indexs[i] - i);
		    totalMoves = Math.max(totalMoves, moves);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		pw.println(totalMoves + 1);
		pw.close();
	}
}