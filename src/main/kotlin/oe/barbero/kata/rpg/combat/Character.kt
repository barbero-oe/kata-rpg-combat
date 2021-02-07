package oe.barbero.kata.rpg.combat

import kotlin.math.max
import kotlin.math.min

class Character private constructor(
    private var remainingHealth: Int,
    val level: Int
) {
    val health: Int
        get() = remainingHealth
    val isAlive: Boolean
        get() = remainingHealth > 0

    fun attack(victim: Character, damage: Int) {
        val levelDifference = this.level - victim.level
        val netDamage = calculateDamage(damage, levelDifference)
        if (this !== victim)
            victim.damage(netDamage)
    }

    private fun damage(damage: Int) {
        this.remainingHealth = max(0, this.remainingHealth - damage)
    }

    private fun calculateDamage(baseDamage: Int, levelDifference: Int): Int {
        return when {
            levelDifference <= -5 -> (baseDamage * 0.5).toInt()
            levelDifference >= 5 -> (baseDamage * 1.5).toInt()
            else -> baseDamage
        }
    }

    fun heal(wounded: Character, healing: Int) {
        if (wounded.isAlive && this === wounded)
            wounded.remainingHealth = min(1000, wounded.remainingHealth + healing)
    }

    companion object {
        fun starting() = Character(1000, 1)
        fun with(health: Int = 1000, level: Int = 1) = Character(health, level)
    }
}