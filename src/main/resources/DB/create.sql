SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS articles (
id int PRIMARY KEY auto_increment,
headline VARCHAR,
documentation VARCHAR,
iamgeurl VARCHAR,
author VARCHAR,
createdat LONG,
formattedCreatedAt VARCHAR
);

CREATE TABLE IF NOT EXISTS tutorials (
id int PRIMARY KEY auto_increment,
title VARCHAR,
videourl VARCHAR,
desc VARCHAR,
source VARCHAR,
createdat LONG,
formattedCreatedAt VARCHAR
);

--CREATE DATABASE perpetual_test WITH TEMPLATE perpetual;