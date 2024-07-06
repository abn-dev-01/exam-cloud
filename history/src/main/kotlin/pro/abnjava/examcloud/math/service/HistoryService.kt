package pro.abnjava.examcloud.history.service

import org.springframework.stereotype.Service
import pro.abnjava.examcloud.history.model.Question
import java.util.*

@Service
class HistoryService {

    private val random: Random = Random()
    private val max = 10

    fun getRandomQuestions(amount: Int): Question {
        val a = random.nextInt(max)
        val b = random.nextInt(max)
        return Question("$a + $b = ?", (a + b).toString())
    }
}
