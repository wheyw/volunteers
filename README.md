# Веб-сервис управления волонтерами 

## Описание

Веб-сервис управления волонтерами - это микросервис для управления волонтерами, предназначенное для упрощения организации и координации мероприятий с участием волонтеров. Проект построен с использованием Spring Boot, Spring Data JPA и PostgreSQL.

## Функциональные возможности

- Реализация отдельных механизмов использования для пользователя и организатора
- Управление вакансиями и откликами: просмотр и откликов и создание вакансий организатором
- Хранение и управление: система обеспечивает надежное хранение данных и управление ими
- Осуществление связи с другим микросервисом, откуда берутся данные об мероприятиях.

## Технологии

- Java 11
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

## Требования

- Java 11
- PostgreSQL

## Установка

1. Клонируйте репозиторий:
    ```sh
    git clone https://github.com/wheyw/volunteers.git
    cd volunteers
    ```

2. Создайте базу данных PostgreSQL и обновите `application.properties` с вашими настройками:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/volunteers
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. Соберите проект с помощью Maven:
    ```sh
    mvn clean install
    ```

4. Запустите приложение:
    ```sh
    mvn spring-boot:run
    ```

## Использование

### API Эндпоинты

- **Создание вакансии**
    ```http
    POST /api/v1/vacancies/add
    Content-Type: application/json
    {
      "eventId": 1,
      "volonterId": "8c04a00a-26d9-465d-b1af-179b16cbff7a"
    }
    ```

- **Создание отклика**
    ```http
    POST /api/v1/otclicks/add
    Content-Type: application/json
    {
      "vacancyId": 1,
      "volonterId": "0418c506-d54b-45a6-9ae0-43a8037302c5"
    }
    ```

- **Получение списка вакансий**
    ```http
    GET /api/v1/vacancies/all
    ```

- **Получение списка откликов**
    ```http
    GET /api/v1/otclicks/all
    ```
    
- **Изменение статуса отклика**
    ```http
    PUT /api/v1/otclicks/came
      Content-Type: Params/QueryParams
    {
       Key     Value
       id      1
       status  true
    }
    ```

## Тестирование

Проект включает юнит-тесты для контроллеров и сервисов. Тесты находятся в каталоге `src/test/java`.

Для запуска тестов используйте Maven:
```sh
mvn test
