package com.prosvirnin.alphabackend.model.user;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EducationLevel {
    None,
    Primary,
    BasicGeneral,
    GeneralSecondary,
    SecondaryVocational,
    UnfinishedHigher,
    HigherBachelorsDegreeOrSpecialty,
    HigherMastersDegree,
    HigherPostgraduateStudies;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}

//        0. Нет
//        1. Начальное образование
//        2. Основное общее образование
//        3. Среднее общее образование
//        4. Среднее профессиональное образование
//        5. Незаконченное высшее
//        6. Высшее образование (бакалавриат/специалитет)
//        7. Высшее образование (магистратура)
//        8. Высшее образование (аспирантура)
