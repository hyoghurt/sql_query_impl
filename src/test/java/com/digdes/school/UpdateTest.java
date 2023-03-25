package com.digdes.school;

import com.digdes.school.operator.OperatorBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateTest extends OperatorBase {

    JavaSchoolStarter starter;
    List<Map<String, Object>> list;

    @BeforeEach
    void init() {
        starter = new JavaSchoolStarter();
        list = new ArrayList<>();

        Arrays.stream(names)
                .map(v -> newItem(
                        v,
                        getNullOrRandomLong(),
                        getNullOrRandomLong(),
                        getNullOrRandomDouble(),
                        getNullOrRandomBoolean()))
                .peek(objects -> list.add(newMapForList(objects)))
                .forEach(objects -> starter.execute(createInsertQuery(objects)));
    }

    @Test
    void update_test() {
        List<Map<String, Object>> expected = list.stream()
                .filter(map -> new GreaterFilter().filter(map, "id", 0L))
                .peek(map -> map.put("age", 4L))
                .collect(Collectors.toList());

        String query = "UPDATE VALUES 'age'=4  WHERE 'id' > 0";
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);

        query = "SELECT";
        List<Map<String, Object>> actualSelect = starter.execute(query);
        assertEquals(list, actualSelect);
    }

    @Test
    void update_all_test() {
        List<Map<String, Object>> expected = list.stream()
                .peek(map -> map.put("lastName", "default"))
                .collect(Collectors.toList());

        String query = "UPDATE VALUES 'LASTNAME'='default'";
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);

        query = "SELECT";
        List<Map<String, Object>> actualSelect = starter.execute(query);
        assertEquals(list, actualSelect);
    }

}
