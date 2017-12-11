package ese4.model;

/**
 * 
 * @author ese04
 * 
 * Enum of different statuses a package can be in
 * A package can only have one Status at the time
 *
 */
public enum Status {
	ZUGESTELLT("zugestellt"),
	GEPLANT("geplant"),
	PENDENT("pendent"),
	NICHTZUSTELLBAR("nicht zustellbar"),
	VERNICHTET("vernichtet"),
	ZURÜCKSENDEN("zurück gesendet");
	
	
	private final String displayName;
	
	/**
	 * Creates a string representation of the Status
	 * @param displayName
	 */
	Status(String displayName) {
	       this.displayName = displayName;
	   }
	
	   /**
	    * @return String from of the Status
	    */
	   public String getDisplayName() {
		   return displayName;
	   }
}
