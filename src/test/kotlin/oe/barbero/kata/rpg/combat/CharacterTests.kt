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

    @Test
    fun `a character can heal another`() {
        val healer = Character.starting()
        val damaged = Character.with(health = 500)

        healer.heal(damaged, 100)

        assertThat(damaged.health).isEqualTo(600)
    }

    @Test
    fun `healing cannot surpass 1000 health`() {
        val healer = Character.starting()
        val damaged = Character.with(health = 500)

        healer.heal(damaged, 600)

        assertThat(damaged.health).isEqualTo(1000)
    }

    @Test
    fun `dead character cannot be healed`() {
        val healer = Character.starting()
        val dead = Character.with(health = 0)

        healer.heal(dead, 600)

        assertThat(dead.isAlive).isFalse
        assertThat(dead.health).isEqualTo(0)
    }
}