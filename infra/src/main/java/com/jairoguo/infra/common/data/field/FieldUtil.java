package com.jairoguo.infra.common.data.field;

import com.jairoguo.infra.base.mark.PO;
import com.jairoguo.infra.exception.FieldException;
import com.jairoguo.infra.util.ClassUtils;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用来处理字段的工具类 <br>
 * 1. 通过方法引用获取字段 2. 通过数据模型的映射关系获取字段
 *
 * @author jairoguo
 */
public class FieldUtil {

  static Logger log = LoggerFactory.getLogger(FieldUtil.class);

  /**
   * 根据lambda方法引用转换为字段名
   *
   * @param columnFunc 方法引用
   * @return 属性名称
   */
  public static <T, R> String getField(FieldFunction<T, R> columnFunc) {

    SerializedLambda serializedLambda = getSerializedLambda(columnFunc);
    String methodName = serializedLambda.getImplMethodName();
    return reduction(methodName);
  }

  /**
   * 获取lambda方法引用的实现类
   *
   * @param fieldFunction 方法引用
   * @return Class<?>
   */
  public static <T, R> Class<?> getFieldClass(FieldFunction<T, R> fieldFunction) {

    try {
      SerializedLambda serializedLambda = getSerializedLambda(fieldFunction);
      String implClass = serializedLambda.getImplClass();
      String declaredClass = implClass.replace("/", ".");
      return Class.forName(declaredClass, false, ClassUtils.getDefaultClassLoader());
    } catch (ClassNotFoundException e) {
      throw new FieldException("获取实现类异常");
    }
  }

  /**
   * 获取SerializedLambda
   *
   * @param fieldFunction 方法引用
   * @return SerializedLambda
   */
  @SuppressWarnings("all")
  public static <T, R> SerializedLambda getSerializedLambda(FieldFunction<T, R> fieldFunction) {
    Class<?> fieldClass = fieldFunction.getClass();

    try {
      Method writeReplace = fieldClass.getDeclaredMethod("writeReplace");
      writeReplace.setAccessible(true);
      Object invoke = writeReplace.invoke(fieldFunction);
      return (SerializedLambda) invoke;

    } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
      log.error("获取SerializedLambda异常");
      throw new FieldException("获取SerializedLambda异常 \n" + e);
    }
  }

  private static final Map<Class<?>, Map<String, String>> mapperCache = new HashMap<>();

  /**
   * 更具数据模型映射关系匹配字段名
   *
   * @param column 要映射匹配的字段名
   * @param valueMapper 值映射表达式
   * @param condition 条件表达式
   * @param po 数据模型类对象
   * @return 返回匹配到的字段名，没有匹配到返回要匹配到字段名
   */
  public static String getFieldByMapper(
      String column,
      Function<Field, String> valueMapper,
      Predicate<Field> condition,
      Class<? extends PO> po) {
    String mapperValue;
    if (mapperCache.containsKey(po)) {
      mapperValue = mapperCache.get(po).get(column);
    } else {
      Map<String, String> mapperColumn =
          Arrays.stream(po.getDeclaredFields())
              .filter(condition)
              .collect(Collectors.toMap(Field::getName, valueMapper));
      mapperCache.put(po, mapperColumn);

      mapperValue = mapperColumn.get(column);
    }
    if (mapperValue != null) {
      return mapperValue;
    }
    return column;
  }

  /**
   * 将get方法或is方法转换为相应的属性名
   *
   * @param methodName 方法名
   * @return 属性名字符串
   */
  private static String reduction(String methodName) {
    if (methodName == null) {
      throw new FieldException("方法名为null");
    }
    String fieldName;
    if (methodName.startsWith("get")) {
      fieldName = methodName.substring(3);
    } else if (methodName.startsWith("is")) {
      fieldName = methodName.substring(2);
    } else {
      throw new FieldException("方法名必须为 'get' 或 'is' 起始的方法");
    }
    return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
  }

  private FieldUtil() {}
}
