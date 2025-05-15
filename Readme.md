Structured school REST API. Spring boot learning tool.

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

Each "students" route has a DTO that contains the following fields:
```
For requests AND responses:
{
    "name": "student name", Must not be null, maximun size is 100
    "registry": "NNNN00", Must not be null, follows "[A-Z][A-Z][A-Z][A-Z][0-9]+" regexp pattern
    "grade": "N0", Must not be null, follows "\d[A-E]" regexp pattern
}
```

## Professors

```
GET .../professors
GET .../professors/{uuid}
POST .../professors
PUT .../professors/{uuid}
DELETE .../professors/{uuid}
```

## Rooms

The "rooms" routing follows the same pattern as the "professors" routing.

## Classrooms

The "classrooms" routing follows the same pattern as the "professors" and "rooms" routing.

## ClassroomStudents

Attaching and detaching students to classrooms.

```
POST .../classroom_students/{classroomUUID}
DELETE .../classroom_students/{classroomUUID}
```