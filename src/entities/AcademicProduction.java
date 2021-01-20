package entities;

public abstract class AcademicProduction implements Comparable<AcademicProduction> {
	
	protected String title;
	protected Integer year;
	protected Project associatedProject;
	
	public AcademicProduction() {
	}
	
	public AcademicProduction(Integer year, String title) {
		this.year = year;
		this.title = title;
	}
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Project getAssociatedProject() {
		return associatedProject;
	}

	public void projectAssociation(Project project) {
		associatedProject = project;
	}

	@Override
	public int compareTo(AcademicProduction academicProduction) {
		if(this.year > academicProduction.getYear()) {
			return -1;
		} else if(this.year < academicProduction.getYear()) {
			return 1;
		}
		return 0;
	}
	
	public abstract String toString();
}
