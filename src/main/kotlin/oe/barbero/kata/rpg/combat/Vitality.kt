package oe.barbero.kata.rpg.combat

import kotlin.math.max
import kotlin.math.min

class Vitality(vitality: Int) {
    var amount: Int = vitality
        private set

    val hasVitality: Boolean
        get() = amount > 0

    fun damage(damage: Damage) {
        this.amount = max(MIN_HEALTH, this.amount - damage())
    }

    fun heal(healing: Int) {
        if (hasVitality)
            this.amount = min(MAX_HEALTH, this.amount + healing)
    }

    companion object {
        const val MIN_HEALTH = 0
        const val MAX_HEALTH = 1000
    }
}