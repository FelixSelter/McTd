package chickencode;

public enum LogType {

	Info("[Information]"), Error("[Error]"), Try("[Try]");

	private String syntax;

	private LogType(String syntax) {
		this.syntax = syntax;
	}

	public String getSyntax() {
		return syntax;
	}

}
