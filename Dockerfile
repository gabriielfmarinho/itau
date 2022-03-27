FROM openjdk:8-jdk-alpine

EXPOSE 8080

ADD /build/libs/itau-*.jar itau.jar

ENTRYPOINT ["java", "-jar", "itau.jar"]