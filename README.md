# Exercicio Walmart



## Tecnologias utilizadas

Maven 3.2.3 - Foi utilizado o maven para o gerenciamento de dependência, buscando nos repositórios os jars necessários para a execução do sistema    

JPA com hibernate - Para persistência dos dados, foi optado pelo uso de jpa com hibernate, pela facilidade de persistir objetos imbutidos.

java 1.7 

Tomcat 7.0

Jersey 1.18.1 - Framework que implementa especificação para o JAX-RS. Basicamente o jersey tem um servidor e um cliente REST 

Eclipse Kepler Service Release 2

JUNIT 4.0  - Para execução dos testes unitários, foi optado pela utilização do junit.

## Instalação

->Baixar o projeto do endereço https://github.com/Crismomesso/Walmart

->Importar o projeto git pelo eclipse. Eu utilizei o pluggin egit

->Adicionar na aba server o tomcat 7.0 , entretando não é necessário atribuir o projeto ao servidor. No pom.xml existe 
um plugin que sobe o servidor e atrela o projeto automáticamente.

-> Em run configuration do maven , configurar  no campo gols a sequinte linha de comando : clean tomcat:run

-> Ainda em run configuration do maven, configurar no campo base diretory o worspace do sistema ,  no meu ficou ${workspace_loc:/Exercicio}

-> Verificar as facetas de projeto se o java esta na versão 1.7

-> Verificar em windows-preferences-java-compiler se esta configurado para 1.7

-> Limpar o projeto project-Clean

## Arquitetura

-> Optei por dividir o sistema em camada de serviço, negocios , dados. 


## Utilização 

###entrada de dados 

-> Para a estrutura de entrada de dados foi optado pelo padrão JSON pela sua simplicidade e uso difundido.

### PUT

http://<Endereço servidor: porta>/Exercicio/rotas/gravaMapa

Gravar mapas:

{

	"nomeMapa":"mapa SP",
	
	"rotas": [
	
	 {"p1" :"A" , "p2":"B" , "distancia":"10" },
	 
	 {"p1" :"B" , "p2":"D" , "distancia":"15" },
	 
	 {"p1" :"A" , "p2":"C" , "distancia":"20" },
	 
	 {"p1" :"C" , "p2":"D" , "distancia":"30" },
	 
	 {"p1" :"B" , "p2":"E" , "distancia":"50" },
	 
	 {"p1" :"D" , "p2":"E" , "distancia":"30" }
	 
	]
	
}


### POST

http://<Endereço servidor: porta>/Exercicio/rotas/pesquisaRota

Pesquisar rota:

{

	"nomeMapa":"mapa SP",
	
	"vertice1":"A",
	
	"vertice2":"D",
	
	"autonomia":"10",
	
	"valorLitro":"2.5"
	
}