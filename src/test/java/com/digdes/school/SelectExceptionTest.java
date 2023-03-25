package com.digdes.school;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SelectExceptionTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "SELECT WHERE",
            "SELECT WHERE VALUES",
            "SELECT VALUES 'id'=3",
            "SELECT VALUES WHERE 'age'>3",
            "SELECT VALUES 'id'=3 WHERE 'age'>3",
            "SELECT WHERE 'id'=3 WHERE 'age'>3",
            "SELECT WHERE WHERE 'age'>3",
    })
    void select_exception(String query) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        assertThrows(Exception.class, () -> starter.execute(query));
    }
}
