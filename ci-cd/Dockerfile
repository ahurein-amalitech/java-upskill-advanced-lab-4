FROM openjdk:8

EXPOSE 8080
ADD target/ci_cd.jar ci_cd.jar
ENTRYPOINT [ "java", "-jar", "/ci_cd.jar" ]