//package cn.edu.zucc.personplan.test;
//
//import java.lang.reflect.*;
//
//public class BaseViewModel<M extends Model> implements ViewModel {
//
//    protected M model;
//
//    private M createModel() {
//
//        try {
//            Type superClass = getClass().getGenericSuperclass();
//            Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
//            Class<?> clazz = getRawType(type);
//            return (M) clazz.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    // type不能直接实例化对象，通过type获取class的类型，然后实例化对象
//    public static Class<?> getRawType(Type type) {
//        if (type instanceof Class) {
//            return (Class) type;
//        } else if (type instanceof ParameterizedType) {
//            ParameterizedType parameterizedType = (ParameterizedType) type;
//            Type rawType = parameterizedType.getRawType();
//            return (Class) rawType;
//        } else if (type instanceof GenericArrayType) {
//            Type componentType = ((GenericArrayType) type).getGenericComponentType();
//            return Array.newInstance(getRawType(componentType), 0).getClass();
//        } else if (type instanceof TypeVariable) {
//            return Object.class;
//        } else if (type instanceof WildcardType) {
//            return getRawType(((WildcardType) type).getUpperBounds()[0]);
//        } else {
//            String className = type == null ? "null" : type.getClass().getName();
//            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + className);
//        }
//    }
//
//}
////上面这个getRawType方法研究了很久，最后查看了Gson的源码发现了这段代码的写法，现分享给大家，希望有帮助。