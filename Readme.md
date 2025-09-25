Structured school REST API. Spring boot learning tool. Developer and Testing version.

## 🛠️ Built With

* [Spring boot web](https://docs.spring.io/spring-boot/documentation.html) — Java Web framework.
* [H2 database for testing environment](https://www.h2database.com/html/main.html) — H2 in-memory database.
* [Postgres for development environment](https://www.postgresql.org/docs/) — Postgres SQL database
* [Maven for dependency control](https://maven.apache.org/guides/index.html) — Maven
* [MapStruct for quick mapping](https://mapstruct.org/documentation/reference-guide/) — Automatic mapping for simple classes

### 📋 Supported routes and responses

Each route has a designated REST controller and a corresponding DTO.

**Where .../ represents the base url.**
## Students
```
GET .../students/{name}?/{registry}?/{grade}?/{page}?
GET .../students/{uuid}
POST .../students
PUT .../students/{uuid}
DELETE .../students/{uuid}
```
Where the search route is a paginated search.

Each "students" route has a DTO that must contain the following fields for POST, PUT requests AND for responses:
```
{
    "name": "student name",
    "registry": "NNNN00",
    "grade": "N0"
}
```
Where no fields can be null. The maximum name size is 100.

Where the registry must follow the "[A-Z][A-Z][A-Z][A-Z][0-9]+" regexp pattern and cannot be duplicated.

Where the grade must follow the "\\d[A-E]" regexp pattern.

## Professors

```
GET .../professors
GET .../professors/{uuid}
POST .../professors
PUT .../professors/{uuid}
DELETE .../professors/{uuid}
```

Each "professors" route has a DTO for POST and PUT requests and for responses:
```
{
    "name": "professor name",
    "age": 18,
    "isCoordinator": boolean
}
```
Where no fields can be null. The maximum name size is 100.

The age must be an integer value between 18 and 99, both inclusive.

## Rooms

The "rooms" routing follows the same pattern as the "professors" routing.

Each "rooms" route has a DTO for POST and PUT requests and for responses:
```
{
    "name": "room name",
    "capacity": 70
}
```

Where no fields can be null. The maximum name size is 30 and cannot be duplicated.

The capacity must be an integer value between 1 and 1000, both inclusive.

## Classrooms

The "classrooms" routing follows the same pattern as the "professors" and "rooms" routing.

Each "classrooms" route must have the following DTO body for POST and PUT requests:
```
{
    "code": "classroom code",
    "schedule": "25/07/2030 16:00:00",
    "professorId": "professorUUID",
    "roomId": "roomUUID"
}
```
Where no fields can be null. Where the code has a size of 5 and follows the "[A-Z][0-9]+" regexp pattern.

Where the schedule must be on the "dd/MM/yyyy HH:mm:ss" date formatting.

Where the professorId and roomId values must be valid within the data storage.

Each "classrooms" route has the following DTO body for GET responses:

```
{
    "code": "classroom code",
    "schedule": "25/07/2030 16:00:00",
    "professor": { professorDTO },
    "room": { roomDTO },
    "students": [ (list of) { studentDTO } ]
}
```

## ClassroomStudents

Attaching and detaching students to classrooms.

```
POST .../classroom_students/{classroomUUID}
DELETE .../classroom_students/{classroomUUID}
```

Where the "classroom_students" POST request must have the following DTO:
```
{
    "studentId": "studentUUID"
}
```
Where the studentId value must be valid within the data storage.

## 📦 Production environment

The Production build and deploy is being developed. This project is for study purposes.

## ⚙️ Testing

[Postman](https://www.postman.com/) — was used for manual testing of routes. Automated testing in development.

---
⌨️ by [Samuel Moura Alves](https://github.com/Somutt)