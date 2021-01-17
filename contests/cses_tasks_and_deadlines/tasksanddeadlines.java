import java.io.*;
import java.util.*;

class task implements Comparable<task> {
    public long duration;
    public long deadline;
    
    public task(int duration, int deadline) {
        this.duration = (long)duration;
        this.deadline = (long)deadline;
    }
    
    public String toString() {
        return "( duration: " + duration + ", " + deadline + ")";
    }
    
    public int compareTo(task other) {
        return (int)(this.duration - other.duration);
    }
}

public class tasksanddeadlines {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		task[] tasks = new task[N];
		
		for (int i = 0; i < N; i++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
            int dur = Integer.parseInt(st.nextToken());
            int dead = Integer.parseInt(st.nextToken());
            tasks[i] = new task(dur, dead);
		}
		
		Arrays.sort(tasks);
		
		long points = 0;
		long time = 0;
		for (int i = 0; i < N; i++) {
		    task curTask = tasks[i];
		    time += curTask.duration;
		    points += curTask.deadline - time;
		}
		
		System.out.println(points);
	}
}