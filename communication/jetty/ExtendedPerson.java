package communication.jetty;

import java.util.List;

public class ExtendedPerson extends Person {
	private List<String> emails;

	public ExtendedPerson(int id, String name, List<String> emails) {
		super(id, name);
		this.emails = emails;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public String toString() {
		return (super.toString() + " emails = " + emails.toString());
	}

	public boolean equals(ExtendedPerson per) {
		return (super.equals(per) && this.getEmails().equals(per.getEmails()));
	}

}
