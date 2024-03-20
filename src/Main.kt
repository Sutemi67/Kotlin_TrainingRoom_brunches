import java.util.*

class CoffeeMachine {
    private var scanner = Scanner(System.`in`)

    fun start(water: Int, milk: Int, beans: Int) {
        println("Кофемашина готова к работе")
        var inputWords = scanner.nextLine()
        var inputWordsLowercase = inputWords.lowercase()
        while (true) {
            println("Введите команду:")
            when (inputWordsLowercase) {
                "выключить" -> {
                    println("До свидания!")
                    break
                }
                "наполнить"->{

                }
                "статус"->{

                }
                "кофе"->{

                }
            }
        }
    }
}

