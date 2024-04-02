import kotlin.random.Random

class Lotto {

    private val persons: MutableList<Person> = mutableListOf()
    val thrownNumbers: MutableSet<Int> = mutableSetOf()

    fun addPerson(person: Person) {
        persons.add(person)
    }


    fun start() {
        if (persons.size > 1) {
            while (true) {
                val thrownNumber = generateUniqueNumber()
                thrownNumbers.add(thrownNumber)
                println("Выброшенное число $thrownNumber")

                for (person in persons) {
                    val card = person.card
                    for (line in card.numbers.keys) {
                        if (thrownNumber in card.numbers[line]!!) {
                            card.numbers[line]!!.remove(thrownNumber)
                        }
                    }
                }

                val winners = persons.filter { person ->
                    person.card.numbers.values.all { it.isEmpty() }
                }

                if (winners.isNotEmpty()) {
                    for (winner in winners) {
                        println("Победитель: ${winner.name}!!!")
                    }
                    break
                }
            }
        } else {
            println("Перед началом игры необходимо добавить хотя бы двух игроков")
        }
    }

    private fun generateUniqueNumber(): Int {
        var number: Int
        do {
            number = Random.nextInt(1,100)
        } while (number in thrownNumbers)
        return number
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
