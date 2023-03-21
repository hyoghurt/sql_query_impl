package com.digdes.school;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        try {
            List<Map<String,Object>> result1 = starter
                    .execute("INSErt VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true");

//            System.out.println(result1);

            List<Map<String,Object>> result2 = starter
                    .execute("INSErt VALUES 'lastName' = 'kekes' , 'cost'=4.5, 'id'=23, 'age'=90, 'active'=false");

            List<Map<String,Object>> result3 = starter
                    .execute("SELECT");

            System.out.println(result3);
            System.out.println("--------------------------------");

            List<Map<String,Object>> result4 = starter
                    .execute("SELECT WHERE 'active' = true");

            System.out.println(result4);
//            List<Map<String,Object>> result2 = starter
//                    .execute("UPDATE VALUES 'active'=false, 'cost'=10.1 where 'id'=3");
//
//            List<Map<String,Object>> result3 = starter
//                    .execute("SELECT");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}