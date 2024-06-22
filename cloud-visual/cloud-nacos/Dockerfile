#FROM findepi/graalvm:java17-native
FROM openjdk:17

MAINTAINER Lion Li

RUN mkdir -p /cloud/nacos/logs

WORKDIR /cloud/nacos

ENV LANG=C.UTF-8 LC_ALL=C.UTF-8 JAVA_OPTS=""

EXPOSE 9100

ADD ./target/cloud-nacos.jar ./app.jar

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom \
           -XX:+HeapDumpOnOutOfMemoryError -XX:+UseZGC ${JAVA_OPTS} \
           -jar app.jar
