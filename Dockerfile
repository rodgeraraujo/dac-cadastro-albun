FROM tomcat
COPY /target/dac-cadastro.war ${CATALINA_HOME}/webapps
#VOLUME "/usr/local/tomcat/testes"