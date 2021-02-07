package oe.barbero.kata.rpg.combat

class Damage(
    private val baseDamage: Int,
    private val levelModifier: LevelModifier
) {
    operator fun invoke() = (baseDamage * levelModifier()).toInt()
}
