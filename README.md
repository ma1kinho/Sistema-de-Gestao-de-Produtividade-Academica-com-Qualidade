# Sistema-de-Gestao-de-Produtividade-Academica-com-Qualidade
Projeto de Desenvolvimento OO da disciplina de Projeto de Software

## Instruções de execução do sistema:
Digitar todas as informações sempre sem caracteres especiais ou acentos. Os nomes dos participantes sempre devem estar completos. Não pode haver mais de um projeto com o mesmo título.

## Como funciona:
### Opção 0 - Fechar o programa
Termina a execução e fecha o programa.

### Opção 1 - Elaborar um projeto
O status inicial de um projeto é "Em elaboração". Para começar a elaborar um projeto é necessário digitar todas as suas informações básicas. Projetos possuem as seguintes informações básicas: título, data de início, data de término, agência financiadora, valor financiado, objetivo, descrição e participantes. Um projeto de pesquisa deve ter pelo menos um professor como participante.

### Opção 2 -Iniciar um projeto
Depois de constarem todas as informações básicas a respeito do projeto, é possível alterar o status do projeto para "Em andamento". Digite o título do projeto que vai ser iniciado e, caso esteja tudo certo conforme as regras, o mesmo será iniciado. A partir deste momento, o status do projeto somente poderá ser alterado para “Concluído”.

### Opção 3 - Concluir um projeto
Para concluir um projeto é necessário existir publicações associadas a ele. Então, se não huver nenhuma publicação associada ao projeto, o status do projeto não poderá ser alterado de "Em andamento" para "Concluído".

### Opção 4 - Alocar um participante
A alocação de participante só é permitida caso o projeto esteja "Em elaboração". Um estudante de graduação não pode participar de mais de dois projetos "Em andamento". Caso o participante que vai ser alocado já faça parte de outro projeto do laboratório, digite apenas seu nome, caso não, digite todas as informações sobre ele.

### Opção 5 - Criar uma publicação
Uma publicação pode ser feita por um ou mais autores e é permitido que todos os colaboradores do laboratório tenham publicações. Uma publicação só poderá ser associada a um projeto quando o status do projeto estiver “Em andamento”. Uma publicação deve ter: título, nome da conferência onde foi publicada, ano de publicação e projeto de pesquisa associado (se houver).

### Opção 6 - Criar uma observacao
As observações são feitas apenas por professores. Orientações devem ter: título, descrição, ano que a orientação foi produzida, quantidade de tarefas, tarefas a serem seguidas e projeto de pesquisa associado (se houver).

### Opção 7 - Consultar um colaborador
Para consultar um colaborador é necessário que seja inserido o seu nome completo. Caso a pessoa seja mesmo um colaborador do laborátorio de pesquisa, o sistema mostrará suas informações: nome, e-mail e um histórico contendo a lista de projetos e de produção acadêmica do colaborador.

### Opção 8 - Consultar um projeto
Dado o título do projeto, o sistema vai verificar se de fato o projeto existe. Caso o projeto seja encontrado, o sistema vai mostrar todos os dados do projeto, incluindo seus colaboradores e uma lista contendo toda a produção acadêmica do projeto.

### Opção 9 - Obter relatório de produção acadêmica do laboratorio
Nesta opção, o sistema vai fornecer um relatório de produçao acadêmica do laboratório, contendo: número de colaboradores, número de projetos em elaboração, número de projetos em andamento, número de projetos concluídos, número total de projetos e número de produção acadêmica por tipo de produção.

## Mudanças no sistema:
### Padrões de projeto 
Foi
