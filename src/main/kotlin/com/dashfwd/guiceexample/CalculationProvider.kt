package com.dashfwd.guiceexample

import com.google.inject.Inject
import com.google.inject.Provider
import java.util.*

class CalculationProvider : Provider<CalculationService> {
    @Inject
    lateinit var additionServiceImpl: AdditionServiceImpl

    @Inject
    lateinit var multiplicationServiceImpl: MultiplicationServiceImpl

    // Note: this method will only be called once upon initial injection; after that the
    // same instance will always be used
    override fun get(): CalculationService {
        return if (Calendar.getInstance().get(Calendar.SECOND) % 2 == 0) {
            additionServiceImpl
        }
        else {
            multiplicationServiceImpl
        }
    }
}