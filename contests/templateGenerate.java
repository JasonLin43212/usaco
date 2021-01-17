import java.io.*;
import java.util.*;

public class templateGenerate {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static String getTemplateString(String problem) {
        String output = "import java.io.*;\nimport java.util.*;\n\n";
        output += "public class " + problem + " {\n";
        output += "\tpublic static void main (String [] args) throws IOException {\n" +
                  "\t\tBufferedReader br = new BufferedReader(new FileReader(\""+ problem  +".in\"));\n";
        output += "\t\tStringTokenizer st = new StringTokenizer(br.readLine());\n" +
                  "\t\tPrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(\""+ problem +".out\")));\n"+
                  "\t\tpw.println(\"solution\");\n" +
                  "\t\tpw.close();\n\t}\n}";
        return output;
    }
    
    public static String getMakeFile(String problem) {
        String output = "all: run\n";
        output += "\nrun:\n";
        output += "\tjavac " + problem + ".java; java " + problem;
        output += "\nstd:\n";
        output += "\tjavac " + problem + ".java; java " + problem + " < " + problem + ".in";
        output += "\ntest:\n"; 
        output += "\tjava -cp ../ tester " + problem;
        return output;
    }

    public static void main (String [] args) throws IOException {
        System.out.println("Enter the name of problem: ");
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        
        System.out.println("Enter contest id (yyyy_mmm_medal_problem#):");
        Scanner sc2 = new Scanner(System.in);
        String contestName = sc2.next();
        //Creating a File object
        File folder = new File(contestName);
        File in = new File(folder + "/" + path + ".in");
        File make = new File(folder + "/makefile");
        //Creating the directory
        boolean bool = folder.mkdir();
        if(bool){
            System.out.println(ANSI_GREEN + "Directory created successfully" + ANSI_RESET);
        }else{
            System.out.println(ANSI_RED + "Sorry couldn’t create specified directory"+ ANSI_RESET);
        }

        boolean bool2 = in.createNewFile();
        if(bool2){
            System.out.println(ANSI_GREEN +"Java File created successfully"+ ANSI_RESET);
        }else{
            System.out.println(ANSI_RED + "Sorry couldn’t create specified file"+ ANSI_RESET);
        }
        
        boolean bool3 = make.createNewFile();
        if(bool3){
            System.out.println(ANSI_GREEN +"Makefile created successfully"+ ANSI_RESET);
        }else{
            System.out.println(ANSI_RED + "Sorry couldn’t create makefile"+ ANSI_RESET);
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(folder + "/" + path + ".java")));
        pw.print(getTemplateString(path));
        pw.close();
        
        pw = new PrintWriter(new BufferedWriter(new FileWriter(folder + "/makefile")));
        pw.print(getMakeFile(path));
        pw.close();
        System.out.println("\nPlease type cd " + folder);
    }
}
