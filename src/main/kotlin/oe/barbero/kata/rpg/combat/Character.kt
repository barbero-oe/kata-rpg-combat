package oe.barbero.kata.rpg.combat

import kotlin.math.max
import kotlin.math.min

class Character private constructor(private var remainingHealth: Int) {
    val level: Int = 1
    val health: Int
        get() = remainingHealth
    val isAlive: Boolean
        get() = remainingHealth > 0

    fun attack(victim: Character, damage: Int) {
        if (this !== victim)
            victim.remainingHealth = max(0, victim.remainingHealth - damage)
    }

    fun heal(wounded: Character, healing: Int) {
        if (wounded.isAlive && this === wounded)
            wounded.remainingHealth = min(1000, wounded.remainingHealth + healing)
    }

    companion object {
        fun starting() = Character(1000)
        fun with(health: Int = 1000) = Character(health)
    }
}