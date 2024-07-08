package pro.abnjava.examcloud.exam.model

data class Section(
    val questions: List<Question>,
    val title: String? = null,
)
