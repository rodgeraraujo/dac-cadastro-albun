FROM tomcat
COPY /target/dac-cadastro.war ${CATALINA_HOME}/webapps
VOLUME [ "/local/tomcat/banco"]
