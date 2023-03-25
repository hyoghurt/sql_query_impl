package com.digdes.school;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SyntaxExceptionTest {

    @ParameterizedTest
    @ValueSource(strings = {

            //null
            "UPDATE VALUES 'id'=3 WHERE 'age'=null",

            //conditions
            "UPDATE VALUES 'id'=3 WHERE",
            "UPDATE VALUES 'id'=3 WHERE AND 'age'=4",
            "UPDATE VALUES 'id'=3 WHERE 'age'=4 AND",
            "UPDATE VALUES 'id'=3 WHERE 'age'=4 AND OR 'age'=5",
            "UPDATE VALUES 'id'=3 WHERE 'age'=4 lol 'age'=5",
            "UPDATE VALUES 'id'=3 WHERE AND",
            "UPDATE VALUES 'id'=3 WHERE 'age'=4 AND 'id'=3 OR",
            "UPDATE VALUES 'id'=3 WHERE 'age'=4 'id'=3",
            "UPDATE VALUES 'id'=3 WHERE 'age'=4, 'id'=3",

            //operators
            "INSERT VALUES 'lastName''keks'",
            "INSERT VALUES 'lastName'=='keks'",
            "INSERT VALUES 'lastName'>'keks'",
            "INSERT VALUES 'lastName'!='keks'",
            "INSERT VALUES 'lastName' LIKE 'keks'",
            "UPDATE VALUES 'id'=3 WHERE 'age'4",
            "UPDATE VALUES 'id'=3 WHERE 'age'=>4",
            "UPDATE VALUES 'id'=3 WHERE 'age'>==4",
            "UPDATE VALUES 'id'=3 WHERE 'age'=<4",
            "UPDATE VALUES 'id'=3 WHERE 'age'==4",
            "UPDATE VALUES 'id'=3 WHERE 'age'!==4",
            "UPDATE VALUES 'id'=3 WHERE 'age'=!4",
            "UPDATE VALUES 'id'=3 WHERE 'age'=!=4",
            "UPDATE VALUES 'id'=3 WHERE 'age'=!=4",

            //syntax
            "VALUES",
            "VALUES 'id'=3",
            "WHERE 'age' > 4",
            "VALUES 'id'=3 WHERE 'age' > 4",
            "INSERT VALUES'lastName'='keks'",
            "IN SERT VALUES 'lastName'='Федоров'",
            "INSERT VALU ES 'lastName'='Федоров'",
            "INSErtVALUES 'lastName' = 'Федоров'",
            "INSERT VALUES 'lastName'='keks', 'lastName'='kek'",

            //delimiter
            "INSERT VALUES 'lastName'='keks' 'id'=3",
            "INSERT VALUES 'lastName'='keks' AND 'id'=3",
            "UPDATE VALUES 'lastName'='keks' AND 'age'=3 WHERE 'id'=3",
            "UPDATE VALUES 'lastName'='keks' WHERE 'id'=3 'age'=4",
            "UPDATE VALUES 'lastName'='keks' WHERE 'id'=3, 'age'=4",

            //string
            "INSERT VALUES 'lastName'=",
            "INSERT VALUES 'lastName'=3",
            "INSERT VALUES 'lastName'=3.4",
            "INSERT VALUES 'lastName'=true",
            "INSERT VALUES 'lastName'=test",
            "INSERT VALUES 'lastName'='test",
            "INSERT VALUES 'lastName'=test'",
            "UPDATE VALUES 'lastName'=2",
//            "INSERT VALUES 'lastName'=''keks'",
//            "INSERT VALUES 'lastName'='keks''",

            //number
            "INSERT VALUES 'cost'=",
            "INSERT VALUES 'cost'=2",
            "INSERT VALUES 'cost'=true",
            "INSERT VALUES 'cost'='2'",
            "INSERT VALUES 'id'=",
            "INSERT VALUES 'id'=2.3",
            "INSERT VALUES 'id'=true",
            "INSERT VALUES 'id'='2'",
            "INSERT VALUES 'cost'=3.4.2",
            "INSERT VALUES 'cost'=3.4.",
            "INSERT VALUES 'cost'=3.4,",
            "INSERT VALUES 'cost'=.2.2",
            "INSERT VALUES 'cost'=3.4 2",
            "INSERT VALUES 'cost'=-.",
            "INSERT VALUES 'id'=-",
            "UPDATE VALUES 'id'=2.2",

            //boolean
            "INSERT VALUES 'active'=",
            "INSERT VALUES 'active'=TRUE",
            "INSERT VALUES 'active'=FALSE",
            "INSERT VALUES 'active'='true'",
            "INSERT VALUES 'active'=3",
            "INSERT VALUES 'active'=3.4",
            "UPDATE VALUES 'active'='true'",

            //field name
            "INSERT VALUES ''lastName'='keks'",
            "INSERT VALUES 'lastName''='keks'",
            "INSERT VALUES 'name'='keks'",
            "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4, 'hello'='test'",
            "UPDATE VALUES 'lastName'='keks' WHERE 'name'='test'",
            "UPDATE VALUES 'name'='keks'",
            "UPDATE VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4, 'hello'='test'",
            "SELECT WHERE 'name'='keks'",
            "SELECT WHERE 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4, 'hello'='test'",
            "DELETE WHERE 'name'='keks'",
            "DELETE WHERE 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true, 'cost'=3.4, 'hello'='test'"
    })
    void syntax_exception(String query) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        assertThrows(Exception.class, () -> starter.execute(query));
    }
}
