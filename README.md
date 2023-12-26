# AlphaBackend
| **API**                                                | **Описание**                         | **Текст запроса**                | **Текст ответа**      |
|--------------------------------------------------------|--------------------------------------|:--------------------------------:|:---------------------:|
| `POST /api/auth/register`                              | Регистрация                          | `LoginRequest`                   | `AuthenticationResponse`|
| `POST /api/auth/authenticate`                          | Аутентификация                       |  `AuthenticationRequest`         |  `AuthenticationResponse`|

Если в тексте ответа прочерк, то может приходить стандартный ответ (OK, I AM A TEAPOT etc)

# `LoginRequest`
Класс для регистрации пользователя. Потом в настройках его можно будет изменить.<br>
Пример:
```json
{
    "email": "email@mail.com",
    "fullName": "Misha",
    "password": "password"
}
```

# `AuthenticationResponse`
Класс для аутентификации пользователя.<br>
Пример:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTcwMzU4OTQ0MywiZXhwIjoxNzAzNjc1ODQzfQ.8DyiNKVmPqJhx0rJ4L8YrRhhVyuIMPF-dpBo9xMCdOw",
  "error": null
}
```

# `AuthenticationRequest`
Класс для аутентификации пользователя.<br>
Пример:
```json
{
  "email": "email@mail.com",
  "password": "email@mail.com"
}
```

# `EducationLevel`: 0-2
# `Income`: 0-2
# `Role`: 0-2
# `Address`
```
{
    "id":int,
    "user":`User`,
    "country": string,
    "region":string,
    "city":string,
    "street":string
}
```


# `User`
Пользователь.<br>
```
{
    "id": int,
    "email": string,
    "password": string,
    "fullName": string",
    "sex": "M" | "F",
    "dateOfBirth": "yyyy-MM-dd",
    "educationLevel": `EducationLevel`,
    "income": `Income`,
    "address": `Address`,
    "role": `Role`
}
```
# `Survey`
Опрос в бд.<br>
Пример:
```json
{
    "id": 5,
    "text": "Текст",
    "picture": "название картинки.расширение",
    "questions": "[{\"question\":\"aabb\",\"type\":1,\"ans\":[\"d\",\"g\",\"g\"]}]",
    "answers": []
}
```

# `SurveyRequest`
Опрос в запросе.<br>
Пример:
```json
{
    "text": "Текст",
    "questions": "[{\"question\":\"aabb\",\"type\":1,\"ans\":[\"d\",\"g\",\"g\"]}]",
    "companyId": 123
}
```

# `Company`
Компания, которая может создавать опросы.<br>
```
{
    "id": int,
    "name": string,
    "workers": List<User>,
    "surveys": List<Survey>
}
```
Пример:
```json
{
    "id": 2,
    "name": "some company2",
    "workers": [
        {
            "id": 1,
            "email": "user",
            "password": "pass",
            "fullName": "Pasha",
            "sex": "M",
            "dateOfBirth": "2003-08-02",
            "educationLevel": "None",
            "income": "Small",
            "address": null,
            "role": null
        }
    ],
    "surveys": [
        {
            "id": 1,
            "text": "text",
            "picture": null,
            "questions": "[{\"question\":\"aabb\",\"type\":1,\"ans\":[\"a\",\"b\",\"c\"]}]",
            "answers": []
        },
        {
            "id": 2,
            "text": "text",
            "picture": null,
            "questions": "[{\"question\":\"aabb\",\"type\":1,\"ans\":[\"a\",\"b\",\"c\"]}]",
            "answers": []
        }
    ]
}
```
# БД
Все таблицы описаны в init.sql в каталоге ресурсов.
```mermaid
graph LR
A(Address) --> B(User)
B --> C(Company)
D(Survey) --> C
E(Answers) --> D
E --> B
```


