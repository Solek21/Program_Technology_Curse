# Курсовой проект по Программным Технологиям

## Описание

Курсовой проект по Программным Технологиям - это микросервис для управления встречами, предназначенный для использования в архитектуре микросервисов. Проект построен с использованием Spring Boot, JPA, Hibernate и H2 (или другой базы данных по вашему выбору).

## Функциональные возможности

- Создание встречи
- Получение информации о встрече по ID
- Обновление информации о встрече
- Мягкое удаление и восстановление встречи
- Получение списка всех встреч
- Подсчет количества встреч

## Технологии

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 (или другая настроенная база данных)
- Maven
- Lombok

## Требования

- Java 17
- Maven 3.6+

## Установка

### Клонирование репозитория


git clone https://github.com/Solek21/Program_Technology_Curse.git
cd Program_Technology_Curse
Сборка проекта с помощью Maven
mvn clean install
Запуск приложения
mvn spring-boot:run
Приложение запустится на http://localhost:8080.

Использование
API Эндпоинты
Создание встречи

POST /api/meet/add
Content-Type: application/json
{
  "idOwner": "owner1",
  "idAudience": "audience1",
  "maxSize": 10,
  "idEquipment": "equipment1",
  "isDeleted": false,
  "idMeetingName": "meeting1"
}
Получение информации о встрече по ID

GET /api/meet/{id_meeting}
Получение списка всех встреч

GET /api/meet/getAll
Обновление информации о встрече

PUT /api/meet/update
Content-Type: application/json
{
  "id_meeting": "uuid",
  "idOwner": "updatedOwner",
  "idAudience": "updatedAudience",
  "maxSize": 15,
  "idEquipment": "updatedEquipment",
  "isDeleted": false,
  "idMeetingName": "updatedMeetingName"
}
Удаление встречи

PATCH /api/meet/delete
Параметр запроса: id_meeting=uuid

Подсчет количества встреч
GET /api/meet/count

Тестирование
Проект включает юнит-тесты для контроллеров. Тесты находятся в каталоге src/test/java.

Для запуска тестов используйте Maven:

mvn test
