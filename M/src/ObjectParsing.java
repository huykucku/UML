import DataStore.*;
import java.util.HashSet;
import java.util.Vector;
		
public class ObjectParsing{
    private String javaCode;
	private Class_info cl;
	private Vector<Member_info> atb;
	private Vector<Member_info> met;
	
	public ObjectParsing() {
		cl = new Class_info();
		atb = new Vector();
		met = new Vector();
	}
    public ObjectParsing(String code) {
		this();
        javaCode = new String(code);
		parsing();
	}
	private void parsing() {
		cl.setName(getName());
		cl.setType(getObjectType());
		cl.setAccessLevel(getAccessLevel());
		cl.setParentsList(getParents());
		
		int begin = javaCode.indexOf("{");
		int end = javaCode.lastIndexOf("}");
		javaCode = javaCode.substring(begin+1, end);
        deleteInsideMethods();
        while (javaCode.length() > 0) {
            String str = getInstruction();
			Member_info newMb = new Member_info(str);
            if ( newMb.getMemberType() )
                atb.add(newMb);
            else
                met.add(newMb);
        }
		cl.setAtributesList(atb);
		cl.setMethodsList(met);
    }
	private String getName() {
		int index1 = javaCode.indexOf("class");
		int index2 = javaCode.indexOf("interface");
		if (index1 >= 0)
			return getSubString(javaCode, ' ', ' ', index1);
		return getSubString(javaCode, ' ', ' ', index2);
	}
	private String getObjectType() {
		if ( javaCode.indexOf("class") >= 0 )
			return "class";
		return "interface";
	}		
	private Vector<String> getParents() {
		Vector<String> parentsList = new Vector();
		if ( javaCode.indexOf("extends") >= 0 ) {
			int index = javaCode.indexOf("extends");
			parentsList.add( getSubString(javaCode, ' ', ' ', index) );
		}
		if ( javaCode.indexOf("implements") >= 0 ) {
			int index = javaCode.indexOf("implements") + 10;
			while ( javaCode.charAt(index+1) != '{' ) {
				parentsList.add( getSubString(javaCode, ' ', ' ', index) );
				index = javaCode.indexOf(" ", index + 1);
			}
		}
		return parentsList;
	}
	private String getAccessLevel() {
		int begin = javaCode.indexOf("{");
        if ( javaCode.indexOf("public", begin) >= 0 )
			return "public";
        return "package";
    }
    private String getSubString(String s, char begin, char end, int fromIndex) {
        int beginIndex = s.indexOf(begin, fromIndex);
        int endIndex = s.indexOf(end, beginIndex + 1);
        return s.substring(beginIndex + 1, endIndex);
    }
    private void deleteInsideMethods() {
		int n = 10;
        while (n-- >0) {
            int begin = javaCode.indexOf("{");
			int end   = javaCode.indexOf("}");
			
            int begin2 = javaCode.indexOf("{", begin + 1);
            while (begin2 < end && begin2 != -1) {
                begin2 = javaCode.indexOf("{", begin2 + 1);
                if (begin2 == -1) break;
                end = javaCode.indexOf("}", end + 1);
            }
            if (begin == -1) return;
            String target = javaCode.substring(begin, end + 1);
            javaCode = javaCode.replace(target, "");
        }
    }
    private String getInstruction() {
        String s = javaCode.substring(0, javaCode.indexOf(";"));
        javaCode =  javaCode.substring(javaCode.indexOf(";") + 1);
        return s;
    }
	public String output() {
		return cl.getInfo();
	}
}