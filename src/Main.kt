fun main() {
    data class Student(
        val name: String, val estimates: List<Int>
    )

    val students: List<Student> = listOf(
        Student("Анна", listOf(5, 4, 5, 5, 4)),
        Student("Алёна", listOf(5, 5, 5, 5, 5)),
        Student("Кирилл", listOf(3, 4, 5, 3, 4)),
        Student("Григорий", listOf(3, 3, 4, 3, 4)),
        Student("Светлана", listOf(5, 5, 5, 5, 4)),
        Student("Саша", listOf(4, 4, 4, 3, 5)),
        Student("Степан", listOf(2, 3, 3, 2, 4)),
        Student("Антон", listOf(2, 2, 3, 3, 3)),
        Student("Катя", listOf(3, 3, 4, 5, 4)),
        Student("Стас", listOf(5, 5, 5, 5, 4)),
    )

    val result = students.filter { student -> student.estimates.average() > 3 }
        .sortedByDescending { student -> student.estimates.average() }
        .map { student -> student.name }
        .take(3)


    println(result)
}