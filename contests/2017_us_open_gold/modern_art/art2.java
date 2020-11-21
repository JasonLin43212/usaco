import java.io.*;
import java.util.*;

class art2 {

    static int N;
    static int[] painting;

    public static void main(String[] args) throws IOException {
        String fileName = "art2";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        N = Integer.parseInt(br.readLine());
        painting = new int[N];
        for (int i=0; i<N; i++) {
            painting[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(N +" " + Arrays.toString(painting));
        /**
        * Idea: loop through the list once and store the intervals in an array of length (N+1) x 2
        * where the first index is the color and the list of two is the start and end interval
        * Initiate it to -1
        *
        * Then you take each interval and add one to a list. the max of the list is the interval

        Bad Case: 1 4 1 4 1
        Base Case 1 1 0 1 1 (0 is no color)
        Not sure how to detect this
        */

        pw.close();
    }
}
