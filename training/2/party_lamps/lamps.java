/*
ID: jasonli7
LANG: JAVA
TASK: lamps
*/
import java.io.*;
import java.util.*;

class lamps {

    static int N;
    static int C;
    static ArrayList<Integer> onLamps = new ArrayList<>();
    static ArrayList<Integer> offLamps = new ArrayList<>();

    public static Integer[] generateLamps(int buttons) {
        Integer[] lamps = new Integer[N];
        for (int i=0; i<lamps.length; i++) {
            lamps[i] = 1;
        }

        if ( ((buttons >> 0) & 1) == 1 ) { // Fourth button
            for (int i=0; i<lamps.length; i+=3) {
                lamps[i] = 1 - lamps[i];
            }
        }

        if ( ((buttons >> 1) & 1) == 1 ) { // Third button
            for (int i=1; i<lamps.length; i+=2) {
                lamps[i] = 1 - lamps[i];
            }
        }

        if ( ((buttons >> 2) & 1) == 1 ) { // Fourth button
            for (int i=0; i<lamps.length; i+=2) {
                lamps[i] = 1 - lamps[i];
            }
        }

        if ( ((buttons >> 3) & 1) == 1 ) { // Fourth button
            for (int i=0; i<lamps.length; i++) {
                lamps[i] = 1 - lamps[i];
            }
        }
        return lamps;
    }

    public static int[] getClickCombos() {
        // Binary encoding of which buttons are clicked
        if (C == 0) return new int[] {0};
        if (C == 1) return new int[] {1, 2, 4, 8};
        if (C == 2) return new int[] {0, 3, 5, 6, 9, 10, 12};
        if (C % 2 == 0) return new int[] {0, 3, 5, 6, 9, 10, 12, 15};
        return new int[] {1, 2, 4, 8, 7, 11, 13, 14};
    }

    public static ArrayList<Integer[]> solve() {
        int[] clickCombos = getClickCombos();
        ArrayList<Integer[]> possibleFromClicks = new ArrayList<>();
        ArrayList<Integer[]> solutions = new ArrayList<>();

        for (int i=0; i<clickCombos.length; i++) {
            possibleFromClicks.add(generateLamps(clickCombos[i]));
        }

        for (Integer[] possibleClick: possibleFromClicks) {
            // System.out.println("possible " + Arrays.toString(possibleClick));
            boolean isValid = true;
            for (int onLamp: onLamps) {
                // System.out.println("onlamp: " + onLamp);
                if (possibleClick[onLamp - 1].intValue() == 0) {
                    // System.out.println("off when supposed to be on");
                    isValid = false;
                }
            }

            for (int offLamp: offLamps) {
                // System.out.println("offlamp: " + offLamp);
                // System.out.println("value at offLamp: " + possibleClick[offLamp - 1] );
                // System.out.println("boolean offLamp: " + (possibleClick[offLamp - 1].intValue() == 1) );
                if (possibleClick[offLamp - 1].intValue() == 1) {
                    isValid = false;
                }
            }

            if (isValid) {
                solutions.add(possibleClick);
            }
        }

        solutions.sort((a, b) -> {
            for (int i=0; i<a.length; i++) {
                if (a[i] != b[i]) {
                    return a[i] - b[i];
                }
            }
            return 0;
        });
        return solutions;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "lamps";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        N = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int onLamp = Integer.parseInt(st.nextToken());
        while (onLamp != -1) {
            onLamps.add(onLamp);
            onLamp = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int offLamp = Integer.parseInt(st.nextToken());
        while (offLamp != -1) {
            offLamps.add(offLamp);
            offLamp = Integer.parseInt(st.nextToken());
        }

        // System.out.println("N: " + N + " C: " + C + " on: " + onLamps.toString() + " off: " + offLamps.toString());
        ArrayList<Integer[]> solutions = solve();
        // for (Integer[] sol: solutions) {
        //     System.out.println(Arrays.toString(sol));
        // }
        if (solutions.size() == 0) {
            pw.println("IMPOSSIBLE");

        } else {
            for (Integer[] sol: solutions) {
                for (Integer num: sol) {
                    pw.print(num);
                }
                pw.print("\n");
            }
        }

        pw.close();
    }
}
