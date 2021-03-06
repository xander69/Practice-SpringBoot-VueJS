## Spring Boot + Vue.js

### Stack

* Gradle
* Java 8
* Spring Boot
* Spring Data, JPA EntityGraph
* VueJS, Vuetify, Vuex, Router
* Rest API
* Jackson
* Axios
* OAuth2
* Keycloak
* WebSocket, STOMP, SockJS
* Material Design
* Open Graph Protocol
* Sentry

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
   docker run -d -p 3100:8080 --name home_keycloack -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:17.0.0 start-dev
   ```

7. Настраиваем Spring Security и oAuth2 в Vue.js. Spring Boot REST

   https://www.youtube.com/watch?v=B0887PLJuSY

8. Spring Boot + VueJS + Keycloak
   
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
   cd .\src\frontend
   vue create frontend --no-git
   ```
   Build and run frontend:
   ```
   npm run build
   npm run serve
   ```
   Install axios
   ```
   npm install --save axios vue-axios
   ```
   Install keycloak-js
   ```
   npm install --save keycloak-js
   ```

9. Настраиваем Webpack и разбиваем фронт на модули. Spring Boot REST

   https://www.youtube.com/watch?v=y5zxAriT0UA

10. Подключаем WebSocket (SockJS + Stomp). Spring Boot REST

    https://www.youtube.com/watch?v=TtcCbDddhrI

     ```shell script
     npm i sockjs-client
     npm i @stomp/stompjs
     ```

11. Стилизуем приложение с Vuetify (Material design). Spring Boot REST

    https://www.youtube.com/watch?v=jmpxRAGJ5v0

    ```shell script
    # Once prompted, choose Vuetify 3 Preview
    vue add vuetify
    ```

    Иконки для material-дизайна:
    > https://material.io/resources/icons/?style=baseline

12. Сериализуем с JsonView при отправке через WebSocket. Spring Boot REST

    https://www.youtube.com/watch?v=pJTO0sLki_c

13. Настраиваем Vuex. Централизованное хранилище. Spring Boot REST

    https://www.youtube.com/watch?v=AMb2QeeRtPM

     ```shell script
     npm install vuex@next --save
     ```

14. Vue Router. Роутинг в браузере. Spring Boot REST

    https://www.youtube.com/watch?v=DdCoFwiRCsw

    ```shell script
    npm i vue-router@next
    ```

15. Open Graph Protocol: превью ссылок на сайте по микроразметке. Spring Boot Rest

    https://www.youtube.com/watch?v=ZM8XkFOebuc

    Twitter Card, Open Graph

16. Комментарии с JPA Entity Graph. Spring Boot Rest

    https://www.youtube.com/watch?v=b2a4rVR5hiQ

17. Решение циклических ссылок в JSON. Vue debug. Spring Boot Rest

    https://www.youtube.com/watch?v=fS4gR8gttes

18. Бесконечная прокрутка списков. Spring Boot Rest

    https://www.youtube.com/watch?v=lqQKNgFZvfI

19. Подписки пользователей. Spring Boot Rest

    https://www.youtube.com/watch?v=P9oy1F1mi_k

20. Подписки с подтверждением. Spring Boot Rest

    https://www.youtube.com/watch?v=bVAWMZTD8pI

21. Подписки с подтверждением: фронтенд. Spring Boot Rest

    https://www.youtube.com/watch?v=x3Zwl4QilvA

22. Sentry: собираем ошибки от пользователей. Spring Boot Rest

    https://www.youtube.com/watch?v=JrY2MezfUX4

    ```shell script
    npm install --save @sentry/vue @sentry/tracing
    ```

23. Собираем JAR с Vue.js внутри. Spring Boot Rest

    https://www.youtube.com/watch?v=zZ7Z7aL2WFk
