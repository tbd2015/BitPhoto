# BitPhoto Sistema en Gradle

Asumiendo que $GLASSFISH_HOME, path de instalación glassfish

* Instalar glassfish4: https://glassfish.java.net
* Iniciar el dominio $GLASSFISH_HOME/bin/asadmin start-domain
* Copiar el driver (mysql-connector-java-5.1.X.jar) de mysql a $GLASSFISH_HOME/lib
* Crear un recurso jdbc: http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-glassfish-config.html el recurso jdbc debe tener el nombre "bitphoto" y tambien deben tener un pool de conecciones que utilice el recurso "bitphoto" de nombre pool_bitphoto. Ejecutar asadmin start-domain.
* Importar proyecto en Netbeans (deben tener soporte para gradle).
* Modificar el glassfishHome en el archivo: ~/.gradle/gradle.properties: glassfishHome=/path/to/glassfish_home(donde tengan instalado glassfish)
* Ejecutar gradle deploy en el directorio de gladle.
* Verificar que esta funcionando en localhost:8080/BitPhotoSystem/albumes

