# Задание 4

_Выполнил Панов Д.В. 4253_

CRUD application, посвящённое морским битвам второй мировой войны.

_Используемые сущности:_
- Страны
- Битвы
- Корабли

_Стек:_
- Java JPA
- Spring
- Postgresql
- Flyway - миграции
- Поддержка swagger

Используется Flyway для применения миграций, которые находятся в директории
resources/db/migration.

http://localhost:8080/v3/api-docs - Информация об эндпоинтах

./gradlew bootRun - запуск
