#FROM findepi/graalvm:java17-native
FROM openjdk:17

MAINTAINER Lion Li

RUN mkdir -p /cloud/gen/logs

WORKDIR /cloud/gen

ENV SERVER_PORT=9202 LANG=C.UTF-8 LC_ALL=C.UTF-8 JAVA_OPTS=""

EXPOSE ${SERVER_PORT}

ADD ./target/cloud-gen.jar ./app.jar

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -Dserver.port=${SERVER_PORT} ${JAVA_OPTS} -jar app.jar
