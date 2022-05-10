package com.ultrapartners.ui.tests.utils

import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.fakerConfig

fun randomizer(): Faker {
    val config = fakerConfig { locale = "en" }
    return Faker(config)
}

fun generateRndNumber(): String {
    return randomizer().random.nextInt(1..Int.MAX_VALUE).toString()
}

fun generateRndEmail(): String {
    return randomizer().internet.email(randomizer().funnyName.name())
}