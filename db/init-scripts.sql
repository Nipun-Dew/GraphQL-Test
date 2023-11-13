create extension hstore;
create schema graphql;
create table if not exists graphql."picture" ("id" BIGSERIAL NOT NULL PRIMARY KEY,"width" INTEGER NOT NULL,"height" INTEGER NOT NULL, "url" VARCHAR);
