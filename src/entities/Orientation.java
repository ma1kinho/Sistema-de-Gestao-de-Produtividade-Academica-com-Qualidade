package entities;

public class Orientation extends AcademicProduction {
	private String description;
	private int qntTasks;
	private String [] tasks = new String[qntTasks];
	
	public Orientation() {
	}
	
	public Orientation(String title, String description, Integer year, int qntTasks, String[] tasks) {
		super(year, title);
		this.description = description;
		this.qntTasks = qntTasks;
		this.tasks = tasks;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQntTasks() {
		return qntTasks;
	}

	public void setQntTasks(int qntTasks) {
		this.qntTasks = qntTasks;
	}

	public String[] getTasks() {
		return tasks;
	}

	public void setTasks(String[] tasks) {
		this.tasks = tasks;
	}
	
	@Override
	public String toString() {
		return "Titulo da orientacao: " + title + 
			   "\nDescricao da orientacao: " + description + 
			   "\nAno que a orientacao foi feita: " + year;
	}
}