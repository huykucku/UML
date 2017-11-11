package DataStore;
import java.util.Vector;
		
public class Class_info {
	private String name;
	private String accessLevel;
	private String type;
	private Vector<String> parentsList;
	private Vector<Member_info> atributesList;
	private Vector<Member_info> methodsList;
	
	public Class_info() {
		name = new String();
		accessLevel = new String();
		type = new String();
		parentsList = new Vector();
		atributesList = new Vector();
		methodsList = new Vector();
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setParentsList(Vector<String> parents) {
		this.parentsList = parents;
	}
	public void setAtributesList(Vector<Member_info> atb) {
		this.atributesList = atb;
	}
	public void setMethodsList(Vector<Member_info> met) {
		this.methodsList = met;
	}
	public String getInfo() {
		String s = new String();
		s += accessLevel + "\n";
		s += type + "\n";
		s += name + "\n";
		s += "parents: ";	for (String st : parentsList) s += st + " ";
		s += "\natributes:\n";	for (Member_info mb : atributesList) s += "\t" + mb.getInfo() + "\n";
		s += "\nmethods:\n";	for (Member_info mb : methodsList) s += "\t" + mb.getInfo() + "\n";
		return s;
	}
}