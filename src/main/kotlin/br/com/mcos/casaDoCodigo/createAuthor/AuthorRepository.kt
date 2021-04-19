package br.com.mcos.casaDoCodigo.createAuthor

import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface AuthorRepository : JpaRepository<Author, Long> {
    fun findAuthorByEmail(email: String): Author
}