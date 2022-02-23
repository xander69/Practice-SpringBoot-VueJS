## Spring Boot + Vue.js

### Stack

* Gradle
* Java 8
* Spring Boot
* VueJS
* Rest API

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

