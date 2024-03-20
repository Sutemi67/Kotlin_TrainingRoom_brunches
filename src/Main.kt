import java.util.*

class CoffeeMachine {
    private var water: Int = 0
    private var milk: Int = 0
    private var beans: Int = 0

    enum class Types(val water: Int, val milk: Int, val beans: Int) {
        ESPRESSO(60, 0, 10),
        AMERICANO(120, 0, 10),
        CAPPUCCINO(120, 20, 60),
        LATTE(240, 20, 120)
    }

    private var scanner = Scanner(System.`in`)

    fun start() {
        println("Кофемашина готова к работе")

        while (true) {
            println("Введите команду:")
            val inputCoffee: String
            val inputWords = scanner.nextLine().lowercase()

            when (inputWords) {
                "выключить" -> {
                    println("До свидания!")
                    break
                }

                "наполнить" -> {
                    water = 2000
                    milk = 1000
                    beans = 500
                    println("Ингридиенты пополнены")
                }

                "статус" -> {
                    println("Ингридиентов осталось: \n$water мл воды\n$milk мл молока\n$beans гр кофе")
                }

                "кофе" -> {
                    println("Введите название напитка или \"назад\" для возврата в главное меню")
                    inputCoffee = scanner.nextLine().lowercase()
                    when (inputCoffee) {
                        "латте" -> {
                            if (water >= Types.LATTE.water && milk >= Types.LATTE.milk && beans >= Types.LATTE.beans) {
                                water -= Types.LATTE.water
                                milk -= Types.LATTE.milk
                                beans -= Types.LATTE.beans
                                println("${Types.LATTE} готов")
                            } else {
                                if (water < Types.LATTE.water) {
                                    println("Недостаточно воды!")
                                } else if (milk < Types.LATTE.milk) {
                                    println("Недостаточно молока!")
                                } else {
                                    println("Недостаточно кофе!")
                                }
                            }
                        }
                    }
                }
                else -> {
                    println("Неверная команда.")
                }
            }
        }
    }
}

fun main() {
    val coffee = CoffeeMachine()
    coffee.start()
}
