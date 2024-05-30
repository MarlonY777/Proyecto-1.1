FROM openjdk:17
COPY "./target/Proyecto1-1-1.jar" "app.jar"
EXPOSE 8044
ENTRYPOINT [ "java", "-jar", "app.jar" ]