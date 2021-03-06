package oe.barbero.kata.rpg.combat

class Character private constructor(
    private val vitality: Vitality,
    val level: Int
) {
    val health: Int get() = vitality.amount
    val isAlive: Boolean get() = vitality.hasVitality

    fun attack(victim: Character, damage: Int) {
        if (this !== victim) {
            val totalDamage = Damage(damage, LevelModifier(level, victim.level))
            victim.damage(totalDamage)
        }
    }

    fun heal(wounded: Character, healing: Int) {
        if (this === wounded)
            wounded.cure(healing)
    }

    private fun damage(damage: Damage) = vitality.damage(damage)
    private fun cure(healing: Int) = vitality.heal(healing)

    companion object {
        private const val STARTING_VITALITY = 1000
        fun starting() = Character(Vitality(STARTING_VITALITY), 1)
        fun with(health: Int = STARTING_VITALITY, level: Int = 1) = Character(Vitality(health), level)
    }
}