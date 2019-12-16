package ml.munan.boot.demo.exceldemo.config;

import java.lang.annotation.*;

/**
 * @Author: munan
 * @Date: 2019/12/16 8:13 下午
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface UniqueId {
    String[] value() default {""};
}
