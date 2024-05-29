# baseimage
FROM openjdk:17-slim

# copy jar file
COPY ./build/libs/*T.jar app.jar

# 빌드할 떄 실행
RUN ["java","-version"]
#ENV profiles=default
EXPOSE 8080

# run jar file
CMD ["java", "-jar","-Dspring.profiles.active=${profiles}", "app.jar"]
