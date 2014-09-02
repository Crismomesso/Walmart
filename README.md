# Exercicio Walmart



## Tecnologias utilizadas

Maven 3.2.3

JPA com hibernate 

java 1.7 

Tomcat 7.0

Jersey 1.18.1

Eclipse Kepler Service Release 2


## Instalação

->Baixar o projeto do endereço https://github.com/Crismomesso/Walmart

->Importar o projeto git pelo eclipse. Eu utilizei o pluggin egit

->Adicionar na aba server o tomcat 7.0 , entretando não é necessário atribuir o projeto ao servidor. No pom.xml existe 
um plugin que sobe o servidor e atrela o projeto automáticamente, podendo fazer hot deploy em tempo de execução .

-> Em run configuration do maven , configurar  no campo gols a sequinte linha de comando : clean tomcat:run

-> Ainda em run configuration do maven, configurar no campo base diretory o worspace do sistema ,  no meu ficou ${workspace_loc:/Exercicio}

-> limpar o projeto com o clean e possivel maven update.

## Arquitetura

## Utilização 

Exemplos de entrada de dados 


http://localhost:8080/Exercicio/rotas/gravaMapa

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



http://<Endereço servidor: porta>/Exercicio/rotas/pesquisaRota
Pesquisar rota:
{
	"nomeMapa":"mapa SP",
	"vertice1":"A",
	"vertice2":"D",
	"autonomia":"10",
	"valorLitro":"2.5"
}