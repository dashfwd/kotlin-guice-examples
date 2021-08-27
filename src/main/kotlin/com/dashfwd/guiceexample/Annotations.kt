package com.dashfwd.guiceexample

import com.google.inject.BindingAnnotation

@BindingAnnotation
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME) // required for Guice
annotation class ColorAnnotation

@BindingAnnotation
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME) // required for Guice
annotation class HttpPortAnnotation