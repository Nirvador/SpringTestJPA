FROM mysql:8.0.20

MAINTAINER Xavier HERWYN

ENV MYSQL_DATABASE=public
ENV MYSQL_ROOT_PASSWORD=password

ADD schema.sql /docker-entrypoint-initdb.d

EXPOSE 3306