package chickencode;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	private String name;
	private LogManager logmanager;

	public void log(String msg, LogType type) {
		SimpleDateFormat time = new SimpleDateFormat("kk:mm:ss:SSS");
		System.err.println(time.format(new Date()) + " " + type.getSyntax() + logmanager.getSyntaxPuffer(type) + " "
				+ name + logmanager.getPuffer(name) + "  logged: " + msg);
		if (type == LogType.Error)
			throw new RuntimeException();
	}

	public Logger(String name, LogManager logmanager) {
		this.name = name;
		this.logmanager = logmanager;
		logmanager.registerName(name);
	}

}
