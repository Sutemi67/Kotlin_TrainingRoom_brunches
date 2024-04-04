class Person(val name: String, val surname: String, val thirdName: String)

// реализуйте свойство firstSymbol для класса String
// "Белка".firstSymbol -> Результат 'Б'
// "автомобиль".firstSymbol -> Результат 'а'
val String.firstSymbol: Char
    get() {
        val firstLetter = this.toCharArray().first()
        return firstLetter
    }

// реализуйте свойство firstDigit для класса Int
// val a = 435
// a.firstDigit -> Результат 4
val Int.firstDigit: Int
    get() {
        var num = this
        while (num >= 10) {
            num /= 10
        }
        return num
    }


// реализуйте свойство fio для класса Person
// Person("Андрей", "Стрельцов", "Александрович") -> Результат "Стрельцов Андрей Александрович"
val Person.fio: String
    get() {
        return "${this.surname} ${this.name} ${this.thirdName}"
    }

// реализуйте свойство biggestDigit для класса Int
// val a = 435
// a.biggestDigit -> Результат 5
val Int.biggestDigit: Int
    get() {
        var most = 0
        var remaining = this
        var lastDigit = 0
        while (remaining >= 10) {
            lastDigit = remaining % 10
            remaining /= 10
            if (lastDigit > most)
                most = lastDigit

        }
        return most
    }

fun main() {
    val str = "Привет че делаешь"
    val int = 32352
    val person = Person("Серега", "Бойков", "Сергеевич")
    println(str.firstSymbol)
    println(int.firstDigit)
    println(person.fio)
    println(int.biggestDigit)
}