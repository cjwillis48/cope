#dockerfile
FROM node:10-alpine AS ui-build
WORKDIR /usr/src/app
COPY . .
RUN npm install --prefer-offline && npm run build

FROM node:10-alpine AS ui-run
WORKDIR /root/
RUN ls -al
COPY server .
COPY --from=ui-build /usr/src/app/dist ./dist
RUN  npm install

EXPOSE 8080

CMD ["npm", "run", "start"]
