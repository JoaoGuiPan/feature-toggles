# FROM node:lts-hydrogen as build
# WORKDIR /app
# COPY package*.json ./
# RUN npm install
# COPY . .
# RUN npm run build --prod

FROM nginx:stable-alpine
COPY nginx.conf /etc/nginx/nginx.conf
# COPY --from=build /app/dist/feature-toggles-front /usr/share/nginx/html
COPY /dist/feature-toggles-front /usr/share/nginx/html