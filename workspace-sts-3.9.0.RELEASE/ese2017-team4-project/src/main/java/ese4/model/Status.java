package ese4.model;

/**
 * 
 * @author Ese Gruppe 4
 * The 3 Different statuses a package can have.
 *
 */
public enum Status {
	ZUGESTELLT("zugestellt"),
	GEPLANT("geplant"),
	PENDENT("pendent");
	
	private final String displayName;
	
	Status(String displayName) {
	       this.displayName = displayName;
	   }

	   public String getDisplayName() {
		   return displayName;
	   }
}
