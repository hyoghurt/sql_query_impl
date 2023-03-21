package com.digdes.school;

import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.exceptions.TypeErrorException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InsertParserTest {

    private static List<Map<String, Object>> getMaps() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("lastName", "keks");
        map.put("id", 3L);
        map.put("age", 40L);
        map.put("active", true);
        map.put("cost", 2.3);
        list.add(map);
        return list;
    }

    @Test
    void insert_parser_success_12() {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        List<Map<String, Object>> list = getMaps();

        //TODO true UPPER case ?

        List<Map<String,Object>> result = starter
                .execute("INSErt VALUES 'lastName' = 'keks' , 'ID'=3, 'COST'=2.3, 'Age'=40, 'acTIVE'=true");
        assertEquals(list, result);
    }

    @Test
    void insert_parser_success_11() {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        List<Map<String, Object>> list = getMaps();

        List<Map<String,Object>> result = starter
                .execute("INSErt VALUES 'LASTname' = 'keks' , 'id'=3, 'cost'=2.3, 'age'=40, 'active'=true");
        assertEquals(list, result);
    }

    @Test
    void insert_parser_success_1() {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        List<Map<String, Object>> list = getMaps();

        List<Map<String,Object>> result = starter
                .execute("INSErt VALUES 'lastName' = 'keks' , 'id'=3, 'cost'=2.3, 'age'=40, 'active'=true");
        assertEquals(list, result);
    }

    @Test
    void insert_parser_success_2() {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        List<Map<String, Object>> list = getMaps();

        List<Map<String,Object>> result = starter
                .execute("INSErt VAlUES 'lastName' = 'keks' , 'id'  = 3  , 'cost' =  2.3 , 'age' = 40 , 'active' = true  ");
        assertEquals(list, result);
    }

    @Test
    void insert_parser_success_3() {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        List<Map<String, Object>> list = getMaps();

        List<Map<String,Object>> result = starter
                .execute("INSErt VAlUES 'lastName'='keks','id'=3,'cost'=2.3,'age'=40,'active'=true");
        assertEquals(list, result);
    }

    @Test
    void insert_parser_exception_1() {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        assertThrows(SyntaxErrorException.class, () -> {
                    starter.execute("INSErtVALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true");
        });
    }
    @Test
    void insert_parser_exception_2() {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSERT VALUES'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true");
        });
    }

    @Test
    void insert_parser_exception_3() {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        assertThrows(TypeErrorException.class, () -> {
            starter.execute("INSERT VALUES 'lastName' = Федоров , 'id'=3, 'age'=40, 'active'=true");
        });
    }

    @Test
    void insert_parser_exception_id_is_string_4() {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        assertThrows(TypeErrorException.class, () -> {
            starter.execute("INSERT VALUES 'lastName' = 'Федоров' , 'id'='3', 'age'=40, 'active'=true");
        });
    }

    @Test
    void insert_parser_exception_5() {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSErtVALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=t");
        });
    }

    @Test
    void insert_parser_exception_6() {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSErtVALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'='true'");
        });
    }
    @Test
    void insert_parser_exception_assignment_error() {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        assertThrows(TypeErrorException.class, () -> {
            starter.execute("INSERT VALUES 'lastName' = 'Федоров' , 'id'==3, 'age'=40, 'active'=true");
        });
    }

    @Test
    void insert_parser_exception_delimiter() {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSERT VALUES 'lastName' = 'Федоров' , 'id'=3 'age'=40, 'active'=true");
        });
    }

    @Test
    void insert_parser_exception_7() {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSERT VALUES 'lastName' = 'Федоров' , 'id'=, 'age'=40, 'active'=true");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSERT VALUES 'lastName' = 'Федоров' , 'id'=3 'age'=40, 'active'=true");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true,");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("IN SERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSERT VALU ES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSERT WHERE 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSERT VALUES ");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSERT  ");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSERT");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("");
        });

        assertThrows(SyntaxErrorException.class, () -> {
            starter.execute("INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true WHERE 'lol'=true");
        });
    }
}