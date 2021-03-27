package chickencode;

public class LogManager {

	private int highest_syntax=0;
	private  int highest = 0;
	
	public LogManager() {
		for (LogType type : LogType.values()) {
			if(type.getSyntax().length()>highest_syntax)highest_syntax=type.getSyntax().length();
		}
	}

	public  void registerName(String name) {
		if (name.length() > highest)
			highest = name.length();
	}

	public  String getPuffer(String name) {
		String puffer = "";
		while ((name + puffer).length() < highest) {
			puffer += "  ";
		}
		return puffer;
	}
	
	public String getSyntaxPuffer(LogType type) {
		String puffer = "";
		while ((type.getSyntax() + puffer).length() < highest_syntax) {
			puffer += "  ";
		}
		return puffer;
	}

}
