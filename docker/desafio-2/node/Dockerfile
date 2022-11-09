FROM node:15

RUN ["mkdir", "/install"]

ADD ["./package.json", "/install"]
WORKDIR /install
RUN npm install --verbose
ENV NODE_PATH=/install/node_modules

WORKDIR /usr/src/app

COPY . .

RUN npm install express --save


EXPOSE 3000

CMD ["node", "index.js"]