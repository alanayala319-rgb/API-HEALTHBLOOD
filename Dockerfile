FROM tomcat:10.1-jdk17-temurin

# Limpiar las aplicaciones que trae Tomcat por defecto
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el WAR como ROOT para que la API quede en la raíz
COPY dist/API_HealthBlood_512.war /usr/local/tomcat/webapps/ROOT.war

# Script que adapta el puerto de Tomcat al que asigna Render
COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh

# Memoria ajustada al plan Free de Render (512 MB)
ENV JAVA_OPTS="-Xms64m -Xmx256m -XX:MaxMetaspaceSize=96m -Dfile.encoding=UTF-8"

EXPOSE 8080

ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
