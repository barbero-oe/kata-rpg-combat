package oe.barbero.kata.rpg.combat

class Character private constructor(
    private val vitality: Vitality,
    val level: Int
) {
    val health: Int
        get() = vitality.amount
    val isAlive: Boolean
        get() = vitality.hasVitality

    fun attack(victim: Character, damage: Int) {
        val levelDifference = this.level - victim.level
        val netDamage = calculateDamage(damage, levelDifference)
        if (this !== victim)
            victim.damage(netDamage)
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
            wounded.cure(healing)
    }

    private fun damage(damage: Int) = vitality.damage(damage)
    private fun cure(healing: Int) = vitality.heal(healing)

    companion object {
        fun starting() = Character(Vitality(1000), 1)
        fun with(health: Int = 1000, level: Int = 1) = Character(Vitality(health), level)
    }
}