package oe.barbero.kata.rpg.combat

import kotlin.math.max

class Character private constructor(private var remainingHealth: Int) {
    val level: Int = 1
    val health: Int
        get() = remainingHealth
    val isAlive: Boolean
        get() = remainingHealth > 0

    fun attack(victim: Character, damage: Int) {
        victim.remainingHealth = max(0, victim.remainingHealth - damage)
    }

    companion object {
        fun starting() = Character(1000)
    }
}