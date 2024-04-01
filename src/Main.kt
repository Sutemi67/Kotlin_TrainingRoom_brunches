import kotlin.random.Random

/*
В игру надо добавить двух или больше игроков. После чего для каждого игрока будет создаваться случайная карточка с числами.
Карточка будет представлять собой сетку в 3 ряда по 5 чисел в каждом.
После создания всех персонажей игра будет случайно выбрасывать числа от 1 до 99 и вычёркивать их из карточки каждого игрока.
Побеждает тот игрок, у которого первым будет зачёркнута хотя бы одна линия. Победителей может быть больше одного.
Разделим задачу на 3 подзадачи.
В первой задаче нужно создать 2 класса:
- класс Card — довольно простой класс, который в себе хранит только одно поле numbers со списком чисел в ряду по ключу (номеру ряда).
- класс Person имеет 2 поля:
    - name — имя игрока. Передаётся при создании в конструкторе.
    - card — карточка игрока. Генерируется новая карточка при создании конструктора.
*/

// создайте класс Card, который содержит в конструкторе одно поле numbers
// поле numbers — это Map, в которой в качестве ключа номер ряда (1 - 3), а в качестве значения набор чисел
// набор чисел должен уметь хранить только уникальные значения и в процессе работы программы должен уметь удалять из себя числа
// подумайте, какая структура данных лучше всего подойдёт для этой цели
class Card {
    val numbers: MutableMap<Int, MutableSet<Int>> = mutableMapOf()

    init {
        for (i in 1..3) {
            numbers[i] = mutableSetOf()
        }
    }

    fun addNumber(row: Int, number: Int) {
        numbers[row]?.add(number)
    }

    fun removeNumber(row: Int, number: Int) {
        numbers[row]?.remove(number)
    }

    fun getNumbers(row: Int): Set<Int>? {
        return numbers[row]
    }
}

// Создайте класс Person, который имеет лишь одно поле в конструкторе — строку name
// в теле класса создайте поле card класса Card. При создании экземпляра класса оно должно генерироваться с помощью метода createCard()

// метод createCard() должен возвращать объект класса Card
// карточка должна содержать в себе 15 случайных чисел. Числа должны быть распределены в 3 ряда по 5 штук в каждом и не повторяться
// числа в карточки должны быть от 1 до 99 включительно. Для генерации чисел можно использовать
// функцию Random.nextInt()

class Person(val name: String) {
    val card: Card = createCard()

    private fun createCard(): Card {
        val randomNumbers = mutableSetOf<Int>()

        while (randomNumbers.size < 15) {
            val randomNumber = Random.nextInt(1, 100)
            randomNumbers.add(randomNumber)
        }

        val rows = randomNumbers.chunked(5)
        return Card().apply {
            for ((index, row) in rows.withIndex()) {
                for (number in row) {
                    addNumber(index + 1, number)
                }
            }
        }
    }
}