enum class GroupRole(val title: String) {
    VOCAL("Вокалист"),
    BASS("Бассист"),
    SOLO("Соло-гитарист"),
    DRUMS("Барабанщик")
}

abstract class RockMusician {

    abstract val name: String
    abstract val role: GroupRole
}

interface CanSing {

    fun sing()
}

interface CanPlayGuitar {

    fun playGuitar()
}

interface CanPlayDrum {

    fun playDrum()
}

// унаследуйте данный класс от абстрактного класса RockMusician
// имя музыканта должно был переопределено в конструкторе
// его роль должна быть задана внутри класса как GroupRole.VOCAL
// вокалист должен уметь петь - реализуйте интерфейс CanSing. Метод sing() должен выводить текст: "We will, we will rock you"
class Vocalist

// унаследуйте данный класс от абстрактного класса RockMusician
// имя музыканта должно был переопределено в конструкторе
// поскольку и басс, и соло гитаристы являются гитаристами - их роль, как и имя, передаётся при создании в конструкторе.
// гитаристы должны уметь играть на гитаре. Реализуйте интерфейс CanPlayGuitar. Метод playGuitar() выводит текст "Играю на гитаре"
class Guitarist

// унаследуйте данный класс от абстрактного класса RockMusician
// имя музыканта должно был переопределено в конструкторе
// его роль должна быть задана внутри класса как GroupRole.DRUMS
// барабанщик играет на ударных. Интерфейс CanPlayDrum поможет в этом, реализуйте его. Метод playDrum() выводит текст "Пам парам пам пам, я играю на барабанах"
class Drummer