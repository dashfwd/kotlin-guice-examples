# kotlin-guice-examples
Examples of using Guice with Kotlin

This repo provides examples of using [Google Guice](https://github.com/google/guice) 
with [Kotlin](https://kotlinlang.org).

Examples include:
1. Simple injection using a module, mapping an interface to an implementing class
2. Using the Singleton scope
3. Injection mapping to an instance
4. Injection using the `@Provides` annotation and a constructor method
5. Annotation-based bindings
6. Injection using a Provider class
7. Injection using a MapBinder and a Factory so implementations can be selected at runtime
8. Using Assisted Inject

For more information, see the comments in MainGuiceModule.kt

## Running the example
in your terminal: 
```
$ mvn compile
mvn exec:java
~/workplace/GlacierArchiveCatalogCDK/src/GlacierArchiveCatalogLambdas/kotlin-guice-examples/kotlin-guice-examples main*
❯ mvn compile
mvn exec:java
[INFO] Scanning for projects...
...
[INFO] BUILD SUCCESS
...
meow
bark
hey; also did you know about the color ? (port=
1 calculate 2 = 3
2 calculate 3 = 5
Mechanism 1 = by phone
Mechanism 2 = by fax
I created a blue car
Could not build an orange car
```

## Learning Guice
- [Google Guice](https://github.com/google/guice)
- [Eat Code Sleep Repeat - Google Guice Tutorial](https://youtube.com/playlist?list=PLp0ed20U4R4jknb4xYdhx3yJn5RhWECxn) on YouTube
- [MVP Java - Google Guice Tutorial](https://youtube.com/playlist?list=PLKiN3faYVq89TjVuba-F62_nKBpcFdOWz) on YouTube
- [Advanced Dependency Injection With Guice](https://dzone.com/articles/advanced-dependency-injection) on dzone
