/*
ID: jasonli7
LANG: JAVA
TASK: runround
*/
import java.io.*;
import java.util.*;

class runround {

    static int N;

    public static void main(String[] args) throws IOException {
        String fileName = "runround";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        N = Integer.parseInt(br.readLine());
        System.out.println(N);



        pw.close();
    }
}
