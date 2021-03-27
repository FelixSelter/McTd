package game;

import java.util.HashMap;

public class VarManager {

	private HashMap<String, Integer> vars = new HashMap<String, Integer>();

	public VarManager() {
		setVar("Wave", 1);
		setVar("Money", 0);
		setVar("EggTowerPrice", 100);
		setVar("CactusTrapPrice", 100);
		setVar("WaterTrapPrice", 100);
		setVar("Live", 6);
		setVar("RunningWaves", 0);
		setVar("ArrowTowerPrice", 100);
		

	}

	public void setVar(String name, int value) {
		
		
		vars.put(name, value);
	}

	public int getValue(String name) {
		return vars.get(name);
	}

	public void addValue(String name, int value) {
		vars.put(name, vars.get(name) + value);
	}

	public HashMap<String, Integer> getData() {
		return vars;
	}
	
}
