package com.digdes.school;

import com.digdes.school.exceptions.FieldNotFoundException;
import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.exceptions.TypeErrorException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsertExceptionTest {

    @ParameterizedTest
    @ValueSource(strings = {
//correct   "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",
            "",
            "INSERT",
            "INSERT 'lastName'='keks'",

            "INSERT VALUES",
            "INSERT VALUES'lastName'='keks'",
            "INSERT VALUES 'lastName'='keks' VALUES 'age'=23",

            "INSERT VALUES WHERE",
            "INSERT WHERE  'lastName'='keks'",
            "INSERT VALUES 'lastName'='keks' WHERE 'id'=3",
            "INSERT VALUES 'lastName'='keks' WHERE",

            "IN SERT VALUES 'lastName'='Федоров'",
            "INSERT VALU ES 'lastName'='Федоров'",
            "INSErtVALUES 'lastName' = 'Федоров'",

            "INSERT VALUES 'lastName'='keks', 'lastName'='kek'",
            "INSERT VALUES 'lastName'=keks",
            "INSERT VALUES 'lastName'='keks' 'id'=3",
            "INSERT VALUES 'lastName'=='keks'",
            "INSERT VALUES ''lastName'='keks'",
            "INSERT VALUES 'lastName''='keks'",
//            "INSERT VALUES 'lastName'=''keks'",
//            "INSERT VALUES 'lastName'='keks''",

            "INSERT VALUES 'cost'=3.4.2",
            "INSERT VALUES 'cost'=3.4.",
            "INSERT VALUES 'cost'=3.4,",
            "INSERT VALUES 'cost'=.2.2",
            "INSERT VALUES 'cost'=3.4 2",

            "INSERT VALUES 'lastName'=",

            "INSERT VALUES 'id'=-",
            "INSERT VALUES 'cost'=-."
    })
    void syntax_exception(String query) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        assertThrows(SyntaxErrorException.class, () -> starter.execute(query));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "INSERT VALUES 'lastName'=23,   'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'=23.2, 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'=true, 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",

            "INSERT VALUES 'lastName'='keks', 'id'=true,   'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3.2,    'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'='keks', 'age'=40, 'active'=true, 'cost'=3.4",

            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=true,   'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40.2,   'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'='test', 'active'=true, 'cost'=3.4",

            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'='true', 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=2,      'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=2.3,    'cost'=3.4",

            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'='3.2'",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=true",

    })
    void type_exception(String query) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        assertThrows(TypeErrorException.class, () -> starter.execute(query));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "INSERT VALUES 'name'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'ative'=true, 'cost'=3.4",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4, 'hello'='test'"
    })
    void field_exception(String query) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        assertThrows(FieldNotFoundException.class, () -> starter.execute(query));
    }
}
