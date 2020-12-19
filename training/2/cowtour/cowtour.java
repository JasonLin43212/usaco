/*
ID: jasonli7
LANG: JAVA
TASK: cowtour
*/
import java.io.*;
import java.util.*;

public class cowtour {
    
    public static int N;
    public static int[][] locs;
    public static double[][] connects;
    
    public static double[][] dists; //dists[a][b] is shortest distance from a to b
    public static double[] longestShort;
    
    public static List<List<Integer>> components = new ArrayList<>();
    
    public static void getComponents() {
        int[] seen = new int[N];
        for (int i = 0; i < N; i++) {
            if (seen[i] == 1) {
                continue;
            }
            List<Integer> queue = new ArrayList<>();
            queue.add(i);
            
            List<Integer> component = new ArrayList<>();
            while (queue.size() > 0) {
                int next = queue.remove(0);
                if (seen[next] == 1) {
                    continue;
                }
                seen[next] = 1;
                component.add(next);
                for (int j = 0; j < N; j++) {
                    double canGo = connects[next][j];
                    if (canGo > 0 && seen[j] == 0) {
                        queue.add(j);
                    }
                }
            }
            components.add(component);
        }
    }
    
    public static double getDist(int first, int second) {
        double dx = (double) (locs[first][0] - locs[second][0]);
        double dy = (double) (locs[first][1] - locs[second][1]);
        
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    public static void getShortestDists(int a) {
        boolean[] seen = new boolean[N];
        dists[a][a] = 0;
        
        for (int i = 0; i < N; i++) {
            double min = Double.MAX_VALUE;
            int minVertex = -1;
            for (int j = 0; j < N; j++) {
                double estDist = dists[a][j];
                if (!seen[j] && estDist < min) {
                    min = estDist;
                    minVertex = j;
                }
            }
            if (minVertex == -1) {
                break;
            }
            
            seen[minVertex] = true;
            for (int j = 0; j < N; j++) {
                if (connects[minVertex][j] >= 0) {
                    if (dists[a][minVertex] + connects[minVertex][j] <
                        dists[a][j]) {
                        dists[a][j] = dists[a][minVertex] + connects[minVertex][j];
                    }
                }
            }
        }
    }
    
    public static void printDists() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(dists[i]));
        }
    }
    
    public static double solve() {
        getComponents();
        for (int i = 0; i < N; i++) {
            getShortestDists(i);
            double longShort = 0;
            for (int j = 0; j < N; j++) {
                if (dists[i][j] != Double.POSITIVE_INFINITY) {
                    longShort = Math.max(longShort, dists[i][j]);
                }
            }
            longestShort[i] = longShort;
        }
//        System.out.println(components);
//        printDists();
//        System.out.println(Arrays.toString(longestShort));
        
        double minDiam = Double.POSITIVE_INFINITY;
        for (int i = 0; i < components.size(); i++) {
            for (int j = i + 1; j < components.size(); j++) {
                List<Integer> firstComp = components.get(i);
                List<Integer> secondComp = components.get(j);
                
                for (int a = 0; a < firstComp.size(); a++) {
                    int first = firstComp.get(a);
                    for (int b = 0; b < secondComp.size(); b++) {
                        int second = secondComp.get(b);
                        double curDiam = longestShort[first] + longestShort[second] + getDist(first, second);
//                        System.out.println(first + " " + second + " " + longestShort[first] + " " + longestShort[second] + " " + getDist(first, second));
                        minDiam = Math.min(minDiam, curDiam);
                    }
                }
            }
        }
        
        return minDiam;
    }
    
	public static void main (String [] args) throws IOException {
		String fileName = "cowtour";
		BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
		N = Integer.parseInt(br.readLine());
		
		locs = new int[N][2];
		connects = new double[N][N];
		dists = new double[N][N];
		longestShort = new double[N];
		
		for (int i = 0; i < N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    locs[i][0] = Integer.parseInt(st.nextToken());
		    locs[i][1] = Integer.parseInt(st.nextToken());
		    longestShort[i] = -1;
		}
		
		for (int i = 0; i < N; i++) {
		    String line = br.readLine();
		    for (int j = 0; j < N; j++) {
		        dists[i][j] = Double.POSITIVE_INFINITY;
		        if (line.charAt(j) == '1') {
		            connects[i][j] = getDist(i, j);
		        } else {
		            connects[i][j] = -1;
		        }
		    }
		}
		
		double solution = solve();
		double printSol = Math.round(solution * 1000000) / 1000000.;
		String strSol = printSol + "";
		int extraZero = 6 - (strSol.length() - strSol.indexOf('.') - 1);
		for (int i = 0; i < extraZero; i++) {
		    strSol += "0";
		}
		pw.println(strSol);
		pw.close();
	}
}