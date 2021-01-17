import java.io.*;
import java.util.*;

class person implements Comparable<person> {
    public int start;
    public int end;
    public int order;
    
    public person (int start, int end, int order) {
        this.start = start;
        this.end = end;
        this.order = order;
    }
    
    public String toString() {
        return "(" + start + ", " + end + ")";
    }
    
    public int compareTo(person other) {
        return this.start - other.start;
    }
}

public class roomallocation {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		person[] people = new person[N];
		
		for (int i = 0; i < N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
		    people[i] = new person(a, b, i);
		}
		
		Arrays.sort(people);
		
		PriorityQueue<person> pq = new PriorityQueue<>((a, b) -> a.end - b.end);
		
		int[] rooms = new int[N];
		int maxRooms = 0;
		for (person aPerson : people) {
		    if (pq.isEmpty()) {
		        pq.add(aPerson);
		        rooms[aPerson.order] = 1;
		    } else {
		        person leaving = pq.peek();
		        if (leaving.end < aPerson.start) {
		            pq.poll();
		            pq.add(aPerson);
		            rooms[aPerson.order] = rooms[leaving.order];
		        } else {
		            pq.add(aPerson);
		            rooms[aPerson.order] = pq.size();
		        }
		    }
		    maxRooms = Math.max(maxRooms, pq.size());
//		    System.out.println(pq);
		}
		
//		System.out.println(Arrays.toString(people));
		System.out.println(maxRooms);
		for (int i = 0; i < N - 1; i++) {
		    System.out.print(rooms[i] + " ");
		}
		System.out.print(rooms[N-1] + "\n");
//		System.out.println(Arrays.toString(rooms));
	
		
		
		
		
		
	}
}