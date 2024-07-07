package pro.abnjava.examcloud.history.rest.service

import org.springframework.stereotype.Service
import pro.abnjava.examcloud.history.db.repository.QuestionRepo
import pro.abnjava.examcloud.history.rest.model.Question

@Service
class HistoryService(
    val questionRepo: QuestionRepo
) {
    fun getRandomQuestions(amount: Int): Question {
        return Question(id = 0, question = "?", answer = "aaa")
    }
}
