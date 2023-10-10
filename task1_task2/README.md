# Задание 1/2

_Выполнил Панов Д.В. 4253_

CRUD application, посвящённое морским битвам второй мировой войны.

_Используемые сущности:_
- Страны
- Битвы
- Корабли

_Стек:_
- Java jdbc
- Spring
- GUI - thymeleaf (web)
- Red Database

Используется "низкоуровневое" подключение к БД через Java jdbc.
Используется Red Database. Все запросы прописаны вручную в классах dao
(репозиториях). Эндпоинты находятся в классах-контроллерах
(web/controllers). Взаимодействие с dao-классами реализовано в сервисах
(web/services)

Взаимодействие с пользователем происходит по схеме MVC. 


## Запуск


- В файле src/main/resources/application.properties в строчке:
  sea-battles-data-base.url=jdbc:firebirdsql://localhost:3050//home/thesr/redbd/tmp/work.fdb
  нужно указать путь до файла.
    - Для linux:
      sea-battles-data-base.url=jdbc:firebirdsql://localhost:3050//path/to/work.fdb
    - Для windows:
      sea-battles-data-base.url=jdbc:firebirdsql://localhost:3050/C:/path/to/work.fdb

- Выполнить $mvn spring-boot:run

_Клиент:_
Для подключения к серверу ввести в браузере localhost:8080/
