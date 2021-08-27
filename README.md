# kotlin-guice-examples
Examples of using Guice with Kotlin

This repo provides examples of using [Google Guice](https://github.com/google/guice) 
with [Kotlin](https://kotlinlang.org).

Examples include:
1. Simple injection using a module, mapping an interface to an implementing class
2. Using the Singleton scope
3. Injection mapping to an instance
4. Injection using the @Provides annotation and a constructor method
5. Annotation-based bindings
6. Injection using a Provider class
7. Injection using a MapBinder and a Factory so implementations can be selected at runtime

For more information, see the comments in MainGuiceModule.kt