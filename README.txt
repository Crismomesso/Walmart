Tecnologias utilizadas

Maven 3.2.3
JPA com hibernate 
java 1.7 
Tomcat 7.0
Jersey 1.18.1


Exemplos de entrada de dados 



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

Pesquisar rota:

{
	"nomeMapa":"mapa SP",
	"vertice1":"A",
	"vertice2":"D",
	"autonomia":"10",
	"valorLitro":"2.5"
}