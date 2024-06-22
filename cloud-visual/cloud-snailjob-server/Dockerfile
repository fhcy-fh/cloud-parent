#FROM findepi/graalvm:java17-native
FROM openjdk:17

MAINTAINER Lion Li

RUN mkdir -p /cloud/snailjob/logs

WORKDIR /cloud/snailjob

ENV LANG=C.UTF-8 LC_ALL=C.UTF-8 JAVA_OPTS="-Xms512m -Xmx1024m"

EXPOSE 8800
EXPOSE 1788

ADD ./target/snailjob-server.jar ./app.jar

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom \
           -XX:+HeapDumpOnOutOfMemoryError -XX:+UseZGC ${JAVA_OPTS} \
           -jar app.jar
