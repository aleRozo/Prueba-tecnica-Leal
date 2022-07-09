## Instalaciones necesarias
- Gradle
- Java 11

## Ejecucion local
Opciones:
1. Abrir proyecto en Intellij o un IDE y ejecutar tareas de gradle
2. Ejecutar desde una terminal en la raiz del proyecto
    - run --> `gradle bootRun`
    - test --> `gradle test`
    - Reporte de jacoco (coverage) --> `gradle jacocoTestReport`
      
      Para ver el reporte de cobertura de pruebas unitarias ingresar a `"reto\build\reports\jacocoHtml\index.html"`
      

**NOTA:** 
- En la raiz del proyecto hay una coleccion de postman en la cual estan los tres endpoints a probar
- Para abrir la base de datos H2, una vez ejecutado el proyecto ingresar en el navegador a `http://localhost:8080/h2-console` y configurar de la siguiente forma:
  
  `Driver Class: org.h2.Driver`
  
  `JDBC URL: jdbc:h2:mem:testdb`
  
  `User Name: sa`
  
  `Password: (dejar campo vacio)`
  
## Ejecucion remota
Reemplazar en postman y en la url de H2 `http://localhost` por `ec2-34-224-57-199.compute-1.amazonaws.com`