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