package pr0304Barracks.contracts;

import jdk.jshell.spi.ExecutionControl;

public interface Repository {

	void addUnit(Unit unit);

	String getStatistics();

	String removeUnit(String unitType);
}
