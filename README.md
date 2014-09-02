# Exercicio Walmart

## Tecnologias utilizadas

Maven 3.2.3 - Foi utilizado o maven para o gerenciamento de dependência, buscando nos repositórios os jars necessários para a execução do sistema    

JPA com hibernate - Para persistência dos dados, foi optado pelo uso de jpa com hibernate, pela facilidade de persistir objetos imbutidos.

java 1.7 

Tomcat 7.0

Jersey 1.18.1 - Framework que implementa especificação para o JAX-RS. Basicamente o jersey tem um servidor e um cliente REST 

Eclipse Kepler Service Release 2

JUNIT 4.0  - Para execução dos testes unitários, foi optado pela utilização do junit.

SoapUI 5.0 - Interface para consumo de webservice. Optei por também realizar testes utilizando essa ide, pela facilidade de executar requisições em webservices

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

###Orientações

-> No sistema existe dois serviços implementados. O primeiro de gravação de mapas, e o segundo de pesquisa de rotas. 
Para se pesquisar uma rota, é necessário previamente gravar um mapa na base, para isso é necessário utilizar o serviço "gravaMapa", após o mapa persistido, utiliza-se o serviço "pesquisaRota"
para achar uma rota valida.

###entrada de dados 

-> Para a estrutura de entrada de dados foi optado pelo padrão JSON pela sua simplicidade e uso difundido.


### WADL

-> Após o servidor estar rodando a aplicação , a especificação WADL estará na URL  "http://<Endereço servidor: porta>/Exercicio/application.wadl"

### PUT

http://<Endereço servidor: porta>/Exercicio/rotas/gravaMapa

Gravar mapas:

Exemplo de requisição JSON para persistência de mapas

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

Exemplo de requisição JSON para pesquisa de rota

{

	"nomeMapa":"mapa SP",
	
	"vertice1":"A",
	
	"vertice2":"D",
	
	"autonomia":"10",
	
	"valorLitro":"2.5"
	
}

### Resposta do webservice

-> JSON contendo a mensagem do resultado do serviço. 

{

"resultado": "menssagem contendo o resultado"

}

## Javadoc

-> O java doc da aplicação se encontra em "Walmart\Exercicio\doc"

