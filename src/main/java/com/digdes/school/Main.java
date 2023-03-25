package com.digdes.school;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        try {

            List<Map<String,Object>> result = starter
                    .execute("INSErt VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true");
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}