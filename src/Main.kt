import java.util.*
import kotlin.random.Random

class Game {
    companion object {
        val input = Scanner(System.`in`)
    }

    fun start() {
        println("Привет, поиграем в лото?")
        val lotto = Lotto()
        while (true) {
            println("Введите имя нового игрока")
            val name = input.nextLine()
            val newPlayer = Person(name)

            lotto.addPerson(newPlayer)

            println("Если хотите добавить ещё игрока - введите любой символ, если хотите начать игру введите 'Нет'")
            when (input.nextLine().lowercase()) {
                "нет" -> {
                    break
                }
            }
        }
        lotto.start()
    }
}

class Card(val numbers: Map<Int, MutableSet<Int>>)

class Person(val name: String) {

    val card: Card = createCard()

    private fun createCard(): Card {
        val numbers: Set<Int> = generateNumbers()

        val iterator: Iterator<Int> = numbers.iterator()
        var currentLine = 1

        val cardNumbers: MutableMap<Int, MutableSet<Int>> = mutableMapOf(
            1 to mutableSetOf(),
            2 to mutableSetOf(),
            3 to mutableSetOf()
        )

        while (iterator.hasNext()) {
            val number = iterator.next()
            cardNumbers[currentLine]?.add(number)

            if (currentLine < 3) {
                currentLine++
            } else {
                currentLine = 1
            }
        }

        return Card(cardNumbers)
    }

    private fun generateNumbers(): Set<Int> {
        val numbers: MutableSet<Int> = mutableSetOf()

        while (numbers.size < NUMBERS_COUNT) {
            numbers.add(Random.nextInt(MIN_NUMBER, MAX_NUMBER))
        }

        return numbers
    }

    private companion object {

        private const val NUMBERS_COUNT = 15
        private const val MAX_NUMBER = 100
        private const val MIN_NUMBER = 1
    }
}

class Lotto {

    private val persons: MutableList<Person> = mutableListOf()
    val thrownNumbers: MutableSet<Int> = mutableSetOf()

    fun addPerson(person: Person) {
        persons.add(person)
    }

    fun start() {
        if (persons.size < 2) {
            println("Перед началом игры необходимо добавить хотя бы двух игроков")
        } else {
            do {
                val number = throwNumber()

                for (person in persons) {
                    val cardNumbers = person.card.numbers
                    for (key in cardNumbers.keys) {
                        cardNumbers[key]?.remove(number)
                    }
                }
            } while (!hasWinners())
        }
    }

    private fun throwNumber(): Int {
        val number = Random.nextInt(1, 100)

        return if (thrownNumbers.contains(number)) {
            throwNumber()
        } else {
            thrownNumbers.add(number)
            println("Выброшенное число: $number")
            number
        }
    }

    private fun hasWinners(): Boolean {
        val winners: MutableList<Person> = mutableListOf()

        for (person in persons) {
            val cardNumbers = person.card.numbers
            for (key in cardNumbers.keys) {
                if (cardNumbers[key]?.isEmpty() == true) {
                    winners.add(person)
                }
            }
        }

        return if (winners.isEmpty()) {
            false
        } else {
            for (winner in winners) {
                println("Победитель: ${winner.name}!!!")
            }
            true
        }
    }
}