package inputProcess;

import java.io.*;
import java.util.Scanner;

public class stringProcessing{

    private String javaCode;

    public stringProcessing() {
        javaCode = new String();
    }

    public void readInput(String inputName) throws IOException {
        Scanner input = new Scanner(new File(inputName));
        while(input.hasNextLine()) {
            String s = input.nextLine();
            s = deleteLineComments(s);
            javaCode += s;
        }
    }

    public String deleteLineComments(String s) {
        return (s.replaceAll("\\/\\/.*", ""));
    }

    public void deleteComments() {
        javaCode = javaCode.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", "");
    }

    public void printCode(String outputName) throws IOException {
        PrintWriter writer = new PrintWriter(outputName, "UTF-8");
        //writer.println(javaCode);
        writer.close();
    }

    public void deleteSpaces() {
        javaCode = javaCode.trim(); // delete begin spaces and end spaces
        javaCode = javaCode.replaceAll("\\}", "};"); // insert ";" after "}"
        javaCode = javaCode.replaceAll("\\s+", " ");  // delete all extra spaces
        javaCode = javaCode.replaceAll("(\\{ )", " {"); // insert space front "{"
        javaCode = javaCode.replaceAll("(; )\\b", ";"); // delete space behind ";"
        javaCode = javaCode.replaceAll("(, )\\b", ","); // delete space behind ","
        javaCode = javaCode.replaceAll("\\s+", " ");  // delete all extra spaces
    }

    public void deleteKeyWord(String keyWord) {
        while(javaCode.indexOf(keyWord) > -1) {
            int startIndex = javaCode.indexOf(keyWord);
            int endIndex = javaCode.indexOf(";", startIndex);
            String toBeReplaced = javaCode.substring(startIndex, endIndex + 1);
            javaCode = javaCode.replace(toBeReplaced, "");
        }
    }

    public String findAClass() {
        return javaCode;
    }
}