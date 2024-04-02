import kotlin.random.Random

class Lotto {

    val persons: MutableList<Person> = mutableListOf()
    val thrownNumbers: MutableSet<Int> = mutableSetOf()

    fun addPerson(person: Person) {
        persons.add(person)
    }

    fun start() {
        if (persons.size <= 1) {
            println("Перед началом игры необходимо добавить хотя бы двух игроков")
            return
        } else {
            val personIterator = persons.iterator()
            while (true) {
                val thrownNumber = Random.nextInt(1, 100)
                thrownNumbers.add(thrownNumber)

                for (key in 0..personIterator.next().card.numbers.keys.size) {

                    while (personIterator.hasNext()) {
                        val cardKeys = personIterator.next().card.numbers[key]
                        val mapIterator = cardKeys!!.iterator()

                        while (mapIterator.hasNext()) {
                            if (mapIterator.next() == thrownNumber) {
                                mapIterator.remove()
                            }
                        }
                        if (cardKeys.size == 0) {
                            println("Победитель: ${personIterator.next()}!!!")
                            return
                        }
                    }
                }
            }
        }
    }
}
// Достать номер. Номер может быть в диапазоне от 1 до 99 включительно
// после каждого выброшенного числа удалять это число из карточек всех игроков, если такое число имеется
// выбрасывать новые числа до тех пор, пока не определится победитель
// побеждает тот, у кого в одном из рядов нет больше чисел. Победителей может быть более одного
// после того как появляется победитель, для каждого победителя вывести текст "Победитель: [имя_победителя]!!!"


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
