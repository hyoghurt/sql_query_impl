package com.digdes.school;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateExceptionTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "UPDATE WHERE",
            "UPDATE WHERE VALUES",
            "UPDATE VALUES",
            "UPDATE VALUES WHERE 'age'>3",
            "UPDATE WHERE 'id'=3 WHERE 'age'>3",
            "UPDATE WHERE WHERE 'age'>3",
            "UPDATE WHERE 'id'=3, 'id'>3",
            "UPDATE WHERE 'id'>3",
            "UPDATE INSERT VALUES 'id'=3",
    })
    void update_exception(String query) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        assertThrows(Exception.class, () -> starter.execute(query));
    }
}
