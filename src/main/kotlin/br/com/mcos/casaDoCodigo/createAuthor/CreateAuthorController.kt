package br.com.mcos.casaDoCodigo.createAuthor

import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.validation.Valid

@Controller("/authors")
@Validated
@Singleton
class CreateAuthorController(val authorRepository: AuthorRepository) {

    val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Post
    fun create(@Valid newAuthorRequest: NewAuthorRequest): MutableHttpResponse<NewAuthorResponse>? {
        LOGGER.info("New request of creation user - ${newAuthorRequest.email}")
        val newAuthor = newAuthorRequest.toAuthor()
        val savedAuthor = authorRepository.save(newAuthor)
        LOGGER.info("Saved Author with id ${savedAuthor.id} and Email: ${savedAuthor.email} at ${savedAuthor.instantOfCreation}")
        val response = savedAuthor.toAuthorResponse()
        return HttpResponse.created(response)
    }
}