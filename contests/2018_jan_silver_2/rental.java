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
        
        // Result at index i is for if we use i cows for milking and N - i cows for rent
        long[] results = new long[N+1];
        int curStoreId = 0;
        for (int i = 1; i < N+1; i++) {
            results[i] = results[i - 1];
            long curGallon = cows[i-1];
            while (curStoreId < M && curGallon > 0) {
                store curStore = stores[curStoreId];
                if (curStore.gallons > curGallon) {
                    results[i] += curGallon * curStore.price;
                    curStore.gallons -= curGallon;
                    curGallon = 0;
                } else {
                    results[i] += curStore.gallons * curStore.price;
                    curGallon -= curStore.gallons;
                    curStoreId++;
                }
            }
        }
        
        long[] renterPrefix = new long[R+1];
        for (int i = 1; i <= R; i++) {
            renterPrefix[i] = renterPrefix[i-1] + renters[i-1];
            if (N-i < 0) {
                break;
            }
            results[N-i] += renterPrefix[i];
        }
//        System.out.println(Arrays.toString(renterPrefix));
//        System.out.println(Arrays.toString(results));
        
        long out = 0;
        for (int i = 0; i < N+1; i++) {
            out = Math.max(results[i], out);
        }
        System.out.println(out);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
		pw.println(out);
		pw.close();
	}
	
}