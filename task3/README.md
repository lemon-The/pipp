# Задание 3

_Выполнил Панов Д.В. 4253_

CRUD application - книжная полка, для хранения информации о книгах,
авторах и жанрах.

_Используемые сущности:_
- Авторы
- Книги
- Жанры
- Обложки книг

_Стек:_
- Java JPA
- Spring
- GUI - thymeleaf (web)
- H2 database

Используется JPA/Hibernate ORM framework для автоматического отображения
кортежей из базы данных на классы Java.

Взаимодействие с пользователем происходит по схеме MVC. 

Контроллеры находятся в bookshelf/web/controllers
Сервисы, отвечающие за "бизнес-логику" в bookshelf/web/services
Репозитории в bookshelf/data
Доменные классы в bookshelf/
