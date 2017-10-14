package inputProcess;

import java.io.*;
import java.util.ArrayList;

public class objectManagerment {
    private String codeOfObject;
    private String nameObject;  // name of class or interfac
    private String typeOfObject; // = "class" or "interface"
    private String accessLevel; // = "public" or "package"
    private String isAbstract; // is class abstract
    private ArrayList<String> extendsList;
    private ArrayList<String> implementsList;
    private ArrayList<String> atributesList;
    private ArrayList<String> methodsList;

    public objectManagerment() {
        codeOfObject = new String();
        nameObject = new String();
        typeOfObject = new String();
        accessLevel = new String("package"); // default = package
        isAbstract = new String();
        extendsList = new ArrayList<String>();
        implementsList = new ArrayList<String>();
        atributesList = new ArrayList<String> ();
        methodsList = new ArrayList<String> ();
    }

    public void setCodeOfObject(String codeOfObject) { this.codeOfObject = codeOfObject; }
    public void setNameObject(String nameObject) { this.nameObject = nameObject; }
    public void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }
    public void setTypeOfObject(String typeOfObject) { this.typeOfObject = typeOfObject; }
    public void setIsAbstract(String isAbstract) { this.isAbstract = isAbstract; }
    public void addExtendsTo(String extendsTo) { extendsList.add(extendsTo); }
    public void addImplementsTo(String implementsTo) { implementsList.add(implementsTo); }
    public void addAtribute(String atribute) { atributesList.add(atribute); }
    public void addMethod(String method) { methodsList.add(method); }

    public void printObject(String outputName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileOutputStream(outputName, true));
        writer.println("accessLevel: " + accessLevel);
        writer.println("abstract: " + isAbstract);
        writer.println("type: " + typeOfObject);
        writer.println("name: " + nameObject);

        writer.print("extends: ");
        for (String s : extendsList)
            writer.print(s + ", ");

        writer.print("\nimplements: ");
        for (String s : implementsList)
            writer.print(s + ", ");

        writer.println("\natributes:");
        for (String s : atributesList)
             writer.println("\t" + s);

        writer.println("methods:");
        for (String s : methodsList)
            writer.println("\t" + s);
        writer.close();
    }

    public String findPartOfString(String s, char begin, char end, int fromIndex) {
        int beginIndex = s.indexOf(begin, fromIndex);
        int endIndex = s.indexOf(end, beginIndex + 1);
        return s.substring(beginIndex + 1, endIndex);
    }

    public String findIsAbstract(String s, int lastIndex) {
        if ( s.lastIndexOf("abstract", lastIndex) != -1)
            return "true";
        return "false";
    }

    public String findAccessLevel(String s, int lastIndex) {
        int i;
        if ( (i = s.lastIndexOf("public", lastIndex) ) != -1)
            return "public";
        return "package";
    }

    public void deleteInsideMethods() {
        while (true) {
            int begin = codeOfObject.indexOf("{");
            int begin2 = codeOfObject.indexOf("{", begin + 1);
            int end = codeOfObject.indexOf("}");
            while (begin2 < end) {
                begin2 = codeOfObject.indexOf("{", begin2 + 1);
                if (begin2 == -1) break;
                end = codeOfObject.indexOf("}", end + 1);
            }
            if (begin == -1) return;
            String target = codeOfObject.substring(begin, end + 1);
            codeOfObject = codeOfObject.replace(target, "");
        }
    }

    public String instruction() {
        String s = codeOfObject.substring(0, codeOfObject.indexOf(";"));
        codeOfObject =  codeOfObject.substring(codeOfObject.indexOf(";") + 1);
        return s;
    }

    public void stringParsing() {
        int beginClass = codeOfObject.indexOf("{");
        int endClass = codeOfObject.lastIndexOf("}");
        int index1 = codeOfObject.indexOf("class");
        int index2 = codeOfObject.indexOf("interface");
        int _index;
        if (index1 != -1 && index1 < beginClass) {
            setTypeOfObject("class");
            _index = index1;
        } else {
            setTypeOfObject("interface");
            _index = index2;
        }
        setNameObject( findPartOfString(codeOfObject, ' ', ' ', _index) );
        setAccessLevel( findAccessLevel(codeOfObject, _index));
        setIsAbstract( findIsAbstract(codeOfObject, _index) );

        if ( (_index = codeOfObject.lastIndexOf("extends", beginClass)) != -1) {
            addExtendsTo( findPartOfString(codeOfObject, ' ', ' ', _index) );
        }

        if ( (_index = codeOfObject.lastIndexOf("implements", beginClass)) != -1) {
            addImplementsTo( findPartOfString(codeOfObject, ' ', '{', _index));
        }

        codeOfObject = codeOfObject.substring(beginClass + 1, endClass);
        System.out.println(codeOfObject);
        deleteInsideMethods();
        while (codeOfObject.length() > 0) {
            String str = instruction();
            if (checkInstruction(str))
                atributesList.add(str);
            else
                methodsList.add(str);
        }
    }

    public boolean checkInstruction(String s) { // true is atribute, false is method
        if (s.indexOf("(") == -1)
            return true;
        if (s.indexOf("=") == -1)
            return false;
        if (s.indexOf("(") > s.indexOf("="))
            return true;
        return false;
    }
}
