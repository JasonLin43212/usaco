import java.io.*;
import java.util.*;

class movie implements Comparable<movie> {
    public int start;
    public int end;
    
    public movie(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    public String toString() {
        return "(start: " + start + ", end: " + end + ")"; 
    }
    
    public int compareTo(movie other) {
        return this.end - other.end;
    }
}

class multiset {
    public TreeMap<Integer, Integer> map;
    public int numMovies;
    public int numPeople;
    
    public multiset() {
        map = new TreeMap<>();
        numMovies = 0;
        numPeople = 0;
    }
    
    public void add(int n) {
        if (map.containsKey(n)) {
            map.put(n, map.get(n) + 1);
        } else {
            map.put(n, 1);
        }
        numMovies++;
        numPeople++;
    }
    
    public void remove(int n) {
        map.put(n, map.get(n)-1);
        
        if (map.get(n) == 0) {
            map.remove(n);
        }
        numPeople--;
    }
    
    public String toString() {
        return map.toString();
    }
}

public class moviefestival2 {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		movie[] movies = new movie[N];
		for (int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    int s = Integer.parseInt(st.nextToken());
		    int t = Integer.parseInt(st.nextToken());
		    movies[i] = new movie(s, t);
		}
		
		Arrays.sort(movies);
//		System.out.println(Arrays.toString(movies));
		multiset curMovies = new multiset();
		
		for (int i = 0; i < N; i++) {
		    movie cur = movies[i];
		    Integer nextPerson = curMovies.map.floorKey(cur.start);
		    if (nextPerson == null) {
		        if (curMovies.numPeople < K) {
		            curMovies.add(cur.end);
		        }
		    } else {
		        curMovies.add(cur.end);
                curMovies.remove(nextPerson);
		    }
//		    System.out.println(cur + " " + curMovies);
		}
		
		System.out.println(curMovies.numMovies);
	}
}