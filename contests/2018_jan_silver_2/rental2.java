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
    public static int numStores = 0;
    public static int numRenters = 0;
    
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
        int[] tempRent = new int[R];
        for (int i = 0; i < R; i++) {
            tempRent[i] = renters[R-1-i];
        }
        renters = tempRent;
        
        System.out.println(Arrays.toString(cows));
        System.out.println(Arrays.toString(stores));
        System.out.println(Arrays.toString(renters));
		
        long totalMoney = 0;
        for (int i = 0; i < N; i++) {
            long milkMoney = getMilkMoney(cows[i]);
            long rentMoney = getRenterMoney();
            System.out.println(milkMoney + " " + rentMoney);
            if (milkMoney > rentMoney) {
                totalMoney += milkMoney;
                updateStores(cows[i]);
            } else {
                totalMoney += rentMoney;
                numRenters++;
            }
        }
        
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
		pw.println(totalMoney);
		pw.close();
	}
	
	public static long getMilkMoney(int gallons) {
	    if (numStores >= M) {
	        return 0;
	    }
	    int currentStoreId = numStores;
	    store curStore = stores[currentStoreId];
	    long milkMoney = 0;
	    while (gallons > 0 && currentStoreId < N) {
	        if (gallons >= curStore.gallons) {
	            gallons -= curStore.gallons;
	            milkMoney += curStore.gallons * curStore.price;
	            currentStoreId++;
	            curStore = stores[currentStoreId];
	        } else {
	            milkMoney += gallons * curStore.price;
	            gallons = 0;
	        }
	    }
	    return milkMoney;
	}
	
	public static long getRenterMoney() {
	    if (numRenters >= R) {
	        return 0;
	    }
	    return renters[numRenters];
	}
	
	public static void updateStores(int gallons) {
	    if (numStores >= M) {
            return;
        }
        store curStore = stores[numStores];
        while (gallons > 0 && numStores < N) {
            if (gallons >= curStore.gallons) {
                gallons -= curStore.gallons;
                curStore.gallons = 0;
                numStores++;
                curStore = stores[numStores];
            } else {
                curStore.gallons -= gallons;
                gallons = 0;
            }
        }
	}
}