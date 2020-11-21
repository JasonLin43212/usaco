/*
ID: jasonli7
LANG: JAVA
TASK: zerosum
*/
import java.io.*;
import java.util.*;

enum Symbol {
    PLUS,
    SUBTRACT,
    BLANK
}

public class zerosum {

    static int M;
    static ArrayList<ArrayList<Symbol>> solutions = new ArrayList<>();
    static ArrayList<ArrayList<Symbol>> allCombos = new ArrayList<>();

    public static void generateAllCombos() {
        for (int i=1; i<M-1; i++) {
            ArrayList<ArrayList<Symbol>> nextCombos = new ArrayList<>();
            for (ArrayList<Symbol> combo: allCombos) {
                for (Symbol sym: Symbol.values()) {
                    ArrayList<Symbol> newCombo = new ArrayList<>(combo);
                    newCombo.add(sym);
                    nextCombos.add(newCombo);
                }
            }
            allCombos = nextCombos;
        }
    }

    public static boolean isValid(ArrayList<Symbol> combo) {
        int sum = 0;
        int curNum = 0;
        int numBlanks = 0;
        // System.out.println("COMBO: " + combo);
        for (int i=combo.size()-1; i>=0; i--) {
            Symbol curSym = combo.get(i);
            if (curSym == Symbol.PLUS) {
                sum += ((int) Math.pow(10, numBlanks)) * (i + 2) + curNum;
                // System.out.println("plus! now sum is " + sum);
                curNum = 0;
                numBlanks = 0;
            } else if (curSym == Symbol.SUBTRACT) {
                sum -= ((int) Math.pow(10, numBlanks)) * (i + 2) + curNum;
                // System.out.println("subtract! now sum is " + sum);
                curNum = 0;
                numBlanks = 0;
            } else if (curSym == Symbol.BLANK) {
                curNum += ((int) Math.pow(10, numBlanks)) * (i + 2);
                numBlanks++;
                // System.out.println("blank! now sum is " + sum);

            }
            // System.out.println(sum);
        }
        if (numBlanks > 0) {
            sum += ((int) Math.pow(10, numBlanks)) + curNum;
        } else {
            sum += 1;
        }
        // System.out.println("FINAL SUM: " + sum);
        // System.out.println("\n");
        return sum == 0;
    }

    public static void solve() {
        for (Symbol symb: Symbol.values()) {
            ArrayList<Symbol> symbList = new ArrayList<>();
            symbList.add(symb);
            allCombos.add(symbList);
        }
        generateAllCombos();

        for (ArrayList<Symbol> combo: allCombos) {
            if (isValid(combo)) {
                solutions.add(combo);
            }
        }
    }

    public static void main (String [] args) throws IOException {
        String fileName = "zerosum";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        M = Integer.parseInt(br.readLine());
        solve();

        ArrayList<String> outputSol = new ArrayList<>();

        for (ArrayList<Symbol> sol: solutions) {
            String solString = "";
            for (int i = 1; i < M ; i++) {
                solString += i;
                if (sol.get(i-1) == Symbol.PLUS) {
                    solString += "+";
                } else if (sol.get(i-1) == Symbol.SUBTRACT) {
                    solString += "-";
                } else if (sol.get(i-1) == Symbol.BLANK) {
                    solString += " ";
                }
            }
            solString += M;
            outputSol.add(solString);
        }

        outputSol.sort((a, b) -> a.compareTo(b));

        for (String solString: outputSol) {
            pw.println(solString);
        }
        pw.close();
    }
}
