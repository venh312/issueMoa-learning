#!/bin/bash

# 현재 날짜를 포맷하여 로그 파일 이름 생성
LOG_FILE="/issuemoa/logs/learning-$(date +'%Y-%m-%d').log"

# Java 프로세스 실행
exec java -Dlogging.file="$LOG_FILE" -jar /app.jar
