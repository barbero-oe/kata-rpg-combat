package oe.barbero.kata.rpg.combat

import kotlin.math.max
import kotlin.math.min

class Vitality(vitality: Int) {
    val hasVitality: Boolean get() = amount > 0
    var amount: Int = vitality
        private set

    fun damage(damage: Damage) {
        this.amount = max(MIN_HEALTH, this.amount - damage())
    }

    fun heal(healing: Int) {
        if (hasVitality)
            this.amount = min(MAX_HEALTH, this.amount + healing)
    }

    companion object {
        private const val MIN_HEALTH = 0
        private const val MAX_HEALTH = 1000
    }
}