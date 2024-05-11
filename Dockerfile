#FROM openjdk:11-jre-slim
#COPY ./target/CurrencyExchangeApp-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
FROM openjdk:17-jdk-alpine


ADD target/CurrencyExchangeApp-0.0.1-SNAPSHOT.jar CurrencyExchangeApp-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/CurrencyExchangeApp-0.0.1-SNAPSHOT.jar"]
