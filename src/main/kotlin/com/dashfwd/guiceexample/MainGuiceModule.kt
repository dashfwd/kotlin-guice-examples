package com.dashfwd.guiceexample

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.assistedinject.FactoryModuleBuilder
import com.google.inject.multibindings.MapBinder
import com.google.inject.name.Names

class MainGuiceModule : AbstractModule() {
    override fun configure() {
        // Example 1: bind an interface to an implementation
        // --------------------------------------------------------
        bind(BirdService::class.java).to(BirdServiceImpl::class.java)

        // This can also be done by adding this annotation to BirdService:
        // @ImplementedBy(BirdServiceImpl::class) [and then the line above can be removed]

        // Example 2: Using the Singleton scope
        // ---------------------------------------------------------
        // See https://github.com/google/guice/wiki/Scopes for more info
        // to make the above into a singleton:
        //    bind(BirdService::class.java).to(BirdServiceImpl::class.java).`in`(Scopes.SINGLETON)
        // or put the @Singleton annotation on BirdServiceImpl

        // Example 3: bind an interface to an instance
        // --------------------------------------------------------
        val catService = CatServiceImpl()
        bind(CatService::class.java).toInstance(catService)

        // Example 4: Use @Provides instead of a binding
        // --------------------------------------------------------
        // (see the createDogService() method)
        // and read https://github.com/google/guice/wiki/ProvidesMethods

        // Example 5: Annotation-based bindings
        // --------------------------------------------------------
        //  See https://github.com/google/guice/wiki/BindingAnnotations
        bind(String::class.java).annotatedWith(Names.named("person message"))
            .toInstance("hey")
        bind(String::class.java).annotatedWith(ColorAnnotation::class.java).toInstance("blue")
        bindConstant().annotatedWith(HttpPortAnnotation::class.java).to("8080") // binding a constant; may be more useful in Java
        bind(PersonService::class.java).to(PersonServiceImpl::class.java)

        // Example 6: Using a provider class to create the instance
        bind(CalculationService::class.java).toProvider(CalculationProvider::class.java)

        // Example 6: Service Factory Using MapBinder
        // ------------------------------------------------------------------------
        //   see also https://github.com/google/guice/wiki/Multibindings
        //   and https://youtu.be/BtGf42WB9wM
        val mapBinder: MapBinder<SenderType, SenderService> = MapBinder.newMapBinder(
            binder(),
            SenderType::class.java,
            SenderService::class.java
        )

        mapBinder.addBinding(SenderType.Phone).to(PhoneSenderServiceImpl::class.java)
        mapBinder.addBinding(SenderType.Fax).to(FaxSenderServiceImpl::class.java)
        mapBinder.addBinding(SenderType.Email).to(EmailSenderServiceImpl::class.java)

        bind(SenderFactory::class.java).to(SenderFactoryImpl::class.java)

        install(
            FactoryModuleBuilder()
                // the next line contains the interface and implementation of a service
                // that needs assisted creation
                .implement(CarConfigurator::class.java, CarConfiguratorImpl::class.java)

                // this is the interface that will be visible to our internal
                // code to create an instance of the car configurator.
                // Guice will generate the actual factory that does the additional
                //
                .build(CarConfiguratorFactory::class.java)
        )
    }

    // A method can also be used to provide an implementation
    @Provides
    fun createDogService(): DogService {
        return DogServiceImpl("bark")
    }
}

