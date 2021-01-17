import java.io.*;
import java.util.*;

class movie implements Comparable<movie> {
    public int start;
    public int end;
    
    public movie(int s, int e) {
        this.start = s;
        this.end = e;
    }
    
    public int compareTo(movie other) {
        return this.end - other.end;
    }
    
    @Override
    public String toString() {
        return "(" + start + ", " + end + ")";
    }
}

public class moviefestival {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		movie[] movies = new movie[N];
		
		for (int i = 0; i < N; i++) {		    
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int s = Integer.parseInt(st.nextToken());
	        int e = Integer.parseInt(st.nextToken());
	        movies[i] = new movie(s, e);
		}
		Arrays.sort(movies);
//		System.out.println(Arrays.toString(movies));
		int numMovies = 0;
		int endTime = 0;
		for (int i = 0; i < N; i++) {
		    movie curMovie = movies[i];
		    if (curMovie.start >= endTime) {
		        numMovies++;
		        endTime = curMovie.end;
		    }
		}
		System.out.println(numMovies);
	}
}