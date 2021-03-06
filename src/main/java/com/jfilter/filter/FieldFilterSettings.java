package com.jfilter.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Repeatable annotation of {@link FieldFilterSetting} interface
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldFilterSettings {

    /**
     * Array of {@link FieldFilterSetting}
     *
     * @return array of {@link FieldFilterSetting}
     */
    FieldFilterSetting[] value();
}