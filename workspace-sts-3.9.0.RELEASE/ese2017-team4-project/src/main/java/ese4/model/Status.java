package ese4.model;

public enum Status {
	ZUGESTELLT("zugestellt"),
	GEPLANT("geplant"),
	PENDANT("pendant");
	
	private final String displayName;
	
	Status(String displayName) {
	       this.displayName = displayName;
	   }

	   public String getDisplayName() {
		   return displayName;
	   }
}
