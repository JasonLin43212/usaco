/*
ID: jasonli7
LANG: JAVA
TASK: castle
*/
import java.io.*;
import java.util.*;


public class castle {

    public static ArrayList<Integer[]> getNextDeltas(int wallNum) {
        Integer[][] deltas = new Integer[][]{
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
        };

        boolean[] toRemove = new boolean[4];
        if (wallNum >= 8) {
            wallNum -= 8;
            toRemove[2] = true;
        }

        if (wallNum >= 4) {
            wallNum -= 4;
            toRemove[1] = true;
        }

        if (wallNum >= 2) {
            wallNum -= 2;
            toRemove[0] = true;
        }

        if (wallNum >= 1) {
            wallNum -= 1;
            toRemove[3] = true;
        }

        ArrayList<Integer[]> validDeltas = new ArrayList<Integer[]>();
        for (int i=0; i<4; i++) {
            if (!toRemove[i]) {
                validDeltas.add(deltas[i]);
            }
        }

        return validDeltas;
    }

    public static int[] solve(int M, int N, int[][] castleLayout) {
        /* Label each cell with a component number */
        int[][] componentNums = new int[N][M];
        boolean[][] seen = new boolean[N][M];

        int curComponentNum = 0;

        for (int r=0; r<N; r++) {
            for (int c=0; c<M; c++) {
                if (seen[r][c]) {
                    continue;
                }

                ArrayList<Integer[]> frontier = new ArrayList<Integer[]>();
                frontier.add(new Integer[]{r, c});
                int times = 0;
                while (frontier.size() > 0) {
                    Integer[] curCell = frontier.remove(0);
                    int curR = curCell[0];
                    int curC = curCell[1];
                    int curWallNum = castleLayout[curR][curC];
                    // System.out.println("curCell: "+ curR + " " + curC);
                    if (seen[curR][curC]) {
                        continue;
                    }
                    componentNums[curR][curC] = curComponentNum;
                    seen[curR][curC] = true;

                    ArrayList<Integer[]> deltas = getNextDeltas(curWallNum);
                    for (Integer[] delt: deltas) {
                        /* -1 for r means up and 1 means down
                            -1 for c means left and 1 means right
                        */
                        // System.out.println("delta: " + Arrays.toString(delt));
                        int newR = curR + delt[0];
                        int newC = curC + delt[1];
                        // System.out.println("newCoords" + newR + " " + newC);
                        if (0 <=  newR  && newR < N &&
                            0 <= newC && newC < M &&
                            !seen[newR][newC]) {
                                // System.out.println("added");
                                frontier.add(new Integer[]{newR, newC});
                        }
                    }
                    // for (Integer[] front: frontier) {
                    //     System.out.println("fronter " + Arrays.toString(front));
                    // }
                    // System.out.println("");
                }
                curComponentNum += 1;
            }
        }

        // System.out.println(Arrays.deepToString(componentNums));

        int[] componentSize = new int[curComponentNum];
        for (int r=0; r<N; r++){
            for (int c=0; c<M; c++) {
                componentSize[componentNums[r][c]] += 1;
            }
        }

        ArrayList<Integer[]> sortedSize = new ArrayList<Integer[]>();
        for (int i=0; i<componentSize.length; i++) {
            sortedSize.add(new Integer[]{i, componentSize[i]});
        }
        sortedSize.sort((a, b) -> b[1] - a[1]);

        /*
            Create a directed graph with components as vertex and shared walls (NORTH, EAST)
            as edges
        */

        // Edges are formated as [r, c, 0 for N and 1 for E, other component]
        ArrayList<ArrayList<Integer[]>> componentEdges = new ArrayList<>();
        for (int i=0; i<componentSize.length; i++) {
            ArrayList<Integer[]> edgeList = new ArrayList<>();
            componentEdges.add(edgeList);
        }

        boolean[][] componentToNeighbors = new boolean[componentSize.length][componentSize.length];

        for (int r=0; r<N; r++){
            for (int c=0; c<M; c++) {
                int componentNum = componentNums[r][c];
                int rightC = c + 1;
                int topR = r - 1;
                if (rightC < M) { // Checks the right cell
                    int rightComponentNum = componentNums[r][rightC];
                    if (componentNum != rightComponentNum) {
                        componentEdges.get(componentNum).add(new Integer[]{r, c, 1, rightComponentNum});
                        componentToNeighbors[componentNum][rightComponentNum] = true;
                        componentToNeighbors[rightComponentNum][componentNum] = true;
                    }
                }

                if (topR >= 0) { // Checks the top cell
                    int topComponentNum = componentNums[topR][c];
                    if (componentNum != topComponentNum) {
                        componentEdges.get(componentNum).add(new Integer[]{r, c, 0, topComponentNum});
                        componentToNeighbors[componentNum][topComponentNum] = true;
                        componentToNeighbors[topComponentNum][componentNum] = true;
                    }
                }
            }
        }


        // for (int i=0; i<componentEdges.size(); i++) {
        //     System.out.println("edges for " + i);
        //     for (int j=0; j<componentEdges.get(i).size(); j++) {
        //         System.out.println(Arrays.toString(componentEdges.get(i).get(j)));
        //     }
        // }

        /* Find the two rooms that sum to the biggest number that are also connected */
        int maxArea = 0;
        ArrayList<Integer[]> roomPairs = new ArrayList<>();
        for (int i=0; i<sortedSize.size()-1; i++) {
            for (int j=i+1; j<sortedSize.size(); j++) {
                if (componentToNeighbors[i][j]) { // Are connected by a wall
                    int area = componentSize[i] + componentSize[j];
                    if (area > maxArea) {
                        roomPairs = new ArrayList<>();
                        roomPairs.add(new Integer[]{i, j});
                        maxArea = area;
                    } else if (area == maxArea) {
                        roomPairs.add(new Integer[]{i, j});
                    }
                }
            }
        }

        // for (Integer[] pair: roomPairs) {
        //     System.out.println(Arrays.toString(pair));
        // }

        // System.out.println(maxArea + " " + Arrays.toString(rooms));
        ArrayList<Integer[]> finalWalls = new ArrayList<Integer[]>();
        for (Integer[] roomPair: roomPairs) {
            for (Integer[] edge: componentEdges.get(roomPair[0])) {
                if (edge[3].equals(roomPair[1])) {
                    finalWalls.add(edge);
                }
            }

            for (Integer[] edge: componentEdges.get(roomPair[1])) {
                if (edge[3].equals(roomPair[0])) {
                    finalWalls.add(edge);
                }
            }
        }


        finalWalls.sort((a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            if (a[0] != b[0]) return b[0] - a[0];
            return a[2] - b[2];
        });

        // for (Integer[] edge: finalWalls) {
        //     System.out.println(Arrays.toString(edge));
        // }
        Integer[] breakWall = finalWalls.get(0);

        int[] solution = new int[]{
            componentSize.length, sortedSize.get(0)[1], maxArea, breakWall[0] + 1, breakWall[1] + 1, breakWall[2]
        };
        return solution;
    }

    public static void main (String [] args) throws IOException {
        String fileName = "castle";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] castleLayout = new int[N][M];

        for (int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0; c<M; c++) {
                castleLayout[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        // System.out.println(M + " " + N);
        // System.out.println(Arrays.deepToString(castleLayout));

        // ArrayList<Integer[]> delts = getNextDeltas(9);
        //
        // for (Integer[] aDelt: delts) {
        //     System.out.println(Arrays.toString(aDelt));
        //
        // }

        int[] solution = solve(M, N, castleLayout);

        for (int i=0; i<3; i++) {
            pw.println(solution[i]);
        }
        if (solution[5] == 0) {
            pw.println(solution[3] + " " + solution[4] + " N");
        } else {
            pw.println(solution[3] + " " + solution[4] + " E");
        }

        pw.close();
    }
}

//NOTE:
/*
    Using the binary (&) to get the room numbers. from 1, 2, 4, and 8
    Using a class for each room to number it and instead of having a delta, you just look
    at whether each wall is there or not and if it is not, then also search that area.
    Use double for loop to go through all the walls from left to right and bottom to up so that
    the first room you find with the maxArea will be the correct order.
*/
