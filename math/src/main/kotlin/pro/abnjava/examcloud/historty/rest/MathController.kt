package pro.abnjava.examcloud.math.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pro.abnjava.examcloud.math.model.Question
import pro.abnjava.examcloud.math.service.MathService

@RestController
@RequestMapping("/api")
class MathController(
    val mathService: MathService
) {

    @GetMapping("/questions")
    fun getRandomQuestions(
        @RequestParam("amount") amount: Int
    ): List<Question> {
        val answers = mutableListOf<Question>()
        for (i in 1..amount) {
            answers.add(mathService.getRandomQuestions(i))
        }
        return answers
    }
}
