package com.digdes.school.operator;

import com.digdes.school.JavaSchoolStarter;
import com.digdes.school.exceptions.TypeErrorException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LikeILikeExceptionTest extends OperatorBase {

    @ParameterizedTest
    @MethodSource
    void type_exception(String query) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        assertThrows(TypeErrorException.class, () -> starter.execute(query));
    }

    private static Stream<Arguments> type_exception() {
        String[] operators = new String[] {"LIKE", "ILIKE"};

        List<String[]> list = new ArrayList<>();
        Arrays.stream(operators).forEach(o -> {
                    list.add(new String[]{"lastName", o, "2"});
                    list.add(new String[]{"lastName", o, "2.2"});
                    list.add(new String[]{"lastName", o, "false"});
                    list.add(new String[]{"id", o, "2"});
                    list.add(new String[]{"id", o, "2.2"});
                    list.add(new String[]{"id", o, "false"});
                    list.add(new String[]{"id", o, "'test'"});
                    list.add(new String[]{"age", o, "2"});
                    list.add(new String[]{"age", o, "2.2"});
                    list.add(new String[]{"age", o, "false"});
                    list.add(new String[]{"age", o, "'test'"});
                    list.add(new String[]{"cost", o, "2"});
                    list.add(new String[]{"cost", o, "2.2"});
                    list.add(new String[]{"cost", o, "false"});
                    list.add(new String[]{"cost", o, "'test'"});
                    list.add(new String[]{"active", o, "2"});
                    list.add(new String[]{"active", o, "2.2"});
                    list.add(new String[]{"active", o, "false"});
                    list.add(new String[]{"active", o, "'test'"});
                }
        );

        return list.stream().map(arr -> Arguments.of(getQueryWithOperator(arr[0], arr[1], arr[2])));
    }
}
