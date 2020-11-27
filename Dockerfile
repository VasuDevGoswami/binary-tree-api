FROM maven:3.6.3-amazoncorretto-11 AS build_stage

ADD ./ /var/www
WORKDIR /var/www

RUN mvn package -DskipTests

FROM maslick/minimalka:jdk11 AS run_stage
WORKDIR /app
COPY --from=build_stage /var/www/target/demo-0.0.1-SNAPSHOT.jar ./
EXPOSE 8300
CMD java $JAVA_OPTIONS -jar demo-0.0.1-SNAPSHOT.jar
