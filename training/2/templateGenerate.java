import java.io.*;
import java.util.*;

public class templateGenerate {

    public static String getTemplateString(String problem) {
        String output = "/*\nID: jasonli7\nLANG: JAVA\nTASK: " + problem + "\n*/\n";
        output += "import java.io.*;\nimport java.util.*;\n\n";
        output += "public class " + problem + " {\n";
        output += "\tpublic static void main (String [] args) throws IOException {\n" +
                  "\t\tString fileName = \"" + problem + "\";\n" +
                  "\t\tBufferedReader br = new BufferedReader(new FileReader(fileName + \".in\"));\n" +
                  "\t\tPrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + \".out\")));\n";
        output += "\t\tStringTokenizer st = new StringTokenizer(br.readLine());\n" +
                  "\t\tpw.println(\"solution\");\n" +
                  "\t\tpw.close();\n\t}\n}";
        return output;
    }

    public static void main (String [] args) throws IOException {
        System.out.println("Enter the name of problem: ");
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        //Creating a File object
        File file = new File(path);
        File in = new File(path + "/" + path + ".in");
        //Creating the directory
        boolean bool = file.mkdir();
        if(bool){
            System.out.println("Directory created successfully");
        }else{
            System.out.println("Sorry couldn’t create specified directory");
        }

        boolean bool2 = in.createNewFile();
        if(bool2){
            System.out.println("Java File created successfully");
        }else{
            System.out.println("Sorry couldn’t create specified file");
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path + "/" + path + ".java")));
        pw.print(getTemplateString(path));
        pw.close();
    }
}
