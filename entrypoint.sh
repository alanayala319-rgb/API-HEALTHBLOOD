#!/bin/sh
set -e

# Render asigna el puerto en la variable PORT. Tomcat usa 8080 por defecto.
sed -i "s/port=\"8080\"/port=\"${PORT:-8080}\"/" /usr/local/tomcat/conf/server.xml

exec catalina.sh run
