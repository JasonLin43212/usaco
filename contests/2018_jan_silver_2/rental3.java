import java.io.*;
import java.util.*;

class store implements Comparable<store>{
    public long gallons;
    public long price;
    
    public store(int gallons, int price) {
        this.gallons = (long) gallons;
        this.price = (long) price;
    }
    
    public int compareTo(store other) {
        return Long.compare(other.price, this.price);
    }
    
    @Override
    public String toString() {
        return "Buy " + this.gallons + " gallons for " + this.price + " cents.";
    }
}

public class rental {
    
    public static int N;
    public static int M;
    public static int R;
    public static int[] cows;
    public static store[] stores;
    public static int[] renters;
    
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("rental.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
		
        cows = new int[N];
        stores = new store[M];
        renters = new int[R];
        
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            stores[i] = new store(g, p);
        }
        for (int i = 0; i < R; i++) {
            renters[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(cows);
        Arrays.sort(stores);
        Arrays.sort(renters);
        int[] tempCows = new int[N];
        for (int i = 0; i < N; i++) {
            tempCows[i] = cows[N-1-i];
        }
        cows = tempCows;
        int[] tempRent = new int[R];
        for (int i = 0; i < R; i++) {
            tempRent[i] = renters[R-1-i];
        }
        renters = tempRent;
        
        int[] cowPrefix = new int[N+1];
        for (int i = 1; i <= N; i++) {
            cowPrefix[i] = cowPrefix[i-1] + cows[i-1];
        }
        int[] renterPrefix = new int[R+1];
        for (int i = 1; i <= R; i++) {
            renterPrefix[i] = renterPrefix[i-1] + renters[i-1];
        }
        
//        System.out.println(Arrays.toString(cows));
//        System.out.println(Arrays.toString(stores));
//        System.out.println(Arrays.toString(renters));
//        System.out.println(Arrays.toString(cowPrefix));
//        System.out.println(Arrays.toString(renterPrefix));
		
        long totalMoney = 0;
        for (int i = 0; i < N; i++) {
            long milkMoney = getMilkMoney(cowPrefix[i]);
            long rentMoney = renterPrefix[Math.min(R, N - i)];
            totalMoney = Math.max(totalMoney, milkMoney + rentMoney);
        }
        
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
		pw.println(totalMoney);
		pw.close();
	}
	
	public static long getMilkMoney(int gallons) {
	    int currentStoreId = 0;
	    
	    long milkMoney = 0;
	    while (gallons > 0 && currentStoreId < M) {
	        store curStore = stores[currentStoreId];
	        if (gallons >= curStore.gallons) {
	            gallons -= curStore.gallons;
	            milkMoney += curStore.gallons * curStore.price;
	            currentStoreId++;
	        } else {
	            milkMoney += gallons * curStore.price;
	            gallons = 0;
	        }
	    }
	    return milkMoney;
	}
	
}