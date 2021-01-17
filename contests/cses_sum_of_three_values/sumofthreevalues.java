import java.io.*;
import java.util.*;

class number{
    public int num;
    public int index;
    
    public number(int num, int index) {
        this.num = num;
        this.index = index;
    }
}
public class sumofthreevalues {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		number[] nums = new number[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
		    nums[i] = new number(Integer.parseInt(st.nextToken()), i + 1);
		}
		
		
		Arrays.sort(nums, (a, b) -> a.num - b.num);
		
		for (int i = 0; i < N - 2; i++) {
		    int j = i + 1;
		    int k = N - 1;
		    while (j != k) {
		        int sum = nums[i].num + nums[j].num + nums[k].num;
		        if (sum == X) {
		            System.out.println(nums[i].index + " " + nums[j].index + " " + nums[k].index);
		            return;
		        }
		        else if (sum < X) {
		            j++;
		        } else {
		            k--;
		        }
		    }
		}
		
		System.out.println("IMPOSSIBLE");
	}
}