# AlphaBackend
| **API**                                                | **Описание**                         | **Текст запроса**                | **Текст ответа**      |
|--------------------------------------------------------|--------------------------------------|:--------------------------------:|:---------------------:|
| `POST /api/auth/register`                              | Регистрация                          | `LoginRequest`                   | `AuthenticationResponse`|
| `POST /api/auth/authenticate`                          | Аутентификация                       |  `AuthenticationRequest`         |  `AuthenticationResponse`|
| `POST /api/users/me/edit`                              | Редактирование пользователя          |  `AuthenticationRequest`         |  `AuthenticationResponse`|

Если в тексте ответа прочерк, то может приходить стандартный ответ (OK, I AM A TEAPOT etc)

# Важно
Далее в тексте, если написано перечисление, то можно использовать строчный вид, или индекс по порядку. (Например, у None индекс - 0, а у SecondaryVocational - 5.)

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

# `AuthenticationRequest`
Класс для аутентификации пользователя.<br>
Пример:
```json
{
  "email": "email@mail.com",
  "password": "email@mail.com"
}
```
# `AuthenticationResponse`
Токен. В запросах, кроме /auth, нужно добавлять его в заголовок: <br>
```text
"Authorization": "Bearer ${token}"
```
Пример:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTcwMzU4OTQ0MywiZXhwIjoxNzAzNjc1ODQzfQ.8DyiNKVmPqJhx0rJ4L8YrRhhVyuIMPF-dpBo9xMCdOw",
  "error": null
}
```

# `User`
Пользователь.<br>
Пример:
```
{
    "id": 1,
    "email": "user1",
    "password": null,
    "fullName": "gg",
    "sex": "M",
    "dateOfBirth": "2003-08-02",
    "educationLevel": 5,
    "income": 30000,
    "city": "Moscow",
    "hobbies": [0,1,2],
    "restaurantVisitsPerWeek": 4,
    "habits": [3,4],
    "role": "CompanyOwner",
    "answersList": [],
    "makingPurchasesOnline": false
}
```
Примечания: 
* формат даты - "yyyy-MM-dd"
* "income" - заработок, типа int

# `Sex`
Перечисление. Пол.
```
M, F
```

# `EducationLevel`
Перечисление. Можно отправлять, как строчки или как индекс по порядку.
```java
    None,
    Primary,
    BasicGeneral,
    GeneralSecondary,
    SecondaryVocational,
    UnfinishedHigher,
    HigherBachelorsDegreeOrSpecialty,
    HigherMastersDegree,
    HigherPostgraduateStudies
```
Например, у None индекс - 0, а у SecondaryVocational - 5.
Вот еще перевод на русский с индексами:
```
        0. Нет
        1. Начальное образование
        2. Основное общее образование
        3. Среднее общее образование
        4. Среднее профессиональное образование
        5. Незаконченное высшее
        6. Высшее образование (бакалавриат/специалитет)
        7. Высшее образование (магистратура)
        8. Высшее образование (аспирантура)
```
# `Role`
Перечисление. Роль. 
```
User, Interviewer, CompanyOwner, Admin
```

# `Hobby`
Перечисление. Хобби. 
```
CarTourism,
    VideoGames,
    Golf,
    CountryHouse,
    HealthyLifestyle,
    ArtAndNeedlework,
    Skiing,
    SportsActivities,
    Boats,
    Horses,
    Music,
    Interior,
    HuntingOrFishing,
    Cooking,
    WatchingSports,
    Journeys,
    Gardening,
    Technologies,
    TourismWithCamping,
    Photographing,
    Reading,
    ExtremeSports,
    Cars
```

# `Habit`
Перечисление. Покупательские привычки. 
```
    BuyingFood,
    BuyingClothesAndShoes,
    VisitingRestaurantsAndCafes,
    TravelAndVacations,
    PurchaseOfHouseholdAppliances,
    AttendingSportsEvents,
    PurchaseOfCosmetics,
    BuyingBooksAndMusic,
    VisitingCinemasAndTheaters,
    BuyingHouseholdGoods
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
Для создания опроса. Так-же использует `UserFilter`. О нем далее<br>
Пример:
```json
{
  "text": "опрос номер 1",
  "questions": "некий json",
  "companyId": 2,
  "filter": {
    "id": null,
    "sex": "M",
    "dateOfBirthInterval": null,
    "educationLevel": 3,
    "income": {
      "type": "Less",
      "first": 40000,
      "second": 0
    },
    "cities": [
      "Moscow", "Kurgan"
    ],
    "hobbies": [
      "CarTourism"
    ],
    "habits": [
      "TravelAndVacations"
    ],
    "restaurantVisitsPerWeek": null,
    "isMakingPurchasesOnline": null
  }
}
```

# `UserFilter`
Используется для фильтрации опросов.
```json
{
    "id": int,
    "sex": Sex,
    "dateOfBirthInterval": {
      "type": IntervalType,
      "first": Date,
      "second": Date | null
    },
    "educationLevel": EducationLevel,
    "income": {
      "type": IntervalType,
      "first": int,
      "second": int | null
    },
    "cities": List<String>,
    "hobbies": List<Hobby>,
    "habits": List<Habit>,
    "restaurantVisitsPerWeek": {
      "type": IntervalType,
      "first": int,
      "second": int | null
    },
    "isMakingPurchasesOnline": bool
  }
```
У трех полей типом является интервал. Интервал задается в общем случае 3 параметрами. Типом и двумя значениями. Если используется первые 2 типа, то задействуется только первое значение. Для лучшего понимания интервалов приведу пример:
```json
{
      "type": "More",
      "first": 30,
      "second": null
}
```
Данный интервал задает такой луч: `[30: +∞)`
```json
{
      "type": 2,
      "first": 30,
      "second": 100
}
```
Данный интервал задает такой отрезок: `[30: 100]`.
C интервалом дат аналогично.

# `IntervalType`
Перечисление. Используется для задания типа интервалов.
```
Less, More, InBetween
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
B(User) --> C(Company)
D(Survey) --> C
E(Answers) --> D
E --> B
```


