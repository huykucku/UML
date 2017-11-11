import java.io.*;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class StringParsing{

	private StringBuilder preCode;
	private String parsedCode;
	JFileChooser fileChooser = new JFileChooser();
		
    public StringParsing() {
		preCode = new StringBuilder();
		parsedCode = new String();
    }
	
	public void parsing() {
		parsedCode = preCode.substring(0);
		parsedCode = deleteComments(parsedCode);
		parsedCode = deleteBlockString(parsedCode);
		parsedCode = deleteKeyWord(parsedCode, "package");
		parsedCode = deleteKeyWord(parsedCode, "import");
		parsedCode = deleteSpaces(parsedCode);
		
	}
	
	public String getParsedCode() {
		return new String(parsedCode);
	}
	
 	public String openFile() throws Exception {
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
				String str = input.nextLine();
				//str = deleteBlockString(str);
				str = deleteLineComments(str);
                preCode.append(str);
            }
            input.close();
			return file.getName();
        }   
		return "can't load file";
	}
	
	private String deleteBlockString(String javaCode) {
		while ( javaCode.indexOf("\"") >= 0 ) {
			int begin = javaCode.indexOf("\"");
			int end = javaCode.indexOf("\"", begin+1);
			String str = javaCode.substring(begin, end+1);
			javaCode = javaCode.replace(str, "");
		}
		return javaCode;
	}
	
    private String deleteLineComments(String javaCode) {
        return ( javaCode.replaceAll("\\/\\/.*", "") );
    }

    private String deleteComments(String javaCode) {
        return ( javaCode.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", "") );
    }

    private String deleteSpaces(String javaCode) {
        javaCode = javaCode.trim(); // delete begin spaces and end spaces
		javaCode = javaCode.replaceAll("\\,", " "); // replace , = " "
        javaCode = javaCode.replaceAll("\\}", "};"); // insert ";" after "}"
        javaCode = javaCode.replaceAll("\\s+", " ");  // delete all extra spaces
        javaCode = javaCode.replaceAll("(\\{ )", " {"); // insert space front "{"
        javaCode = javaCode.replaceAll("(; )\\b", ";"); // delete space behind ";"
        javaCode = javaCode.replaceAll("(, )\\b", ","); // delete space behind ","
        javaCode = javaCode.replaceAll("\\s+", " ");  // delete all extra spaces
		return javaCode;
    }

    private String deleteKeyWord(String javaCode, String keyWord) {
        while(javaCode.indexOf(keyWord) > -1) {
            int startIndex = javaCode.indexOf(keyWord);
            int endIndex = javaCode.indexOf(";", startIndex);
            String toBeReplaced = javaCode.substring(startIndex, endIndex + 1);
            javaCode = javaCode.replace(toBeReplaced, "");
        }
		return javaCode;
    }
}