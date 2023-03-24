package com.digdes.school;

import java.util.*;
import java.util.stream.Stream;

public class Base {

    protected static JavaSchoolStarter starter;
    protected static List<Map<String, Object>> list;
    static Random rd = new Random();
    static int maxRange = 10;


    static {
        list = new ArrayList<>();
        starter = new JavaSchoolStarter();
        String[] names = new String[] {
                "",
                "est",
                "tes",
                "test",
                "tEst",
                "ADteAst",

                "ADtestAD",
                "AtestAD",
                "testAD",
                "estAD",
                "estADtest",
                "testestAD",

                "ADtestAD",
                "ADtestA",
                "ADtest",
                "ADtes",
                "testADtes",
                "ADtestest",

                "BCTESTBC",
                "CTESTBC",
                "TESTBC",
                "ESTBC",
                "TESTESTBC",
                "ESTBCTEST",

                "BCTESTBC",
                "BCTESTB",
                "BCTEST",
                "BCTES",
                "BCTESTEST",
                "TESTBCTES",
        };


        Arrays.stream(names)
                .map(v -> newItem(
                        v,
                        getNullOrRandomLong(),
                        getNullOrRandomLong(),
                        getNullOrRandomDouble(),
                        getNullOrRandomBoolean()))
                .peek(objects -> list.add(newMapForList(objects)))
                .forEach(objects -> starter.execute(createInsertQuery(objects)));

        createStreamLong()
                .map(v -> newItem(
                        getNullOrRandomString(names),
                        v,
                        getNullOrRandomLong(),
                        getNullOrRandomDouble(),
                        getNullOrRandomBoolean()))
                .peek(objects -> list.add(newMapForList(objects)))
                .forEach(objects -> starter.execute(createInsertQuery(objects)));

        createStreamLong()
                .map(v -> newItem(
                        getNullOrRandomString(names),
                        getNullOrRandomLong(),
                        v,
                        getNullOrRandomDouble(),
                        getNullOrRandomBoolean()))
                .peek(objects -> list.add(newMapForList(objects)))
                .forEach(objects -> starter.execute(createInsertQuery(objects)));

        createStreamDouble()
                .map(v -> newItem(
                        getNullOrRandomString(names),
                        getNullOrRandomLong(),
                        getNullOrRandomLong(),
                        getDoublePrecision(v),
                        getNullOrRandomBoolean()))
                .peek(objects -> list.add(newMapForList(objects)))
                .forEach(objects -> starter.execute(createInsertQuery(objects)));
    }

    public static Double getDoublePrecision(Double d) {
        return Math.round(d * 10000.0) / 10000.0;
    }

    public static Stream<Long> createStreamLong() {
        return Stream.iterate(-2L, n -> n + 1L)
                .limit(10);
    }

    public static Stream<Double> createStreamDouble() {
        return Stream.iterate(-2.0, n -> n + 0.1)
                .limit(41);
    }

    static Object nullOrNot(Object obj) {
        if (rd.nextBoolean()) {
            return obj;
        }
        return null;
    }

    public static int randIntRange(int min, int max) {
        return rd.nextInt((max - min) + 1) + min;
    }

    private static Long getNullOrRandomLong() {
        return (Long) nullOrNot(randLongRange(-maxRange, maxRange));
    }

    private static Double getNullOrRandomDouble() {
        return (Double) nullOrNot(getDoublePrecision(-maxRange + (maxRange + maxRange) * rd.nextDouble()));
    }

    private static Boolean getNullOrRandomBoolean() {
        return (Boolean) nullOrNot(rd.nextBoolean());
    }

    private static String getNullOrRandomString(String[] names) {
        return (String) nullOrNot(names[randIntRange(0, names.length - 1)]);
    }


    public static Long randLongRange(int min, int max) {
        return (long) (rd.nextInt((max - min) + 1) + min);
    }

    public static Map<String, Object> newMapForList(Object[] objects) {
        String lastName = (String) objects[0];
        Long id = (Long) objects[1];
        Long age = (Long) objects[2];
        Double cost = (Double) objects[3];
        Boolean active = (Boolean) objects[4];

        return newMap(lastName, id, age, cost, active);
    }

    public static Object[] newItem(String lastName, Long id, Long age, Double cost, Boolean active) {
        return new Object[] {lastName, id, age, cost, active};
    }

    public static String createInsertQuery(Object[] objects) {
        String lastName = (String) objects[0];
        Long id = (Long) objects[1];
        Long age = (Long) objects[2];
        Double cost = (Double) objects[3];
        Boolean active = (Boolean) objects[4];

        StringBuilder query = new StringBuilder();

        if (lastName != null) {
            lastName = "'" + lastName + "'";
        }

        query.append(String
                .format("INSERT VALUES 'lastName' = %s, 'id' = %d, 'age' = %d, 'cost' = %.4f, 'active' = ",
                        lastName, id, age, cost));

        if (active == null) {
            query.append("null");
        } else {
            query.append(String.format("%b", active));
        }

        return query.toString();
    }

    public static Map<String, Object> newMap(String lastName, Long id, Long age, Double cost, Boolean active) {
        Map<String, Object> map = new HashMap<>();

        if (lastName != null) {
            map.put("lastName", lastName);
        }
        if (id != null) {
            map.put("id", id);
        }
        if (age != null) {
            map.put("age", age);
        }
        if (active != null) {
            map.put("active", active);
        }
        if (cost != null) {
            map.put("cost", cost);
        }

        return map;
    }
}