FROM amazoncorretto:17
VOLUME /tmp
COPY build/libs/issueMoa-voca-0.0.1-SNAPSHOT.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java","-jar","/app.jar"]