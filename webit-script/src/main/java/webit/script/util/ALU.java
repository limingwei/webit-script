package webit.script.util;

import webit.script.Context;
import webit.script.core.ast.Expression;
import webit.script.exceptions.ScriptRuntimeException;

/**
 *
 * @author Zqq
 */
public class ALU {

    public static final int NULL = 0;
    public static final int OBJECT = 1;
    public static final int STRING = 2;
    public static final int DOUBLE = 3;
    public static final int FLOAT = 4;
    public static final int LONG = 5;
    public static final int INTEGER = 6;
    public static final int SHORT = 7;
    public static final int CHAR = 8;
    public static final int BYTE = 9;

    public static int getBaseType(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            Class cls1 = o1.getClass();
            Class cls2 = o2.getClass();

            if (cls1 == String.class || cls2 == String.class) {
                return STRING;
            } //
            else if (cls1 == Character.class && cls2 == Character.class) {
                return CHAR;
            } //
            if (o1 instanceof Number && o2 instanceof Number) {
                if (cls1 == Double.class || cls2 == Double.class) {
                    return DOUBLE;
                }//
                else if (cls1 == Float.class || cls2 == Float.class) {
                    return FLOAT;
                } //
                else if (cls1 == Long.class || cls2 == Long.class) {
                    return LONG;
                } //
                else if (cls1 == Integer.class || cls2 == Integer.class) {
                    return INTEGER;
                } //
                else if (cls1 == Short.class || cls2 == Short.class) {
                    return SHORT;
                } //
                else if (cls1 == Byte.class || cls2 == Byte.class) {
                    return BYTE;
                } //
            }

            //Note: default to String
            return STRING;
        }
        return NULL;
    }

    public static int getNumberType(Number o1) {
        if (o1 != null) {
            Class cls = o1.getClass();
            if (cls == Integer.class) {
                return INTEGER;
            } else if (cls == Long.class) {
                return LONG;
            } else if (cls == Short.class) {
                return SHORT;
            } else if (cls == Float.class) {
                return FLOAT;
            } else if (cls == Double.class) {
                return DOUBLE;
            }
        }
        return NULL;
    }

    public static int getBaseType(Object o1) {
        if (o1 != null) {
            Class cls = o1.getClass();
            if (cls == String.class) {
                return STRING;
            } else if (cls == Integer.class) {
                return INTEGER;
            } else if (cls == Long.class) {
                return LONG;
            } else if (cls == Short.class) {
                return SHORT;
            } else if (cls == Float.class) {
                return FLOAT;
            } else if (cls == Double.class) {
                return DOUBLE;
            } else if (cls == Character.class) {
                return CHAR;
            } else if (cls == Byte.class) {
                return BYTE;
            } else {
                return OBJECT;
            }
        }
        return NULL;
    }

    //+1
    public static Object plusOne(Object o1) {
        if (o1 != null) {
            if (o1 instanceof Number) {
                Number num = (Number) o1;
                switch (getNumberType(num)) {
                    case INTEGER:
                        return Integer.valueOf(num.intValue() + 1);
                    case LONG:
                        return Long.valueOf(num.longValue() + 1L);
                    case DOUBLE:
                        return Double.valueOf(num.doubleValue() + 1D);
                    case FLOAT:
                        return Float.valueOf(num.floatValue() + 1F);
                    case SHORT:
                        return Short.valueOf((short) (num.intValue() + 1));
                }
            } else {
                throw new ScriptRuntimeException("value not a number");
            }
        }
        throw ValueIsNullException(o1);
    }
    // -1

    public static Object minusOne(Object o1) {
        if (o1 != null) {
            if (o1 instanceof Number) {
                Number num = (Number) o1;
                switch (getNumberType(num)) {
                    case INTEGER:
                        return Integer.valueOf(num.intValue() - 1);
                    case LONG:
                        return Long.valueOf(num.longValue() - 1l);
                    case DOUBLE:
                        return Double.valueOf(num.doubleValue() - 1d);
                    case FLOAT:
                        return Float.valueOf(num.floatValue() - 1f);
                    case SHORT:
                        return Short.valueOf((short) (num.intValue() - 1));
                }
            } else {
                throw new ScriptRuntimeException("value not a number");
            }
        }
        throw ValueIsNullException(o1);
    }
    /////////////////////////////
    //+

    public static Object plus(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            int type = getBaseType(o1, o2);
            switch (type) {
                case STRING:
                    return String.valueOf(o1) + String.valueOf(o2);
                case INTEGER:
                    return Integer.valueOf(((Number) o1).intValue() + ((Number) o2).intValue());
                case LONG:
                    return Long.valueOf(((Number) o1).longValue() + ((Number) o2).longValue());
                case DOUBLE:
                    return Double.valueOf(((Number) o1).doubleValue() + ((Number) o2).doubleValue());
                case FLOAT:
                    return Float.valueOf(((Number) o1).floatValue() + ((Number) o2).floatValue());
                case SHORT:
                    return Short.valueOf((short) (((Number) o1).intValue() + ((Number) o2).intValue()));
                case CHAR:
                    return Character.valueOf((char) (((Number) o1).intValue() + ((Number) o2).intValue()));
                case BYTE:
                    return Byte.valueOf((byte) (((Number) o1).intValue() + ((Number) o2).intValue()));
                default:
                    throw UnsupportedTypeException(o1, o2);
            }
        } else {
            return o1 != null ? o1 : o2;
        }
    }
    //-

    public static Object minus(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            int type = getBaseType(o1, o2);
            switch (type) {
                //case STRING:
                //return String.valueOf(o1) + String.valueOf(o2);
                case INTEGER:
                    return Integer.valueOf(((Number) o1).intValue() - ((Number) o2).intValue());
                case LONG:
                    return Long.valueOf(((Number) o1).longValue() - ((Number) o2).longValue());
                case DOUBLE:
                    return Double.valueOf(((Number) o1).doubleValue() - ((Number) o2).doubleValue());
                case FLOAT:
                    return Float.valueOf(((Number) o1).floatValue() - ((Number) o2).floatValue());
                case SHORT:
                    return Short.valueOf((short) (((Number) o1).intValue() - ((Number) o2).intValue()));
                case CHAR:
                    return Character.valueOf((char) (((Number) o1).intValue() - ((Number) o2).intValue()));
                case BYTE:
                    return Byte.valueOf((byte) (((Number) o1).intValue() - ((Number) o2).intValue()));
                default:
                    throw UnsupportedTypeException(o1, o2);
            }
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }

    // 负
    public static Object negative(Object o1) {
        if (o1 != null) {
            int type = getBaseType(o1);
            switch (type) {
                //case STRING:
                //return String.valueOf(o1) + String.valueOf(o2);
                case INTEGER:
                    return -((Number) o1).intValue();
                case LONG:
                    return -((Number) o1).longValue();
                case DOUBLE:
                    return -((Number) o1).doubleValue();
                case FLOAT:
                    return -((Number) o1).floatValue();
                case SHORT:
                    return -((Number) o1).shortValue();
                //case CHAR:
                //    return Character.valueOf((char) (((Number) o1).intValue() * ((Number) o2).intValue()));
                //case BYTE:
                //    return Byte.valueOf((byte) (((Number) o1).intValue() * ((Number) o2).intValue()));
                default:
                    throw new ScriptRuntimeException("value not a number");
            }
        } else {
            throw ValueIsNullException(o1);
        }
    }
    //*

    public static Object mult(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            int type = getBaseType(o1, o2);
            switch (type) {
                //case STRING:
                //return String.valueOf(o1) + String.valueOf(o2);
                case INTEGER:
                    return Integer.valueOf(((Number) o1).intValue() * ((Number) o2).intValue());
                case LONG:
                    return Long.valueOf(((Number) o1).longValue() * ((Number) o2).longValue());
                case DOUBLE:
                    return Double.valueOf(((Number) o1).doubleValue() * ((Number) o2).doubleValue());
                case FLOAT:
                    return Float.valueOf(((Number) o1).floatValue() * ((Number) o2).floatValue());
                case SHORT:
                    return Short.valueOf((short) (((Number) o1).intValue() * ((Number) o2).intValue()));
                //case CHAR:
                //    return Character.valueOf((char) (((Number) o1).intValue() * ((Number) o2).intValue()));
                //case BYTE:
                //    return Byte.valueOf((byte) (((Number) o1).intValue() * ((Number) o2).intValue()));
                default:
                    throw UnsupportedTypeException(o1, o2);
            }
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }
    // /

    public static Object div(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            int type = getBaseType(o1, o2);
            switch (type) {
                //case STRING:
                //return String.valueOf(o1) + String.valueOf(o2);
                case INTEGER:
                    return Integer.valueOf(((Number) o1).intValue() / ((Number) o2).intValue());
                case LONG:
                    return Long.valueOf(((Number) o1).longValue() / ((Number) o2).longValue());
                case DOUBLE:
                    return Double.valueOf(((Number) o1).doubleValue() / ((Number) o2).doubleValue());
                case FLOAT:
                    return Float.valueOf(((Number) o1).floatValue() / ((Number) o2).floatValue());
                case SHORT:
                    return Short.valueOf((short) (((Number) o1).intValue() / ((Number) o2).intValue()));
                //case CHAR:
                //    return Character.valueOf((char) (((Number) o1).intValue() / ((Number) o2).intValue()));
                //case BYTE:
                //    return Byte.valueOf((byte) (((Number) o1).intValue() / ((Number) o2).intValue()));
                default:
                    throw UnsupportedTypeException(o1, o2);
            }
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }
    // %

    public static Object mod(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            int type = getBaseType(o1, o2);
            switch (type) {
                //case STRING:
                //return String.valueOf(o1) + String.valueOf(o2);
                case INTEGER:
                    return Integer.valueOf(((Number) o1).intValue() % ((Number) o2).intValue());
                case LONG:
                    return Long.valueOf(((Number) o1).longValue() % ((Number) o2).longValue());
                case DOUBLE:
                    return Double.valueOf(((Number) o1).doubleValue() % ((Number) o2).doubleValue());
                case FLOAT:
                    return Float.valueOf(((Number) o1).floatValue() % ((Number) o2).floatValue());
                case SHORT:
                    return Short.valueOf((short) (((Number) o1).intValue() % ((Number) o2).intValue()));
                //case CHAR:
                //    return Character.valueOf((char) (((Number) o1).intValue() % ((Number) o2).intValue()));
                //case BYTE:
                //    return Byte.valueOf((byte) (((Number) o1).intValue() % ((Number) o2).intValue()));
                default:
                    throw UnsupportedTypeException(o1, o2);
            }
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }
    ///////////////////////////

    /////////////////
    // &&
    public static boolean and(Object o1, Object o2) {
        return toBoolean(o1) && toBoolean(o2);
    }

    public static boolean and(Expression expr1, Expression expr2, Context context) {
        return toBoolean(StatmentUtil.execute(expr1, context)) && toBoolean(StatmentUtil.execute(expr2, context));
    }

    // ||
    public static boolean or(Object o1, Object o2) {
        return toBoolean(o1) || toBoolean(o2);
    }

    public static boolean or(Expression expr1, Expression expr2, Context context) {
        return toBoolean(StatmentUtil.execute(expr1, context)) || toBoolean(StatmentUtil.execute(expr2, context));
    }

    // !
    public static boolean not(Object o1) {
        return !toBoolean(o1);
    }

    // ==
    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (o1.equals(o2)) {
            return true;
        }
        if (o1 instanceof Number && o2 instanceof Number) {
            int type = getBaseType(o1, o2);
            switch (type) {
                //case STRING:
                //case CHAR:
                case BYTE:
                case SHORT:
                case INTEGER:
                    return ((Number) o1).intValue() == ((Number) o2).intValue();
                case LONG:
                    return ((Number) o1).longValue() == ((Number) o2).longValue();
                case DOUBLE:
                    return ((Number) o1).doubleValue() == ((Number) o2).doubleValue();
                case FLOAT:
                    return ((Number) o1).floatValue() == ((Number) o2).floatValue();
            }
        }

        return false;
    }
    // !=

    public static boolean notEquals(Object o1, Object o2) {
        return !equals(o1, o2);
    }

    // >
    public static boolean greater(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            int type = getBaseType(o1, o2);
            switch (type) {
                //case STRING:
                case CHAR:
                    int left = o1 instanceof Number ? ((Number) o1).intValue() : (int) ((Character) o1).charValue();
                    int right = o2 instanceof Number ? ((Number) o2).intValue() : (int) ((Character) o2).charValue();
                    return left > right;
                case BYTE:
                case SHORT:
                case INTEGER:
                    return ((Number) o1).intValue() > ((Number) o2).intValue();
                case LONG:
                    return ((Number) o1).longValue() > ((Number) o2).longValue();
                case DOUBLE:
                    return ((Number) o1).doubleValue() > ((Number) o2).doubleValue();
                case FLOAT:
                    return ((Number) o1).floatValue() > ((Number) o2).floatValue();
                default:
                    throw UnsupportedTypeException(o1, o2);
            }
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }
    // >=

    public static boolean greaterEquals(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            int type = getBaseType(o1, o2);
            switch (type) {
                //case STRING:
                case CHAR:
                    int left = o1 instanceof Number ? ((Number) o1).intValue() : (int) ((Character) o1).charValue();
                    int right = o2 instanceof Number ? ((Number) o2).intValue() : (int) ((Character) o2).charValue();
                    return left >= right;
                case BYTE:
                case SHORT:
                case INTEGER:
                    return ((Number) o1).intValue() >= ((Number) o2).intValue();
                case LONG:
                    return ((Number) o1).longValue() >= ((Number) o2).longValue();
                case DOUBLE:
                    return ((Number) o1).doubleValue() >= ((Number) o2).doubleValue();
                case FLOAT:
                    return ((Number) o1).floatValue() >= ((Number) o2).floatValue();
                default:
                    throw UnsupportedTypeException(o1, o2);
            }
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }
    // <

    public static boolean less(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            int type = getBaseType(o1, o2);
            switch (type) {
                //case STRING:
                case CHAR:
                    int left = o1 instanceof Number ? ((Number) o1).intValue() : (int) ((Character) o1).charValue();
                    int right = o2 instanceof Number ? ((Number) o2).intValue() : (int) ((Character) o2).charValue();
                    return left < right;
                case BYTE:
                case SHORT:
                case INTEGER:
                    return ((Number) o1).intValue() < ((Number) o2).intValue();
                case LONG:
                    return ((Number) o1).longValue() < ((Number) o2).longValue();
                case DOUBLE:
                    return ((Number) o1).doubleValue() < ((Number) o2).doubleValue();
                case FLOAT:
                    return ((Number) o1).floatValue() < ((Number) o2).floatValue();
                default:
                    throw UnsupportedTypeException(o1, o2);
            }
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }
    // <=

    public static boolean lessEquals(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            int type = getBaseType(o1, o2);
            switch (type) {
                //case STRING:
                case CHAR:
                    int left = o1 instanceof Number ? ((Number) o1).intValue() : (int) ((Character) o1).charValue();
                    int right = o2 instanceof Number ? ((Number) o2).intValue() : (int) ((Character) o2).charValue();
                    return left <= right;
                case BYTE:
                case SHORT:
                case INTEGER:
                    return ((Number) o1).intValue() <= ((Number) o2).intValue();
                case LONG:
                    return ((Number) o1).longValue() <= ((Number) o2).longValue();
                case DOUBLE:
                    return ((Number) o1).doubleValue() <= ((Number) o2).doubleValue();
                case FLOAT:
                    return ((Number) o1).floatValue() <= ((Number) o2).floatValue();
                default:
                    throw UnsupportedTypeException(o1, o2);
            }
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }

    // ****************************
    // &
    public static Object bitAnd(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            int type = getBaseType(o1, o2);
            switch (type) {
                //case STRING:
                case CHAR:
                    char left = o1 instanceof Number ? (char) ((Number) o1).intValue() : ((Character) o1).charValue();
                    char right = o2 instanceof Number ? (char) ((Number) o2).intValue() : ((Character) o2).charValue();
                    return left & right;
                case BYTE:
                    return ((Number) o1).byteValue() & ((Number) o2).byteValue();
                case SHORT:
                    return ((Number) o1).shortValue() & ((Number) o2).shortValue();
                case INTEGER:
                    return ((Number) o1).intValue() & ((Number) o2).intValue();
                case LONG:
                    return ((Number) o1).longValue() & ((Number) o2).longValue();
                //case DOUBLE:
                //case FLOAT:
                default:
                    throw UnsupportedTypeException(o1, o2);
            }
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }
    // |

    public static Object bitOr(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            int type = getBaseType(o1, o2);
            switch (type) {
                //case STRING:
                case CHAR:
                    char left = o1 instanceof Number ? (char) ((Number) o1).intValue() : ((Character) o1).charValue();
                    char right = o2 instanceof Number ? (char) ((Number) o2).intValue() : ((Character) o2).charValue();
                    return left | right;
                case BYTE:
                    return ((Number) o1).byteValue() | ((Number) o2).byteValue();
                case SHORT:
                    return ((Number) o1).shortValue() | ((Number) o2).shortValue();
                case INTEGER:
                    return ((Number) o1).intValue() | ((Number) o2).intValue();
                case LONG:
                    return ((Number) o1).longValue() | ((Number) o2).longValue();
                //case DOUBLE:
                //case FLOAT:
                default:
                    throw UnsupportedTypeException(o1, o2);
            }
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }
    // ^ XOR

    public static Object bitXor(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            int type = getBaseType(o1, o2);
            switch (type) {
                //case STRING:
                case CHAR:
                    char left = o1 instanceof Number ? (char) ((Number) o1).intValue() : ((Character) o1).charValue();
                    char right = o2 instanceof Number ? (char) ((Number) o2).intValue() : ((Character) o2).charValue();
                    return left ^ right;
                case BYTE:
                    return ((Number) o1).byteValue() ^ ((Number) o2).byteValue();
                case SHORT:
                    return ((Number) o1).shortValue() ^ ((Number) o2).shortValue();
                case INTEGER:
                    return ((Number) o1).intValue() ^ ((Number) o2).intValue();
                case LONG:
                    return ((Number) o1).longValue() ^ ((Number) o2).longValue();
                //case DOUBLE:
                //case FLOAT:
                default:
                    throw UnsupportedTypeException(o1, o2);
            }
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }

    // ~ 
    public static Object bitNot(Object o1) {
        if (o1 != null) {
            int type = getBaseType(o1);
            switch (type) {
                //case STRING:
                //case CHAR:
                case BYTE:
                    return ~((Number) o1).byteValue();
                case SHORT:
                    return ~((Number) o1).shortValue();
                case INTEGER:
                    return ~((Number) o1).intValue();
                case LONG:
                    return ~((Number) o1).longValue();
                //case DOUBLE:
                //case FLOAT:
                default:
                    throw new ScriptRuntimeException("unsupported type:" + o1.getClass().getName());
            }
        } else {
            throw ValueIsNullException(o1);
        }
    }

    //*****************
    // <<
    public static Object lshift(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            if (o2 instanceof Number) {
                int right = ((Number) o2).intValue();
                int type = getBaseType(o1);
                switch (type) {
                    //case STRING:
                    case CHAR:
                        return ((Character) o1).charValue() << right;
                    case BYTE:
                        return ((Byte) o1).byteValue() << right;
                    case SHORT:
                        return ((Short) o1).shortValue() << right;
                    case INTEGER:
                        return ((Integer) o1).intValue() << right;
                    case LONG:
                        return ((Long) o1).longValue() << right;
                    //case DOUBLE:
                    //case FLOAT:
                    default:
                        throw new ScriptRuntimeException("left value type is unsupported:" + o1.getClass().getName());
                }
            }
            throw new ScriptRuntimeException("right value not a number");
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }
    // >>

    public static Object rshift(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            if (o2 instanceof Number) {
                int right = ((Number) o2).intValue();
                int type = getBaseType(o1);
                switch (type) {
                    //case STRING:
                    case CHAR:
                        return ((Character) o1).charValue() >> right;
                    case BYTE:
                        return ((Byte) o1).byteValue() >> right;
                    case SHORT:
                        return ((Short) o1).shortValue() >> right;
                    case INTEGER:
                        return ((Integer) o1).intValue() >> right;
                    case LONG:
                        return ((Long) o1).longValue() >> right;
                    //case DOUBLE:
                    //case FLOAT:
                    default:
                        throw new ScriptRuntimeException("left value type is unsupported:" + o1.getClass().getName());
                }
            }
            throw new ScriptRuntimeException("right value not a number");
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }
    // >>>

    public static Object urshift(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            if (o2 instanceof Number) {
                int right = ((Number) o2).intValue();
                int type = getBaseType(o1);
                switch (type) {
                    //case STRING:
                    case CHAR:
                        return ((Character) o1).charValue() >>> right;
                    case BYTE:
                        return ((Byte) o1).byteValue() >>> right;
                    case SHORT:
                        return ((Short) o1).shortValue() >>> right;
                    case INTEGER:
                        return ((Integer) o1).intValue() >>> right;
                    case LONG:
                        return ((Long) o1).longValue() >>> right;
                    //case DOUBLE:
                    //case FLOAT:
                    default:
                        throw new ScriptRuntimeException("left value type is unsupported:" + o1.getClass().getName());
                }
            }
            throw new ScriptRuntimeException("right value not a number");
        } else {
            throw ValueIsNullException(o1, o2);
        }
    }
    //*******************

    private static ScriptRuntimeException UnsupportedTypeException(Object o1, Object o2) {
        return new ScriptRuntimeException("Unsupported type: left[" + o1.getClass().getName() + "], right[" + o2.getClass().getName() + "]");
    }

    private static ScriptRuntimeException ValueIsNullException(Object o1) {
        return new ScriptRuntimeException("value is null");
    }

    private static ScriptRuntimeException ValueIsNullException(Object o1, Object o2) {
        if (o1 == null) {
            if (o2 == null) {
                return new ScriptRuntimeException("left & right values are null");
            } else {
                return new ScriptRuntimeException("left value is null");
            }
        } else if (o2 == null) {
            return new ScriptRuntimeException("right value is null");
        } else {
            return new ScriptRuntimeException("left & right values are not null");
        }
    }

    ////////////////////////////
    public static boolean toBoolean(Object o) {
        if (o == null) {
            return false;
        } else if (Boolean.TRUE.equals(o)) {
            return true;
        } else if (Boolean.FALSE.equals(o)) {
            return false;
        }

        //if Collection empty 
        return CollectionUtil.notEmpty(o, true);
    }
}
