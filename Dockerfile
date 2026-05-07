# Se usa una imagen base oficial de Java21
FROM eclipse-temurin:21-jdk-alpine

# Se define un directorio de trabajo dentro del contenedor
WORKDIR /app

# Se copia el archivo .jar generado por Maven a la imagen Docker
# Se usa un comodín *.jar por si la versión cambia
COPY target/*.jar app.jar

# se expone el puerto que usa Spring Boot por defecto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]