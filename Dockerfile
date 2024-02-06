# FROM amazoncorretto:17
# VOLUME /tmp
# COPY build/libs/issueMoa-learning-0.0.1-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java", "-jar", "/app.jar"]
FROM amazoncorretto:17
VOLUME /issuemoa/logs

COPY entrypoint.sh /entrypoint.sh
COPY build/libs/issueMoa-learning-0.0.1-SNAPSHOT.jar app.jar

# 환경 변수를 통해 로그 디렉토리 및 파일명 동적으로 설정
#ENV LOG_DIRECTORY=/issuemoa/logs
#ENV LOG_FILE=learning

ENTRYPOINT ["java", "-jar" ,"/app.jar", "> /issuemoa/logs/learning.log 2 > &1"]
#ENTRYPOINT ["/entrypoint.sh"]
