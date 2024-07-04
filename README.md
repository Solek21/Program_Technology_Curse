# ProgramTech Users Microservice

## Описание

Program_Technology_Curse - это микросервис для управления пользователями, предназначенный для использования в архитектуре микросервисов. Проект построен с использованием Spring Boot, JPA, Hibernate и PostgreSQL.

## Функциональные возможности

- Создание встречи
- Получение информации о встрече по ID
- Обновление информации о встрече
- Мягкое удаление и восстановление встречи
- Получение списка всех встреч
- Подсчет количества встреч

## Технологии

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Docker
- Maven
- Lombok

## Требования

- Java 21
- Docker и Docker Compose
- PostgreSQL

## Установка

1. Клонируйте репозиторий:
```sh
git clone git clone https://github.com/Solek21/Program_Technology_Curse.git
cd Program_Technology_Curse
```

2. Соберите проект с помощью Maven:
```sh
mvn clean install
```

## Использование

### API Эндпоинты

- **Создание Мероприятия**
```http
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
```

- **Получение информации о мероприятии по ID**
```http
GET /api/meet/{id_meeting}
```

- **Получение списка всех мероприятий**
```http
GET /api/meet/getAll
```

- **Обновление информации о мероприятии**
```http
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
```

- **Удаление мероприятия**
```http
PATCH /api/meet/delete
```


## Тестирование

Проект включает юнит-тесты для контроллеров. Тесты находятся в каталоге `src/test/java`.

Для запуска тестов используйте Maven:
```sh
mvn test
