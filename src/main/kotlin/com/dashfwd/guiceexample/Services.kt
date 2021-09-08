package com.dashfwd.guiceexample

import com.google.inject.Inject
import com.google.inject.Singleton
import com.google.inject.assistedinject.Assisted
import com.google.inject.name.Named
import java.util.*

class AllServices {
    @Inject
    lateinit var birdService: BirdService

    @Inject
    lateinit var catService: CatService

    @Inject
    lateinit var dogService: DogService

    @Inject
    lateinit var personService: PersonService

    @Inject
    lateinit var calculationService: CalculationService

    @Inject
    lateinit var senderFactory: SenderFactory

    @Inject
    lateinit var carConfiguratorFactory: CarConfiguratorFactory

}


interface BirdService { fun speak() }

class BirdServiceImpl : BirdService {
    override fun speak() = println("squawk!")
}

interface CatService { fun speak() }
class CatServiceImpl : CatService {
    override fun speak() = println("meow")
}

interface DogService { fun speak() }
class DogServiceImpl(private val msg: String) : DogService {
    override fun speak() = println(msg)
}

interface CalculationService { fun calculate(x:Int, y:Int): Int }
class AdditionServiceImpl: CalculationService { override fun calculate(x:Int, y:Int) = x + y }
class MultiplicationServiceImpl: CalculationService { override fun calculate(x:Int, y:Int) = x * y }

enum class SenderType {
    Email, Fax, Phone
}
interface SenderService { fun getMechanism(): String }
class EmailSenderServiceImpl: SenderService { override fun getMechanism() = "by email" }
class FaxSenderServiceImpl:   SenderService { override fun getMechanism() = "by fax" }
class PhoneSenderServiceImpl: SenderService { override fun getMechanism() = "by phone" }

interface SenderFactory { fun getSender(type: SenderType) : SenderService }

@Singleton
class SenderFactoryImpl : SenderFactory {
    @Inject
    @JvmSuppressWildcards // <-- NOTE: this is important; see https://stackoverflow.com/questions/45287126/guice-mapbinder
    lateinit var personBinder: Map<SenderType, SenderService>

    override fun getSender(type: SenderType): SenderService {
        return personBinder[type] ?: error("Could not find sender for type=$type")
    }
}

interface PersonService { fun speak() }

class PersonServiceImpl : PersonService {
    @Inject
    @Named("person message")
    lateinit var msg: String

    @Inject
    @ColorAnnotation
    lateinit var color: String

    @Inject
    @HttpPortAnnotation
    var httpPort: String=""

    override fun speak() = println("$msg; also did you know about the color $color? (port=$httpPort")
}

/**
 * Asssisted Injection
 *
 * Good article on this: https://dzone.com/articles/advanced-dependency-injection
 */

data class Car(val color: String)

class ColorCheckService {
    fun checkIfValid(color: String) = (color != "orange")
}

/**
 * This interface is what will be used by client code to create a car configurator.
 */
interface CarConfiguratorFactory {
    fun create(color: String): CarConfigurator
}

interface CarConfigurator {
    fun validateColor(): Optional<Car>
}

class CarConfiguratorImpl @Inject constructor(
    @param:Assisted
    private val color: String
) : CarConfigurator {
    @Inject
    private lateinit var colorCheckService: ColorCheckService

    override fun validateColor(): Optional<Car> {
        val valid = colorCheckService.checkIfValid(color)
        return if (valid) Optional.of(Car(color)) else Optional.empty()
    }
}