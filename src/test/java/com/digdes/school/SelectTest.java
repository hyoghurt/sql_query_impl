package com.digdes.school;

import com.digdes.school.operator.OperatorBase;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectTest extends OperatorBase {

    @Test
    void select_test() {
        List<Map<String, Object>> expected = list.stream()
                .filter(map -> new GreaterFilter().filter(map, "id", 0L))
                .collect(Collectors.toList());

        String query = "SELECT WHERE 'id' > 0";
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);
    }

    @Test
    void select_all_test() {
        String query = "SELECT";
        List<Map<String, Object>> actualSelect = starter.execute(query);
        assertEquals(list, actualSelect);
    }

}
