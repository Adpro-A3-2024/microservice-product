FROM gradle:jdk21-alpine
ARG PRODUCTION
ARG JDBC_DATABASE_PASSWORD
ARG JDBC_DATABASE_URL
ARG JDBC_DATABASE_USERNAME

ENV PRODUCTION ${PRODUCTION}
ENV JDBC_DATABASE_PASSWORD ${JDBC_DATABASE_PASSWORD}
ENV JDBC_DATABASE_URL ${JDBC_DATABASE_URL}
ENV JDBC_DATABASE_USERNAME ${JDBC_DATABASE_USERNAME}

WORKDIR /app
RUN ls -al
COPY ./microservice_product-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java","-jar","microservice_product-0.0.1-SNAPSHOT.jar","--spring.profiles.active=${PRODUCTION}","--spring.datasource.url=${JDBC_DATABASE_URL}", "--spring.datasource.username=${JDBC_DATABASE_USERNAME}", "--spring.datasource.password=${JDBC_DATABASE_PASSWORD}","--spring.datasource.driver-class-name=org.postgresql.Driver","--spring.jpa.hibernate.ddl-auto=update","--spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect","--spring.jpa.properties.hibernate.format_sql=true"]
