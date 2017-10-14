import java.io.*;
import inputProcess.*;

public class Main{
    public static void main(String[] args) {
        stringProcessing inputFile = new stringProcessing();
        objectManagerment objectList = new objectManagerment();
        try {
            inputFile.readInput("input.txt");
            inputFile.deleteComments();
            inputFile.deleteKeyWord("package");
            inputFile.deleteKeyWord("import");
            inputFile.deleteSpaces();
            //inputFile.printCode("output.txt");
            objectList.setCodeOfObject(inputFile.findAClass());
            objectList.stringParsing();
            objectList.printObject("output.txt");
        } catch (IOException e) {}
    }
}