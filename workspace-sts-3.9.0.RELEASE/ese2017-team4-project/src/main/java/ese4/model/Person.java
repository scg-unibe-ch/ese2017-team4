package ese4.model;

/**
 * 
 * @author ese4
 * The base type of a person which is used to create Logins with a name and an ID
 * @Param id Int To keep track of different users even if they have the same names
 * @Param name String The name of a person
 */
public abstract class Person {
	protected String name;
	protected int id;
	

	public Person(String name, int id)
	{
		this.name = name;
		this.id = id;
	}

}
