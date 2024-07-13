package pro.abnjava.examcloud.history.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import pro.abnjava.examcloud.history.db.model.QuestionEntity

interface QuestionRepo : JpaRepository<QuestionEntity, Long> {

    // Define a method for PostgreSQL
    @Query(value = "SELECT * FROM questions ORDER BY RANDOM() LIMIT ?1", nativeQuery = true)
    fun findRandomRecordsPostgres(count: Int): List<QuestionEntity>

    // Define a method for H2
    @Query(value = "SELECT * FROM questions ORDER BY RAND() LIMIT ?1", nativeQuery = true)
    fun findRandomRecordsH2(count: Int): List<QuestionEntity>
}
