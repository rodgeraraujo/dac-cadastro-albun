docker build -t rogerioaraujo/banco ./postgres
docker run -p 5433:5432 --name banco --privileged=true -d rogerioaraujo/banco 
mvn clean package
docker build -t rogerioaraujo/app .
docker run -p 8082:8080 --name app --link banco:host-banco --privileged=true -it -v $(pwd)/apps:/local/tomcat/banco rogerioaraujo/app 
