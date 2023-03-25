package com.digdes.school;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteExceptionTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "DELETE WHERE",
            "DELETE WHERE VALUES",
            "DELETE VALUES",
            "DELETE VALUES 'id'=3 WHERE 'age'>3",
            "DELETE VALUES 'id'=3",
            "DELETE WHERE 'id'=3 WHERE 'age'>3",
            "DELETE WHERE WHERE 'age'>3",
            "DELETE WHERE 'id'=3, 'id'>3",
            "DELETE INSERT VALUES 'id'=3",
    })
    void delete_exception(String query) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        assertThrows(Exception.class, () -> starter.execute(query));
    }
}
