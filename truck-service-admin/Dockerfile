FROM node:latest

RUN apt-get update && apt-get install -y vim

EXPOSE 4200

ENV NODE_ROOT /usr/app/
# Creates the directory and all the parents (if they don’t exist)
RUN mkdir -p $NODE_ROOT
# Sets the /usr/app as the active directory
WORKDIR $NODE_ROOT
# Copies all the content
COPY . .

RUN npm install -g @angular/cli