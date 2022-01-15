#base docker image
FROM openjdk:11
LABEL maintainer="SL-Match.net"
ADD target/ID1212Project-0.0.1-SNAPSHOT.jar sprinboot-docker-demo.jar

ENTRYPOINT ["java","-jar","sprinboot-docker-demo.jar"]
