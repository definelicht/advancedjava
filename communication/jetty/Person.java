package communication.jetty;

public class Person {

	private int id;
	private String name;

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return ("ID = " + this.getId() + " and name = " + this.getName());
	}

	public boolean equals(Person per) {
		return (this.getId() == per.getId() && this.getName().equalsIgnoreCase(
				per.getName()));
	}

}
