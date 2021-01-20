package entities;

public class ParameterObject {
	private String name;
	private String email;
	
	public ParameterObject(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}	
}