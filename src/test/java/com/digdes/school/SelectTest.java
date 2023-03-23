package com.digdes.school;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectTest extends Base{

    @Test
    void all() {
        List<Map<String, Object>> expected = list;
        List<Map<String, Object>> actual = starter.execute("SELECT");
        assertEquals(expected, actual);
    }
}