package pro.abnjava.examcloud.history.rest.service

import org.springframework.stereotype.Service
import pro.abnjava.examcloud.history.db.model.QuestionEntity
import pro.abnjava.examcloud.history.db.repository.QuestionRepo
import pro.abnjava.examcloud.history.rest.model.Question

@Service
class HistoryService(
    val questionRepo: QuestionRepo
) {
    fun getRandomQuestions(amount: Int): List<Question> {
        return questionRepo.findRandomRecordsH2(count = amount)
            .map { m1 -> m1.toCore() }
            .toList()
    }

    private fun QuestionEntity.toCore() = Question(
        id = this.id,
        question = this.question,
        answer = this.answer
    )
}
