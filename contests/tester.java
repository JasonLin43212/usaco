import java.io.*;
import java.util.*;

public class tester {

    public static Map<String, Map<String, String>> numToFilename = new HashMap<>();
    public static String problemName;
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("You need one argument: problem name");
            return;
        }
        
        problemName = args[0];
        
        try {
            File[] files = new File("tests").listFiles();
            for (File file: files) {
                if (!file.isDirectory()) {
                    String[] filenameList = file.getName().split("\\.");
                    if (numToFilename.containsKey(filenameList[0])) {
                        numToFilename.get(filenameList[0]).put(filenameList[1], file.toString());
                    } else {
                        Map<String, String> filenames = new HashMap<>();
                        filenames.put(filenameList[1], file.toString());
                        numToFilename.put(filenameList[0], filenames);
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Make sure to have a folder named 'tests' with test files");
            return;
        }
        
        System.out.println("Show all output? (y/n):");
        Scanner sc = new Scanner(System.in);
        boolean verbose = sc.next().equals("y") ? true : false;
        
        System.out.println("Enter test case number (enter 0 to test all): ");
        sc = new Scanner(System.in);
        String num = sc.next();
        
        try {
            if (num.equals("0")) { // Test all
                for (String number : numToFilename.keySet()) {
                    test(number, verbose);
                }
            } else { // Test one
                test(num, verbose);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static void test(String problemNum, boolean verbose) throws FileNotFoundException, IOException {
        if (numToFilename.containsKey(problemNum)) {
            BufferedReader br = new BufferedReader(new FileReader(numToFilename.get(problemNum).get("in")));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".in")));
            
            String line = br.readLine();
            while(line != null) {
                pw.println(line);
                line = br.readLine();
            }
            pw.close();
            
            try {
                Process pro = Runtime.getRuntime().exec("make");
                
                if (verbose) {
                    BufferedReader out = new BufferedReader(
                            new InputStreamReader(pro.getInputStream()));
                    String processLine = null;
                    while ((processLine = out.readLine()) != null) {
                        System.out.println(processLine);
                    }
                }
                pro.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            BufferedReader br2 = new BufferedReader(new FileReader(problemName + ".out"));
            BufferedReader br3 = new BufferedReader(new FileReader(numToFilename.get(problemNum).get("out")));
            
            String outLine = br2.readLine();
            String solLine = br3.readLine();
            boolean pass = true;
            while (solLine != null || outLine != null) {
                if ((outLine == null || solLine == null) ||
                    !outLine.equals(solLine)) {
                    pass = false;
                    break;
                }
                outLine = br2.readLine();
                solLine = br3.readLine();
            }
            
            if (pass) {
                System.out.println("Test case #" + problemNum + "... OK\n");
            } else {
                System.out.println("Test case #" + problemNum + "... FAILED\n");
            }
            
        } else {
            System.out.println("There is no test case #" + problemNum);
        }
    }
    
}