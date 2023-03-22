package com.digdes.school;

import com.digdes.school.exceptions.TypeErrorException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SelectWhereGreaterExceptionTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "SELECT WHERE 'id'>'2'",
            "SELECT WHERE 'id'>true",
            "SELECT WHERE 'cost'>'4'",
            "SELECT WHERE 'cost'>true",
            "SELECT WHERE 'lastName'>4",
            "SELECT WHERE 'lastName'>4.4",
            "SELECT WHERE 'lastName'>true",
            "SELECT WHERE 'lastName'>'keks'",
            "SELECT WHERE 'active'>4",
            "SELECT WHERE 'active'>4.4",
            "SELECT WHERE 'active'>'keks'",
            "SELECT WHERE 'active'>true",
    })
    void select_where_type_exception_greater(String query) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        assertThrows(TypeErrorException.class, () -> starter.execute(query));
    }
}
