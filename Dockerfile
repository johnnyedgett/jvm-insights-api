FROM java:8
EXPOSE 8080
ADD /target/jvm-insights-api-0.0.1-SNAPSHOT.jar jvm-insights-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "jvm-insights-api-0.0.1-SNAPSHOT.jar"]
