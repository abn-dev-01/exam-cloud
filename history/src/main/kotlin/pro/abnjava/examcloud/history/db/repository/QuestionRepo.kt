package pro.abnjava.examcloud.history.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import pro.abnjava.examcloud.history.db.model.QuestionEntity

interface QuestionRepo : JpaRepository<QuestionEntity, Long> {
}
