package com.digdes.school;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsertExceptionTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "INSERT",
            "INSERT 'lastName'='keks'",
            "INSERT VALUES",
            "INSERT WHERE",
            "INSERT WHERE 'lastName'='keks'",
            "INSERT VALUES 'lastName'='keks' VALUES 'age'=23",
            "INSERT VALUES WHERE 'age'>3",
            "INSERT VALUES 'lastName'='keks' WHERE 'id'=3",
            "INSERT VALUES 'lastName'='keks', 'lastName'='kek'",
    })
    void insert_exception(String query) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        assertThrows(Exception.class, () -> starter.execute(query));
    }
}
