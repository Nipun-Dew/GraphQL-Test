create extension hstore;
create schema graphql;
create table if not exists graphql."picture" ("id" BIGSERIAL NOT NULL PRIMARY KEY,"width" INTEGER NOT NULL,"height" INTEGER NOT NULL, "url" VARCHAR);
create table if not exists graphql."paper" ("id" BIGSERIAL NOT NULL PRIMARY KEY,"pictureId" INTEGER NOT NULL,"author" VARCHAR NOT NULL, "isbn" VARCHAR NOT NULL);
