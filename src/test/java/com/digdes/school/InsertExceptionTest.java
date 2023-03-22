package com.digdes.school;

import com.digdes.school.exceptions.FieldNotFoundException;
import com.digdes.school.exceptions.SyntaxErrorException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsertExceptionTest {

    @ParameterizedTest
    @ValueSource(strings = {
//correct   "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",

            "",
            "INSERT",
            "INSERT VALUES",
            "INSERT 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'=keks, 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks' 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'==3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4.2",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4.",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4,",

//            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=34.",
//            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=.2",

            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=.2.2",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4 WHERE 'id'=3",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4 WHERE",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4, 'lastName'='kek'",
            "INSERT WHERE 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4 2",
            "INSERT VALUES WHERE",

            "INSERT VALUES 'lastName' = 'Федоров' VALUES 'active'=true",
            "INSERT VALUES 'lastName' = 'Федоров' , 'id'=, 'age'=40, 'active'=true",
            "INSERT VALUES 'lastName' = 'Федоров' , 'id'=3 'age'=40, 'active'=true",
            "INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true,",
            "IN SERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true",
            "INSERT VALU ES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true",
            "INSERT WHERE 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true",
            "INSERT VALUES 'lastName'='Федоров', 'id'=3, 'age'=40, 'active'=true WHERE 'lol'=true",
            "INSERT VALUES 'lastName'='Федоров', 'id'=3, 'age'=40, 'active'=true WHERE",
            "INSErtVALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true",
            "INSERT VALUES'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true",
            "INSErtVALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'='true'",
            "INSERT VALUES 'lastName' = 'Федоров' , 'id'=3 'age'=40, 'active'=true",
            "INSErtVALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=t",

            "INSERT VALUES 'lastName'=23, 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'=23.2, 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'=true, 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",

            "INSERT VALUES 'lastName'='keks', 'id'=true, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3.2, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'='keks', 'age'=40, 'active'=true, 'cost'=3.4",

            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'='true', 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=2, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=2.3, 'cost'=3.4",

            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'='3.2'",
//TODO int to double       "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=true",

    })
    void insert_syntax_exception(String query) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        assertThrows(SyntaxErrorException.class, () -> starter.execute(query));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "INSERT VALUES 'name'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'ative'=true, 'cost'=3.4"
    })
    void insert_field_exception(String query) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        assertThrows(FieldNotFoundException.class, () -> starter.execute(query));
    }
}
