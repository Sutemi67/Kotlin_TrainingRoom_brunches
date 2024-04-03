import kotlin.random.Random

fun main() {
    val catDownloaderV2 = CatDownloaderV2()
    val downloadedCats = catDownloaderV2.downloadCats(
        CATS_COUNT,
        onNext = { showCat(it) },
        onComplete = { showComplete() },
        onError = { showError() }
    )
}


fun showCat(cat: Cat) {
    println("Котик ${cat.name} успешно загружен")
}

fun showComplete() {
    println("Загрузка котиков завершена")
}

fun showError() {
    println("Упс. При загрузке котика произошла ошибка :(")
}

// константа для выбора количества загруженных котиков
const val CATS_COUNT = 5


class CatDownloaderV2 {

    fun downloadCats(
        count: Int,
        onNext: (Cat) -> Unit,
        onComplete: () -> Unit,
        onError: () -> Unit
    ) {
        for (i in 1..count) {
            val cat: Cat? = getCatFromInternet()
            if (cat != null) {
                onNext.invoke(cat)
            } else {
                onError()
            }
        }
        onComplete()
    }

    private fun getCatFromInternet(): Cat? {
        return when (Random.nextInt(5)) {
            0 -> null
            1 -> Cat("Борис")
            2 -> Cat("Кузьма")
            3 -> Cat("Барсик")
            4 -> Cat("Кефирчик")
            else -> null
        }
    }
}

data class Cat(val name: String)