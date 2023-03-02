# FROM node:18.14.2-alpine3.17 as build
# WORKDIR /app
# COPY package*.json ./
# RUN npm ci
# COPY . .
# RUN npm run build --prod

FROM nginx:stable-alpine
# COPY --from=build /app/dist/ /usr/share/nginx/html
COPY dist/ /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf