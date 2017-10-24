package ese4.model;

/**
 * 
 * @author ese4
 * The base type of a person which is used to create Logins with a name and an ID
 */
public abstract class Person {
	protected String name;
	protected int id;
	
	/**
	 * Constructor of a person. Takes its name and id as input and assigns them. 
	 * @param name Name of a person
	 * @param id Each person has an individual ID so we can differentiate them even if they
	 * have the same name
 	*/
	public Person(String name, int id)
	{
		this.name = name;
		this.id = id;
	}

}
