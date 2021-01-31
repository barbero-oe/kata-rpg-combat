package oe.barbero.kata.rpg.combat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CharacterTests {
    @Test
    fun `an starting character has 1000 of life`() {
        val character = Character.starting()

        assertThat(character.health).isEqualTo(1000)
        assertThat(character.level).isEqualTo(1)
        assertThat(character.isAlive).isTrue
    }

    @Test
    fun `an attack reduces target's health`() {
        val character = Character.starting()
        val victim = Character.starting()

        character.attack(victim, 100)

        assertThat(victim.health).isEqualTo(900)
    }

    @Test
    fun `a deadly attack kills the oponent`() {
        val character = Character.starting()
        val victim = Character.starting()

        character.attack(victim, 1100)

        assertThat(victim.health).isEqualTo(0)
        assertThat(victim.isAlive).isFalse
    }
}