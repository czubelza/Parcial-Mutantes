# Parcial-Mutantes



**Consigna del exámen**

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.

Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

Para eso te ha pedido crear un programa con un método o función con la siguiente firma (En alguno de los siguiente lenguajes: Java / Golang / C-C++ / Javascript (node) / Python / Ruby):

boolean isMutant(String[] dna); // Ejemplo Java
En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

No-Mutante

A T G C G A

C A G T G C

T T A T T T

A G A C G G

G C G T C A

T C A C T G

Mutante

A T G C G A

C A G T G C

T T A T G T

A G A A G G

C C C C T A

T C A C T G


Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras iguales​, de forma oblicua, horizontal o vertical. Ejemplo (Caso mutante):

String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

En este caso el llamado a la función isMutant(dna) devuelve “true”. Desarrolla el algoritmo de la manera más eficiente posible. Desafíos:

Nivel 1:
Programa (en cualquier lenguaje de programación) que cumpla con el método pedido por Magneto.

Nivel 2:
Crear una API REST, hostear esa API en un cloud computing libre (Google App Engine, Amazon AWS, etc), crear el servicio “/mutant/” en donde se pueda detectar si un humano es mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el siguiente formato:

POST → /mutant/ { “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }

En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un 403-Forbidden

Nivel 3:
Anexar una base de datos, la cual guarde los ADN’s verificados con la API. Solo 1 registro por ADN.

Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las verificaciones de ADN: {"count_mutant_dna" : 40, "count_human_dna" : 100, "ratio" : 0.4 } Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico (Entre 100 y 1 millón de peticiones por segundo). Test-Automáticos, Code coverage > 80%.

<h1>Proyecto subido a Render</h1>
[Proyecto](https://parcial-mutantes-h7su.onrender.com)


<h1>Endpoints (sea local o en render)</h1>
POST /mutant. Para Verificar si es mutante se coloca el Ejemplo:


```json
{
  "dna": ["ATGCGA", "CCGTAC", "TTATGT", "AGAAGG", "CCCTTA", "TCACTG"]
}
```

GET /stats. Para las estadisticas resultado devuelto:


```json
{
    "countMutantDna": 0,
    "countHumanDna": 0,
    "ratio": 0.0
}
```
 <h1>Para Ejecutar el Proyecto de forma local</h1>

1. **Descomprimir el archivo**:
   - Extrae el contenido del archivo comprimido en alguna ubicación de tu elección.

2. **Abrir la carpeta del proyecto con Intellij**

   
   -Iniciar o correr la aplicación y efectuar los endpoints mencionados con postman o herramientas similares

   
   _otra opcion es usar la herramienta provista por la api como swagger se puede llamar a ella usando :

   http://localhost:8080/swagger-ui/index.html  

<h1>Ejecución de tests automáticos de forma local </h1>

Se ejecuta mediante el comando :
  ```sh
     ./gradlew test jacocoTestReport
  ```
<h1>Tests</h1>
<img src="https://raw.githubusercontent.com/czubelza/Parcial-Mutantes/refs/heads/main/ArchivosNivel3/testAutomaticos.png" alt="Logo" width="8700"/>
<h1>Prueba de Stress con Jmeter</h1>
<img src="https://raw.githubusercontent.com/czubelza/Parcial-Mutantes/refs/heads/main/ArchivosNivel3/B.png" alt="Logo" width="8700"/>
<img src="https://raw.githubusercontent.com/czubelza/Parcial-Mutantes/refs/heads/main/ArchivosNivel3/A.png" alt="Logo" width="8700"/>
