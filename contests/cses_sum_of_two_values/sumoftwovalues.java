import java.io.*;
import java.util.*;

class Pair{
    public int a;
    public int b;
    
    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public String toString() {
        return "(" + a + ", " + b + ")";
    }
}

public class sumoftwovalues {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Pair[] nums = new Pair[N];
		for (int i = 0; i < N; i++) {
		    nums[i] = new Pair(Integer.parseInt(st.nextToken()), i + 1);
		}
		Arrays.sort(nums, (a, b) -> {
		    return a.a - b.a;
		});
		
//		System.out.println(Arrays.toString(nums));
		int p1 = 0;
		int p2 = N-1;
		
		while (p1 != p2) {
		    int sum = nums[p1].a + nums[p2].a;
		    if (sum < x) {
		        p1++;
		    } else if (sum > x) {
		        p2--;
		    } else {
		        // sum is equal to x
		        break;
		    }
		}
		
		if (nums[p1].a + nums[p2].a == x && p1 != p2) {
		    System.out.println((nums[p1].b) + " " + (nums[p2].b));
		} else {
		    System.out.println("IMPOSSIBLE");
		}
		
		
	}
}