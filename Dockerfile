FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/fitness-tracking-app-0.0.1-SNAPSHOT.jar app.jar
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "exec java -Dserver.port=${PORT} -XX:TieredStopAtLevel=1 -Xmx350m -jar app.jar"]