
FROM openjdk:17.0.1
EXPOSE 8989 
ADD target\Kitchen_Story-0.0.1-SNAPSHOT.war Kitchen_Story-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java", "-jar", "/Kitchen_Story-0.0.1-SNAPSHOT.war"]