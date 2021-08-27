package com.dashfwd.guiceexample

import com.google.inject.Guice

fun main() {
    val injector = Guice.createInjector(MainGuiceModule())
    val allServices = injector.getInstance(AllServices::class.java)

    allServices.birdService.speak()
    allServices.catService.speak()
    allServices.dogService.speak()
    allServices.personService.speak()

    println("1 calculate 2 = " + allServices.calculationService.calculate(1,2))
    println("2 calculate 3 = " + allServices.calculationService.calculate(2,3))

    println("Mechanism 1 = " + allServices.senderFactory.getSender(SenderType.Phone).getMechanism())
    println("Mechanism 2 = " + allServices.senderFactory.getSender(SenderType.Fax).getMechanism())
}