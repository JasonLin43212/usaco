import java.io.*;
import java.util.*;

public class destruct {
    
    static class pair implements Comparable<pair> {
        public int a;
        public int b;
        
        public pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
        
        public int compareTo(pair other) {
            return Math.max(a, b) - Math.max(other.a, other.b);
        }
        
        public String toString() {
            return "(" + a + " + " + b + ")";
        }
    }
    
    public static int n;
    public static int[] nums;
    public static Set<Integer> numSet = new HashSet<>();
    public static int maxNum = Integer.MIN_VALUE;
    public static PrintWriter pw = new PrintWriter(System.out);
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int z = 0; z < t; z++) {
		    n = Integer.parseInt(br.readLine());
		    nums = new int[2*n];
		    numSet.clear();
		    maxNum = Integer.MIN_VALUE;
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    for (int i = 0; i < 2*n; i++) {
		        nums[i] = Integer.parseInt(st.nextToken());
		        numSet.add(nums[i]);
		        maxNum = Math.max(maxNum, nums[i]);
		    }
//		    System.out.println(Arrays.toString(nums) + "nums");
		    solve();
		}
		pw.close();
	}
	
	public static void solve() {
	    TreeMap<Integer, List<pair>> tMap = new TreeMap<>();
	    
	    for (int i = 0; i < 2*n; i++) {
	        for (int j = i + 1; j < 2*n; j++) {
	            int numSum = nums[i] + nums[j];
	            if (numSet.contains(numSum)) {
	                if (tMap.containsKey(numSum)) {
	                    tMap.get(numSum).add(new pair(nums[i], nums[j]));
	                } else {
	                    List<pair> pairList = new ArrayList<>();
	                    pairList.add(new pair(nums[i], nums[j]));
	                    tMap.put(numSum, pairList);
	                }
	            }
	        }
	    }
	    
	    System.out.println(tMap.size() + " n:" + n);
	    System.out.println(tMap + " maxnum: " + maxNum);
	    if (tMap.size() < n - 1 || (tMap.size() > 0 && tMap.lastKey() != maxNum)) {
	        pw.println("NO");
	        return;
	    }
	    
	    int curSum = tMap.lastKey();
	    List<pair> path = new ArrayList<>();
	    Set<Integer> 
	    for (int i = 0; i < n - 1; i++) {
	        List<pair> curList = tMap.get(curSum);
	        pair curPair = curList.get(0);
	        for (int j = 1; j < curList.size(); j++) {
	            if (curPair.compareTo(curList.get(j)) < 0) {
	                curPair = curList.get(j);
	            }
	        }
	        
	        int nextSum = Math.max(curPair.a, curPair.b);
	        if (tMap.containsKey(nextSum)) {
	            curSum = nextSum;
	            path.add(curPair);
	        } else {
	            pw.println("NO");
	            return;
	        }
	    }
	    System.out.println("PATH" + path);
	    pw.println("YES");
	    for (int i = 0; i < path.size(); i++) {
	        pw.println();
	    }
	}
}