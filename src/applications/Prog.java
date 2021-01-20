package applications;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.AcademicProduction;
import entities.Collaborator;
import entities.DoctoralStudent;
import entities.GraduationStudent;
import entities.MasterStudent;
import entities.Orientation;
import entities.ParameterObject;
import entities.Project;
import entities.Publication;
import entities.Researcher;
import entities.Teacher;
import entities.enums.ProjectStatus;

public class Prog {
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);

		List<Project> listProjects = new ArrayList<>();
		List<Collaborator> listCollaborators = new ArrayList<>();

		int option;

		do {
			System.out.println("O que voce pretende fazer?");
			System.out.println("[0] - Fechar o programa");
			System.out.println("[1] - Elaborar um projeto");
			System.out.println("[2] - Iniciar um projeto");
			System.out.println("[3] - Concluir um projeto");
			System.out.println("[4] - Alocar um participante");
			System.out.println("[5] - Criar uma publicacao");
			System.out.println("[6] - Criar uma observacao");
			System.out.println("[7] - Consultar um colaborador");
			System.out.println("[8] - Consultar um projeto");
			System.out.println("[9] - Obter relatorio de producao academica do laboratorio");
			System.out.print("=> ");

			try {
				option = sc.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("Digite uma opcao valida!");
				System.out.print("=> ");
				sc.nextLine();
				option = sc.nextInt();
			}

			sc.nextLine();
			
			switch (option) {
			case 0:
				System.out.println("Programa fechado com sucesso!");
				break;
				
			case 1:
				System.out.print("Digite o titulo do projeto a ser elaborado: ");
				String titleProject = sc.nextLine();
				
				Date startDate;
				try {
					System.out.print("Data de inicio (DD/MM/YYYY): ");
					startDate = sdf.parse(sc.next());
				}
				catch(ParseException e) {
					System.out.println("Digite a data no formato certo!");
					System.out.print("Data de inicio (DD/MM/YYYY): ");
				    startDate = sdf.parse(sc.next());
				}
				
				Date endDate;
				try {
					System.out.print("Data de termino (DD/MM/YYYY): ");
					endDate = sdf.parse(sc.next());
				}
				catch(ParseException e) {
					System.out.println("Digite a data no formato certo!");
					System.out.print("Data de termino (DD/MM/YYYY): ");
					endDate = sdf.parse(sc.next());
				}
				
				sc.nextLine();
				System.out.print("Agencia financiadora: ");
				String financeAgency = sc.nextLine();
				System.out.print("Valor financiado: ");
				double financedAmount = sc.nextDouble();
				System.out.print("Objetivo do projeto: ");
				sc.nextLine();
				String objective = sc.nextLine();
				System.out.print("Descricao do projeto: ");
				String description = sc.nextLine();
				System.out.print("Quantos participantes o projeto tera inicialmente? ");
				int qntParticipants = sc.nextInt();
				sc.nextLine();
				int qntTeachers = 0;

				Project project = new Project(titleProject, startDate, endDate, financeAgency, financedAmount,
											objective, description);
				
				Collaborator participant;
				
				for (int i = 0; i < qntParticipants; i++) {
					System.out.println();
					System.out.println("PARTICIPANTE #" + (i + 1) + ":");
					
					participant = AllocCollaborator(listCollaborators, sc);
					
					if(participant instanceof GraduationStudent && participant.qntProjectsInElaboration() >= 2) {
						System.out.println("Um aluno de graduação não pode participar de mais de dois projetos em andamento.\n");
					} else {
						if(participant instanceof Teacher) qntTeachers += 1;
						project.addParticipant(participant);
						participant.addProjectToCollaborator(project);
					}
					
					if (i == qntParticipants - 1 && qntTeachers == 0) {
						System.out.println("\nUm projeto de pesquisa deve ter pelo menos um professor como participante!!!");
						System.out.println("Adicione um professor.");
						qntParticipants += 1;
					}
				}	
				listProjects.add(project);
				System.out.println("Projeto elaborado com sucesso!\n");

				break;

			case 2:
				System.out.print("Digite o titulo do projeto a ser iniciado: ");
				titleProject = sc.nextLine();
				Project projectAux = SearchProject(listProjects, titleProject);

				while (projectAux == null) {
					System.out.println("O projeto nao existe! Tente novamente!");
					System.out.print("Digite o nome do projeto a ser iniciado: ");
					titleProject = sc.nextLine();
					projectAux = SearchProject(listProjects, titleProject);
				}

				if (projectAux.VerificProject()) {
					System.out.print("Alterar o status do projeto para 'Em andamento' (s/n)? ");
					char choiceStatus = sc.next().charAt(0);

					if (choiceStatus == 's') {
						projectAux.setStatus(ProjectStatus.IN_PROGRESS);
						System.out.println("Projeto iniciado com sucesso!\n");
					}
				} else {
					System.out.println("Um projeto so pode ser iniciado apenas se constarem"
							+ " todas as informações básicas a respeito do projeto cadastradas.\n");
				}

				break;

			case 3:
				System.out.print("Digite o titulo do projeto a ser concluido: ");
				titleProject = sc.nextLine();
				projectAux = SearchProject(listProjects, titleProject);
				
				while (projectAux == null) {
					System.out.println("O projeto nao existe! Tente novamente.\n");
					System.out.print("Digite o nome do projeto a ser concluido: ");
					titleProject = sc.nextLine();
					projectAux = SearchProject(listProjects, titleProject);
				}
				
				int qntPub = 0;
				for(AcademicProduction ap : projectAux.getAcademicProduction()) {
					if(ap instanceof Publication) {
						qntPub++;
						break;
					}
				}
				
				if(qntPub == 0) {
					System.out.println("Para o projeto ser concluido devem "
							+ "existir publicações associadas a ele.\n");
				} else {
					projectAux.setStatus(ProjectStatus.FINISHED);
					System.out.println("Projeto concluido com sucesso!\n");
				}
				
				break;
			
			case 4:
				System.out.println("DADOS DO PARTICIPANTE A SER ALOCADO:");
				participant = AllocCollaborator(listCollaborators, sc);
				
				if(participant instanceof GraduationStudent && participant.qntProjectsInElaboration() >= 2) {
					System.out.println("Um aluno de graduação não pode participar de mais de dois projetos em andamento.\n");
					break;
				}
				
				System.out.print("Digite o titulo do projeto que o participante vai ser alocado: ");
				titleProject = sc.nextLine();
				projectAux = SearchProject(listProjects, titleProject);
				
				while (projectAux == null) {
					System.out.println("O projeto nao existe! Tente novamente.\n");
					System.out.print("Digite o titulo do projeto que o participante vai ser alocado: ");
					titleProject = sc.nextLine();
					projectAux = SearchProject(listProjects, titleProject);
				}
				
				if(projectAux.getStatus().equals(ProjectStatus.IN_ELABORATION)) {
					projectAux.addParticipant(participant);
					participant.addProjectToCollaborator(projectAux);
					System.out.println("Participante alocado com sucesso!\n");
				} else {
					System.out.println("A alocação deve ser permitida apenas quando o projeto estiver 'em elaboração'.\n");
				}
					
				break;
				
			case 5:
				System.out.print("Digite o titulo da publicacao: ");
				String title = sc.nextLine();
				System.out.print("Nome da conferencia onde foi publicada: ");
				String conferenceName = sc.nextLine();
				System.out.print("Ano de publicacao: ");
				int yearOfPublication = sc.nextInt();
				
				List<AcademicProduction> nonAssociatedPublications = new ArrayList<>();
				AcademicProduction publication = new Publication(title, conferenceName, yearOfPublication);
				
				System.out.print("Quantos autores a publicacao tem? ");
				int qntAuthors = sc.nextInt();			
				sc.nextLine();
				System.out.println();
				for(int i=0; i<qntAuthors; i++) {
					System.out.println("Autor #" + (i + 1) + ": ");
					System.out.print("Nome: ");
					String name = sc.nextLine();
					
					Collaborator collaboratorAux = SearchCollaborator(listCollaborators, name);
					
					while(collaboratorAux == null) {
						System.out.println("Autor nao foi encontrado!");
						System.out.print("Nome: ");
						name = sc.nextLine();
						collaboratorAux = SearchCollaborator(listCollaborators, name);
					}
					System.out.println("Autor encontrado com sucesso!\n");
					collaboratorAux.addAcademicProductionToCollaborator(publication);
				}
				
				System.out.print("A publicacao possui algum projeto de pesquisa associado (s/n)? ");
				char choice = sc.next().charAt(0);

				if (choice == 's') {
					System.out.print("Qual o titulo do projeto associado? ");
					sc.nextLine();
					titleProject = sc.nextLine();
					projectAux = SearchProject(listProjects, titleProject);
					
					while (projectAux == null) {
						System.out.println("O projeto nao existe!\n");
						System.out.print("Digite o titulo do projeto associado: ");
						titleProject = sc.nextLine();
						projectAux = SearchProject(listProjects, titleProject);
					}
							
					if (projectAux.getStatus() != ProjectStatus.IN_PROGRESS) {
						System.out.println("\nUma publicação só poderá ser associada a um projeto"
										 + " quando o status do projeto estiver 'Em andamento'\n");
					} else {
						projectAux.addAcademicProduction(publication);
						publication.projectAssociation(projectAux);
						System.out.println("Publicacao associada com sucesso!\n");
					}
				} else {
					nonAssociatedPublications.add(publication);
					System.out.println("Publicacao feita com sucesso!\n");
				}

				break;
				
			case 6:
				System.out.print("Digite o titulo da orientacao: ");
				title = sc.nextLine();
				System.out.print("Digite a descricao da orientacao: ");
				String descriptionOfOrientation = sc.nextLine();
				System.out.print("Ano da orientacao: ");
				int yearOfOrientation = sc.nextInt();
				System.out.print("Quantidade de tarefas a serem seguidas: ");
				int qntTasks = sc.nextInt();
				sc.nextLine();
				String [] tasks = new String[qntTasks];
				
				System.out.println("TAREFAS");
				for(int i=0; i<qntTasks; i++) {
					System.out.print("Digite a tarefa #" + (i + 1) + ": ");
					tasks[i] = sc.nextLine();
				}
				
				Orientation orientations = new Orientation(title, descriptionOfOrientation, yearOfOrientation, qntTasks, tasks);
				
				System.out.print("Quantos professores fizeram as orientacoes? ");
				int qntT = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				for(int i=0; i<qntT; i++) {
					System.out.println("Professor #" + (i + 1) + ":");
					System.out.print("Nome: ");
					String nameOfTeacher = sc.nextLine();
					Collaborator collaboratorAux = SearchCollaborator(listCollaborators, nameOfTeacher);
					
					while(collaboratorAux == null) {
						System.out.println("Professor nao encontrado!\n");
						System.out.print("Nome: ");
						nameOfTeacher = sc.nextLine();
						collaboratorAux = SearchCollaborator(listCollaborators, nameOfTeacher);
					} 
				
					if(collaboratorAux instanceof Teacher) {
						System.out.println("Professor encontrado com sucesso!");
						collaboratorAux.addAcademicProductionToCollaborator(orientations);
					} else {
						System.out.println("As orientações devem ser feitas por professores.");
					}
				}
				
				
				System.out.print("As orientacoes possuem algum projeto de pesquisa associado (s/n)? ");
			    choice = sc.next().charAt(0);

				if (choice == 's') {
					System.out.print("Qual o titulo do projeto associado? ");
					sc.nextLine();
					titleProject = sc.nextLine();
					projectAux = SearchProject(listProjects, titleProject);
					
					while (projectAux == null) {
						System.out.println("O projeto nao existe!\n");
						System.out.print("Digite o titulo do projeto associado: ");
						titleProject = sc.nextLine();
						projectAux = SearchProject(listProjects, titleProject);
					}
					
					projectAux.addAcademicProduction(orientations);
					orientations.projectAssociation(projectAux);
					System.out.println("As orientacoes foram associadas com sucesso!");
				}		
				System.out.println();
				
				break;
				
			case 7:
				System.out.print("Digite o nome do colaborador a ser consultado: ");
				String name = sc.nextLine();
				
				Collaborator collaboratorAux = SearchCollaborator(listCollaborators, name);
				
				while (collaboratorAux == null) {
					System.out.println("O colaborador nao existe! Tente novamente.");
					name = sc.nextLine();
					collaboratorAux = SearchCollaborator(listCollaborators, name);
				}			
				
				Collections.sort(collaboratorAux.getProjects());
				Collections.sort(collaboratorAux.getAcademicProductions());
				System.out.println("Colaborador encontrado com sucesso!");
				System.out.println("INFORMACOES DO COLABORADOR");
				System.out.println(collaboratorAux);
				System.out.println("LISTA DE PROJETOS DO COLABORADOR");
				collaboratorAux.printCollaboratorProjects();
				System.out.println("LISTA DE PRODUCAO ACADEMICA DO COLABORADOR");
				printAcademicProduction(collaboratorAux.getAcademicProductions());
				System.out.println();
				break;
				
			case 8:
				System.out.print("Digite o titulo do projeto a ser consultado: ");
				titleProject = sc.nextLine();
				
				projectAux = SearchProject(listProjects, titleProject);
				
				while(projectAux == null) {
					System.out.println("O projeto nao existe! Tente novamente.");
					System.out.print("Digite o titulo do projeto a ser consultado: ");
					titleProject = sc.nextLine();
					projectAux = SearchProject(listProjects, titleProject);
				}
				
				System.out.println("Projeto encontrado com sucesso!");
				System.out.println("DADOS DO PROJETO");
				System.out.println(projectAux);
				System.out.println("COLABORADORES ALOCADOS NO PROJETO");
				projectAux.printCollaboratorsProject();
				System.out.println("PRODUCAO ACADEMICA DO PROJETO");
				Collections.sort(projectAux.getAcademicProduction());
				printAcademicProduction(projectAux.getAcademicProduction());
				System.out.println();
					
				break;
				
			case 9:
				System.out.println("Numero de colaboradores: " + listCollaborators.size());
				
				int qntPIE = 0;
				int qntPIP = 0;
				int qntPF = 0;
				for(Project p : listProjects) {
					if(p.getStatus().equals(ProjectStatus.IN_ELABORATION)) qntPIE += 1;
					if(p.getStatus().equals(ProjectStatus.IN_PROGRESS)) qntPIP += 1;
					if(p.getStatus().equals(ProjectStatus.FINISHED)) qntPF += 1;
				}
				System.out.println("Numero de projetos em elaboracao: " + qntPIE);
				System.out.println("Numero de projetos em andamento: " + qntPIP);
				System.out.println("Numero de projetos concluidos: " + qntPF);
				System.out.println("Numero total de projetos: " + (qntPIE + qntPIP + qntPF));
				
				int qntPubl = 0;
				int qntOrient = 0;
				for(Project p : listProjects) {
					for(AcademicProduction ap : p.getAcademicProduction()) {
						if(ap instanceof Publication) qntPubl += 1;
					    if(ap instanceof Orientation) qntOrient += 1;
					}
				}
				System.out.println("Numero de publicacoes: " + qntPubl);
				System.out.println("Numero de orientacoes: " + qntOrient);
				System.out.println();
				
				break;
	
			default:
			}

		} while (option != 0);
		
		sc.close();
	}	

	public static Collaborator AllocCollaborator(List<Collaborator> listCollaborators, Scanner sc) {
		Collaborator participant;

		System.out.print("O participante ja faz parte de algum projeto do laboratorio (s/n)? ");
		char opt = sc.next().charAt(0);
		sc.nextLine();
		
		if(opt == 's') {
			System.out.print("Nome: ");
			String name = sc.nextLine();
			participant = SearchCollaborator(listCollaborators, name);
			
			while(participant == null) {
				System.out.println("Participante nao encontrado! Tente novamente.");
				System.out.print("Nome: ");
				name = sc.nextLine();
				participant = SearchCollaborator(listCollaborators, name);
			}
			System.out.println("Participante encontrado com sucesso!");
		} else {
			System.out.print("Nome: ");
			String name = sc.nextLine();
			System.out.print("Email: ");
			String email = sc.nextLine();
			System.out.println("Qual o cargo de " + name + " no projeto?");
			System.out.print("1 - aluno de graduação\n"
						   + "2 - aluno de mestrado\n"
						   + "3 - aluno de doutorado\n"
						   + "4 - professor\n"
						   + "5 - pesquisador\n=> ");
			int numCargo;
			try {
				numCargo = sc.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("Digite uma opcao valida!");
				sc.nextLine();
				numCargo = sc.nextInt();
			}
			sc.nextLine();
			
			ParameterObject parameterObject = new ParameterObject(name, email);
			
			if(numCargo == 1) {
				participant = new GraduationStudent(parameterObject);
			}
			else if(numCargo == 2) {
				participant = new MasterStudent(parameterObject);
			}
			else if(numCargo == 3) {
				participant = new DoctoralStudent(parameterObject);
			}
			else if(numCargo == 4) {
				participant = new Teacher(parameterObject);
			}
			else {
				participant = new Researcher(parameterObject);
			}
			listCollaborators.add(participant);
			System.out.println("Participante instanciado com sucesso!");
		}
		return participant;
	}
	
	public static Project SearchProject(List<Project> listProjects, String title) {

		for (Project p : listProjects) {
			if (title.equals(p.getTitle())) {
				return p;
			}
		}
		return null;
	}
	
	public static Collaborator SearchCollaborator(List<Collaborator> listCollaborators, String name) {
		for (Collaborator col : listCollaborators) {
			if(name.equals(col.getName())) {
				return col;
			}
		}
		return null;
	}
	
	public static void printAcademicProduction(List<AcademicProduction> academicProduction) {
		for(AcademicProduction ap : academicProduction) {
			System.out.println(ap);
		}
	}
}