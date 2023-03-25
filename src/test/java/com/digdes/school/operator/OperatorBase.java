package com.digdes.school.operator;

import com.digdes.school.Base;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OperatorBase extends Base {

    public static List<Map<String, Object>> filter(String key, Object val, MyFilter myFilter) {
        return list.stream()
                .filter(e -> myFilter.filter(e, key, val))
                .collect(Collectors.toList());
    }

    public static class GreaterFilter implements MyFilter {
        @Override
        public boolean filter(Map<String, Object> map, String key, Object val) {
            Object o = map.get(key);
            if (o == null) {
                return false;
            }

            if (o instanceof Long) {
                if (val instanceof Double) {
                    return (long) o > (long) (double) val;
                }
                return (long) o > (long) val;
            }

            if (val instanceof Long) {
                return (double) o > (double) (long) val;
            }
            return (double) o > (double) val;
        }
    }

    public static class GreaterOrEqualFilter implements MyFilter {
        @Override
        public boolean filter(Map<String, Object> map, String key, Object val) {
            Object o = map.get(key);
            if (o == null) {
                return false;
            }

            if (o instanceof Long) {
                if (val instanceof Double) {
                    return (long) o >= (long) (double) val;
                }
                return (long) o >= (long) val;
            }

            if (val instanceof Long) {
                return (double) o >= (double) (long) val;
            }
            return (double) o >= (double) val;
        }
    }

    public static class LessFilter implements MyFilter {
        @Override
        public boolean filter(Map<String, Object> map, String key, Object val) {
            Object o = map.get(key);
            if (o == null) {
                return false;
            }

            if (o instanceof Long) {
                if (val instanceof Double) {
                    return (long) o < (long) (double) val;
                }
                return (long) o < (long) val;
            }

            if (val instanceof Long) {
                return (double) o < (double) (long) val;
            }
            return (double) o < (double) val;
        }
    }

    public static class LessOrEqualFilter implements MyFilter {
        @Override
        public boolean filter(Map<String, Object> map, String key, Object val) {
            Object o = map.get(key);
            if (o == null) {
                return false;
            }

            if (o instanceof Long) {
                if (val instanceof Double) {
                    return (long) o <= (long) (double) val;
                }
                return (long) o <= (long) val;
            }

            if (val instanceof Long) {
                return (double) o <= (double) (long) val;
            }
            return (double) o <= (double) val;
        }
    }


    public static class EqualFilter implements MyFilter {
        @Override
        public boolean filter(Map<String, Object> map, String key, Object val) {
            return map.get(key) != null && map.get(key).equals(val);
        }
    }

    public static class NotEqualFilter implements MyFilter {
        @Override
        public boolean filter(Map<String, Object> map, String key, Object val) {
            return map.get(key) != null && !map.get(key).equals(val);
        }
    }

    public static class LikeStartFilter implements MyFilter {
        @Override
        public boolean filter(Map<String, Object> map, String key, Object val) {
            if (map.get(key) != null) {
                String s = (String) map.get(key);
                return s.startsWith((String) val);
            }
            return false;
        }
    }

    public static class LikeEndFilter implements MyFilter {
        @Override
        public boolean filter(Map<String, Object> map, String key, Object val) {
            if (map.get(key) != null) {
                String s = (String) map.get(key);
                return s.endsWith((String) val);
            }
            return false;
        }
    }

    public static class LikeContainsFilter implements MyFilter {
        @Override
        public boolean filter(Map<String, Object> map, String key, Object val) {
            if (map.get(key) != null) {
                String s = (String) map.get(key);
                return s.contains((String) val);
            }
            return false;
        }
    }

    public static class ILikeStartFilter implements MyFilter {
        @Override
        public boolean filter(Map<String, Object> map, String key, Object val) {
            if (map.get(key) != null) {
                String s = (String) map.get(key);
                String sVal = (String) val;
                return s.toUpperCase().startsWith(sVal.toUpperCase());
            }
            return false;
        }
    }

    public static class ILikeEndFilter implements MyFilter {
        @Override
        public boolean filter(Map<String, Object> map, String key, Object val) {
            if (map.get(key) != null) {
                String s = (String) map.get(key);
                String sVal = (String) val;
                return s.toUpperCase().endsWith(sVal.toUpperCase());
            }
            return false;
        }
    }

    public static class ILikeContainsFilter implements MyFilter {
        @Override
        public boolean filter(Map<String, Object> map, String key, Object val) {
            if (map.get(key) != null) {
                String s = (String) map.get(key);
                String sVal = (String) val;
                return s.toUpperCase().contains(sVal.toUpperCase());
            }
            return false;
        }
    }

    public static String getQueryWithOperator(String name, String operator, String value) {
        return String.format("SELECT WHERE '%s' %s %s", name, operator, value);
    }
}