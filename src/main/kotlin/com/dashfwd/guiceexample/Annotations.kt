package com.dashfwd.guiceexample

import com.google.inject.BindingAnnotation

@BindingAnnotation
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME) // required for Guice
annotation class ColorAnnotation

@BindingAnnotation
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME) // required for Guice
annotation class HttpPortAnnotation