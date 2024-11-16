# Challenge LiterAlura💻

Este programa es un desafio como forma parte del programa d educacion en tecnologia de ORACLE ONE + ALURA LATAM, el cual se centra en el consumo de una Api que contiene una biblioteca de libros, en donde el usuario podra eligir entre diferentes opciones que se detallan mas adelante. Te invito a explorar mas sobre este programa, vamos..!

## Comencemos 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

### Tecnologias usadas 📋

_Para ejecutar este proyecto, necesitarás tener instalado lo siguiente:_

- JDK 17 o superior
- Maven (para gestionar las dependencias)
- Spring: versión actual
- Mysql: versión actual
- IDE (Entorno de desenvolvimento integrado) - IntelliJ IDEA

Para el **Despliegue** se detalla los siguientes pasos.
#### Instalación en Windows

1. **Instalar Java**
    - Descarga e instala el JDK desde el sitio oficial de Oracle:  
      [Descargar JDK](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
    - Sigue las instrucciones del instalador para instalar el JDK.
    - Verifica la instalación abriendo la consola `cmd` y ejecutando:
      ```
      java -version
      ```

2. **Instalar Maven**
    - Descarga Maven desde el sitio oficial:  
      [Descargar Maven](https://maven.apache.org/download.cgi)
    - Descomprime el archivo descargado y agrega la ruta de Maven a las variables de entorno de tu sistema.
    - Verifica la instalación abriendo la consola `cmd` y ejecutando:
      ```
      mvn -version
      ```

### Instalación 🔧

_Una serie de pasos para tener el entorno de desarrollo configurado:_

1. **Clonar el repositorio**  
   Clona este repositorio en tu máquina local usando Git:
   ```
   git clone https://github.com/ariocal/Literalura.git
   cd literalura
   ```

2. **Agregar dependencias**  
   Este proyecto utiliza Jackson y JPA. Si estás usando Maven, puedes agregar las dependencias al archivo `pom.xml` de la siguiente manera:

   ```xml
   <dependencies>
      <dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.18.0</version>
		</dependency>
   <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
   </dependencies>
   ```

3. **Compilar el proyecto**  
   Compila el proyecto con Maven:
   ```
   mvn clean install
   ```

_Finaliza estos pasos con la creación del archivo JAR ejecutable:_

4. **Crear el archivo JAR**  
   Ejecuta el siguiente comando para empaquetar el proyecto en un archivo JAR:
   ```
   mvn package
   ```

5. **Ejecutar el programa**  
   Para ejecutar el archivo JAR generado, utiliza:
   ```
   java -jar target/literalura.jar
   ```

### Guia de funcionamiento ⌨️
-Al ejecutar la aplicación se los desplegara una pantalla de opciones de las cuales podremos elegir una...
###### Pantalla principal.
![Opcion 1](imagenes/imagen1.png)

En la opcion 1 podremos buscar un libro ingresando el cual se guardara en el base de datos que estemos usando.
###### Ingresar opcion para buscar libro.
![Opcion 2](imagenes/imagen2.png) 
 
Después de ingresar la cantidad nos aparecerá, el resultado de la conversión de la cual queríamos conocer su cambio, y vuelve a aparecer el menú inicial. Si deseamos realizar otra conversion podemos nuevamente elegir una opción que este dentro del menu...
###### Respuesta de la API
![Opcion 3](imagenes/imagen3.png)

Para salir del programa solo elegimos la opción numero 7 y se nos especificara que hemos salido del sistema de conversion. 
###### Salir del programa.
![Opcion 4](imagenes/paso4.png)




## Construido con 🛠️

_Herramientas utilizadas en el desarrollo del proyecto:_

* [Java](https://www.oracle.com/java/) - El lenguaje de programación principal
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Gson](https://github.com/google/gson) - Para trabajar con JSON
* [Json.org](https://www.json.org/json-en.html) - Otra biblioteca para trabajar con JSON
* [ExachangeRate-API](https://www.exchangerate-api.com/docs/pair-conversion-requests) - API para el intercambio de monedas en tiempo real

## Autor ✒️


* **Mario Calderon** - *Creador* - 👨🏽‍💻🖥️
