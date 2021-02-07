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
    fun `healing increases vitality`() {
        val hero = Character.with(health = 500)

        hero.heal(hero, 100)

        assertThat(hero.health).isEqualTo(600)
    }

    @Test
    fun `healing cannot surpass 1000 health`() {
        val hero = Character.with(health = 500)

        hero.heal(hero, 600)

        assertThat(hero.health).isEqualTo(1000)
    }

    @Test
    fun `dead character cannot be healed`() {
        val dead = Character.with(health = 0)

        dead.heal(dead, 600)

        assertThat(dead.isAlive).isFalse
        assertThat(dead.health).isEqualTo(0)
    }

    @Test
    fun `a character cannot hurt itself`() {
        val hero = Character.starting()

        hero.attack(hero, 100)

        assertThat(hero.health).isEqualTo(1000)
    }

    @Test
    fun `a character can only heal itself`() {
        val hero = Character.starting()
        val another = Character.with(health = 500)

        hero.heal(another, 100)

        assertThat(another.health).isEqualTo(500)
    }

    @Test
    fun `if victim is higher level the damage is reduced`() {
        val attacker = Character.starting()
        val victim = Character.with(level = 6)

        attacker.attack(victim, 200)

        assertThat(victim.health).isEqualTo(900)
    }

    @Test
    fun `if victim is lower level the damage is increased`() {
        val attacker = Character.with(level = 6)
        val victim = Character.starting()

        attacker.attack(victim, 200)

        assertThat(victim.health).isEqualTo(700)
    }
}