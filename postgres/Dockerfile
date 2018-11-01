FROM postgres
ENV POSTGRES_DB albuns
ENV POSTGRES_USER rodger
ENV POSTGRES_PASSWORD mnb098

COPY cria.sql /docker-entrypoint-initdb.d/
COPY insere.sql /docker-entrypoint-initdb.d/