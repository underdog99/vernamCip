# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM tomcat:9.0-jdk11
ADD target/*.jar vernamCip.jar
#RUN mv /usr/local/tomcat/conf/server.xml /usr/local/tomcat/conf/server-bu.xml
#ADD certJava1-rsa-cert.pem /usr/local/tomcat/conf/
#ADD certJava1-rsa-key.pem /usr/local/tomcat/conf/
#ADD server.xml /usr/local/tomcat/conf/
EXPOSE 8080
ENTRYPOINT ["java","-jar","vernamCip.jar"]
