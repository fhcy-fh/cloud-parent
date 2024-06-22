#FROM findepi/graalvm:java17-native
FROM openjdk:17

MAINTAINER Lion Li

RUN mkdir -p /cloud/system/logs \
    /cloud/system/temp \
    /cloud/skywalking/agent

WORKDIR /cloud/system

ENV SERVER_PORT=9201 LANG=C.UTF-8 LC_ALL=C.UTF-8 JAVA_OPTS=""

EXPOSE ${SERVER_PORT}

ADD ./target/cloud-system.jar ./app.jar

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -Dserver.port=${SERVER_PORT} \
           -XX:+HeapDumpOnOutOfMemoryError -XX:+UseZGC ${JAVA_OPTS} \
           -jar app.jar

