package chickencode;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StackTraceTools {

	public static String toString(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
