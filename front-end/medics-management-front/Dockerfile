FROM node:18.12.1-alpine as build-step
RUN mkdir -p /home/medics-app/frontend
WORKDIR /home/medics-app/frontend
COPY package.json /home/medics-app/frontend
RUN npm i npm@latest -g
RUN npm install
COPY . /home/medics-app/frontend
RUN npm run build --prod
EXPOSE 4200
CMD [ "npm", "start"]
