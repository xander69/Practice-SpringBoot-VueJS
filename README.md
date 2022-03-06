## Spring Boot + Vue.js

### Stack

* Gradle
* Java 8
* Spring Boot
* Spring Data
* VueJS
* Rest API
* Jackson
* Axios
* OAuth2
* Keycloak

### References

1. Делаем простое REST приложение с нуля. Spring Boot REST

   https://www.youtube.com/watch?v=LOmcsf5IylI

   Пример POST-запрос в консоли браузера (F12):
    ```javascript
    fetch('http://localhost:8080/message', {
           method: 'POST',
           headers: {'Content-Type': 'application/json'},
           body: JSON.stringify({text: 'Fourth message'})
    }).then(result => console.log(result))
    ```

2. Vue.js - базовый JavaScript интерфейс для REST приложения (часть 1). Spring Boot REST

   https://www.youtube.com/watch?v=VwVRGpoGT5M

   Для корректной инспекции JS в Intellij Idea настроить версию языка JavaScript
   > Settings -> Languages & Frameworks -> JavaScript -> JavaScript Language Version = ECMAScript6

   Документация по JS:
   > https://learn.javascript.ru/

3. Vue.js - отображение и изменение данных с сервера (часть 2). Spring Boot REST

   https://www.youtube.com/watch?v=E0S1ZrQP0-Y

4. Подключаем базу данных, настраиваем Jackson. Spring Boot REST

   https://www.youtube.com/watch?v=mGfiV9WDd6Y
   ```
   docker run -d -e POSTGRES_PASSWORD=postgres --name home_pg -p 5432:5432 postgres
   ```
   ```postgresql
   create database vuejs;
   ```

5. Подключаем Spring Security и oAuth2. Spring Boot REST

   https://www.youtube.com/watch?v=-ohlXEJeRX8

   Tutorial:
   https://docs.spring.io/spring-security/site/docs/5.2.12.RELEASE/reference/html/oauth2.html

6. Spring Security OAuth2 Tutorial with Keycloak | Full Course:
   https://www.youtube.com/watch?v=t9O99l4gjAc
   ```
   docker run -d -p 3100:8080 --name home_keyclock -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:17.0.0 start-dev
   ```

7. Настраиваем Spring Security и oAuth2 в Vue.js. Spring Boot REST

   https://www.youtube.com/watch?v=B0887PLJuSY

8. Spring Boot and VueJS
   
   Update node:
   ```
   npm install -g npm@latest
   ```
   Install/update vue-cli:
   ```
   npm install -g @vue/cli
   ```
   Create project:
   ```
   vue create frontend --no-git
   ```
   Build and run frontend:
   ```
   npm run build
   npm run serve
   ```