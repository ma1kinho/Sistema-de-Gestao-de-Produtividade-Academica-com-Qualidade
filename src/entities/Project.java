package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.ProjectStatus;

public class Project implements Comparable<Project> {
	private String title;
	private Date startDate;
	private Date endDate;
	private String financeAgency;
	private Double financedAmount;
	private String objective;
	private String description;
	private List<Collaborator> participants = new ArrayList<>();
	private ProjectStatus status = ProjectStatus.IN_ELABORATION;
	private List<AcademicProduction> academicProductions = new ArrayList<>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Project() {
	}

	public Project(String title, Date startDate, Date endDate, String financeAgency, 
					Double financedAmount, String objective, String description) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.financeAgency = financeAgency;
		this.financedAmount = financedAmount;
		this.objective = objective;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getFinanceAgency() {
		return financeAgency;
	}

	public Double getFinancedAmount() {
		return financedAmount;
	}

	public String getObjective() {
		return objective;
	}

	public String getDescription() {
		return description;
	}

	public List<Collaborator> getParticipants() {
		return participants;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public List<AcademicProduction> getAcademicProduction() {
		return academicProductions;
	}
	
	public void addParticipant(Collaborator participant) {
		participants.add(participant);
	}
	
	public void addAcademicProduction(AcademicProduction academicProduction) {
		academicProductions.add(academicProduction);
	}
	
	public boolean VerificProject() {
		if (title != null && startDate != null && endDate != null && financeAgency != null 
			&& financedAmount != null && objective != null && description != null && participants != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int compareTo(Project project) {
		if(this.endDate.after(project.getEndDate())) {
			return -1;
		} else if(this.endDate.before(project.getEndDate())) {
			return 1;
		}
		return 0;
	}
	
	public String projectDate() {
		return "Titulo do projeto: " + title + "\nData de inicio: " + sdf.format(endDate) + 
				"\nData de termino: " + sdf.format(endDate);
	}
	
	public void printCollaboratorsProject() {
		for(Collaborator c : participants) {
			System.out.println(c);
		}
	}
	
	public String toString() {
		return "Titulo do projeto: " + title +
			   "\nData de inicio: " + sdf.format(startDate) +
			   "\nData de termino: " + sdf.format(endDate) +
			   "\nAgencia financiadora: " + financeAgency +
			   "\nValor financiado: " + financedAmount +
			   "\nObjetivo do projeto: " + objective +
			   "\nDescricao do projeto: " + description;
	}
}