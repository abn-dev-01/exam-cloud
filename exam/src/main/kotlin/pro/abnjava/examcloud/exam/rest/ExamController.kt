package pro.abnjava.examcloud.exam.rest

import mu.KLogging
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.PostMapping
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
    val restTemplate: RestTemplate,
) : KLogging() {

    @PostMapping("/exam")
    fun getExam(
        @RequestBody spec: Map<String, Int>
    ): Exam {
        val sections: List<Section> = spec.entries
            .map(::getUrl)
            .onEach { sourceUrl -> println("url: $sourceUrl") }
            .map { fetchQuestions(restTemplate, it) }
            .map { m3: List<Question> ->
                Section(questions = m3)
            }
            .toList()
        return Exam(title = "Exam", sections = sections)
    }

    fun fetchQuestions(restTemplate: RestTemplate, url: String): List<Question> {
        val responseType = object : ParameterizedTypeReference<List<Question>>() {}
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType).body ?: emptyList()
    }

    private fun getUrl(entry: Map.Entry<String, Int>): String {
        val url = "http://${entry.key.uppercase()}/api/questions?amount=${entry.value}"
        logger.info("URL: $url")
        return url
    }
}
