package pro.abnjava.examcloud.history.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pro.abnjava.examcloud.history.rest.model.Question
import pro.abnjava.examcloud.history.rest.service.HistoryService

@RestController
@RequestMapping("/api")
class HistoryController(
    val historyService: HistoryService
) {

    @GetMapping("/questions")
    fun getRandomQuestions(
        @RequestParam("amount") amount: Int
    ): List<Question> {
        val answers = mutableListOf<Question>()
        for (i in 1..amount) {
            answers.add(historyService.getRandomQuestions(i))
        }
        return answers
    }
}
