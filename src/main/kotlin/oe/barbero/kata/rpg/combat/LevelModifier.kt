package oe.barbero.kata.rpg.combat

import oe.barbero.kata.rpg.combat.LevelModifier.CompetitionWinner.*

class LevelModifier(private val attacker: Int, private val defender: Int) {
    operator fun invoke() = strongerOpponent().modifier

    private fun strongerOpponent(): CompetitionWinner {
        val levelDifference = attacker - defender
        return when {
            levelDifference >= 5 -> ATTACKER
            levelDifference <= -5 -> DEFENDER
            else -> TIE
        }
    }

    private enum class CompetitionWinner(val modifier: Double) {
        ATTACKER(1.5),
        DEFENDER(0.5),
        TIE(1.0)
    }
}