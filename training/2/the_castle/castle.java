/*
ID: jasonli7
LANG: JAVA
TASK: castle
*/
import java.io.*;
import java.util.*;

class Cell {
    public int number;
    public boolean seen;

    public Cell() {
        number = -1;
        seen = false;
    }
}

public class castle {

    public static void numberCell(int r, int c, int roomNum, Cell[][] cells, int[][] castleLayout) {
        int wallNum = castleLayout[r][c];
        if (cells[r][c].seen) {
            return;
        }

        cells[r][c].number = roomNum;
        cells[r][c].seen = true;

        int width = castleLayout[0].length;
        int height = castleLayout.length;

        if (c > 0 && (wallNum & 1) == 0) { // If the binary & gives all zeros, that means that the wall is not there.
            numberCell(r, c - 1, roomNum, cells, castleLayout);
        }
        if (r > 0 && (wallNum & 2) == 0) {
            numberCell(r - 1, c, roomNum, cells, castleLayout);
        }
        if (c < width - 1  && (wallNum & 4) == 0) {
            numberCell(r, c + 1, roomNum, cells, castleLayout);
        }
        if (r < height - 1 && (wallNum & 8) == 0) {
            numberCell(r + 1, c, roomNum, cells, castleLayout);
        }
    }

    public static int[] solve(int M, int N, int[][] castleLayout) {
        /* Label each cell with a component number */
        Cell[][] cells = new Cell[N][M];
        for (int r=0; r<N; r++) {
            Cell[] row = new Cell[M];
            for (int c=0; c<M; c++) {
                row[c] = new Cell();
            }
            cells[r] = row;
        }

        int curRoomNum = 0;

        for (int r=0; r<N; r++) {
            for (int c=0; c<M; c++) {
                if (cells[r][c].seen) {
                    continue;
                }

                numberCell(r, c, curRoomNum, cells, castleLayout);
                curRoomNum++;
            }
        }

        // for (Cell[] cellRow: cells) {
        //     for (Cell aCell: cellRow) {
        //         System.out.print(aCell.number + " ");
        //     }
        //     System.out.print("\n");
        // }
        /* Calculate the max room size */
        int[] roomSizes = new int[curRoomNum];
        for (Cell[] cellRow: cells) {
            for (Cell aCell: cellRow) {
                roomSizes[aCell.number] += 1;
            }
        }
        int maxRoomSize = 0;
        for (int size: roomSizes) {
            maxRoomSize = Math.max(maxRoomSize, size);
        }

        // Loop through entire cells and find that wall
        int maxDoubleRoomSize = 0;
        int roomR = -1;
        int roomC = -1;
        int dir = -1; // 0 is N and 1 is E
        for (int c = 0; c < M; c++) {
            for (int r = N - 1; r >= 0; r--) {
                int thisRoomNum = cells[r][c].number;
                if (r > 0) {
                    int northRoomNum = cells[r-1][c].number;
                    if (thisRoomNum != northRoomNum) {
                        int sumArea = roomSizes[thisRoomNum] + roomSizes[northRoomNum];
                        if (sumArea > maxDoubleRoomSize) {
                            maxDoubleRoomSize = sumArea;
                            roomR = r;
                            roomC = c;
                            dir = 0;
                        }
                    }
                }

                if (c < M - 1) {
                    int eastRoomNum = cells[r][c+1].number;
                    if (thisRoomNum != eastRoomNum) {
                        int sumArea = roomSizes[thisRoomNum] + roomSizes[eastRoomNum];
                        if (sumArea > maxDoubleRoomSize) {
                            maxDoubleRoomSize = sumArea;
                            roomR = r;
                            roomC = c;
                            dir = 1;
                        }
                    }
                }
            }
        }

        int[] solution = new int[] {
            roomSizes.length, maxRoomSize, maxDoubleRoomSize, roomR + 1, roomC + 1, dir
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

        int[] solution = solve(M, N, castleLayout);
        // System.out.println("SOLUTION: " + Arrays.toString(solution));

        for (int i=0; i<3; i++) {
            pw.println(solution[i]);
        }
        for (int i=3; i<5; i++) {
            pw.print(solution[i] + " ");
        }
        if (solution[5] == 0) {
            pw.print("N\n");
        } else {
            pw.print("E\n");
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
