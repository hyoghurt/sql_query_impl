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

public class DeleteTest extends OperatorBase {

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
    void delete_test() {
        Map<Boolean, List<Map<String, Object>>> expected = list.stream()
                .collect(Collectors.partitioningBy(map -> new GreaterFilter().filter(map, "id", 3L)));

        String query = "DELETE WHERE 'id' > 3";
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected.get(true), actual);

        query = "SELECT";
        List<Map<String, Object>> actualSelect = starter.execute(query);
        assertEquals(expected.get(false), actualSelect);
    }

    @Test
    void delete_all_test() {
        String query = "DELETE";
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(list, actual);

        query = "SELECT";
        List<Map<String, Object>> actualSelect = starter.execute(query);
        assertEquals(new ArrayList<>(), actualSelect);
    }
}
