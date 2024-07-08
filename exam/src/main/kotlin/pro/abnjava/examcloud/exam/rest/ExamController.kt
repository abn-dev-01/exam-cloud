package pro.abnjava.examcloud.exam.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import pro.abnjava.examcloud.exam.model.Exam
import pro.abnjava.examcloud.exam.model.Question
import pro.abnjava.examcloud.exam.model.Section

@RestController
@RequestMapping()
class ExamController(
    val restTemplate: RestTemplate
) {

    @GetMapping("/exam")
    fun getExam(
        @RequestBody spec: Map<String, Int>
    ): Exam {
        val sections: List<Section> = spec.entries
            .map(::getUrl)
            .map { restTemplate.getForObject(it, List::class.java) ?: emptyList<Question>() }
//            .map { restTemplate.getForObject(it, Question::class.java) }
            .map { m2 -> listOf(m2 as Question) }
            .map { m3 -> Section(questions = m3) }
            .toList()

        return Exam(title = "Exam", sections = sections)
    }

    private fun getUrl(entry: Map.Entry<String, Int>): String {
        return ""
    }
}
