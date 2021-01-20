package entities;

public class Publication extends AcademicProduction {

	private String conferenceName;
	
	public Publication() {	
	}

	public Publication(String title, String conferenceName, Integer year) {
		super(year, title);
		this.conferenceName = conferenceName;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}
	
	@Override
	public String toString() {
		return "Titulo da publicacao: " + title + 
			   "\nNome da conferencia onde foi publicada: " + conferenceName + 
			   "\nAno da publicacao: " + year;	
	}
}
