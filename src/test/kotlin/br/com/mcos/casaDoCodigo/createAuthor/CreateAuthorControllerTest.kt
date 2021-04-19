package br.com.mcos.casaDoCodigo.createAuthor

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class CreateAuthorControllerTest(val authorRepository: AuthorRepository) {

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @BeforeEach
    fun setup() {
        authorRepository.deleteAll()
    }

    @Test
    fun `must create a author and return status code 201`() {
//        Cenary
        val newAuthorRequest = NewAuthorRequest("email@email.com.br", "Nome de Teste", "Descrição de teste.")

//        Action
        val request = HttpRequest.POST("/authors/", newAuthorRequest)
        val response = client.toBlocking().exchange(request, NewAuthorResponse::class.java)
        val possibleAuthor = authorRepository.findAuthorByEmail("email@email.com.br")

//        Validation
        assertEquals(response.status, HttpStatus.CREATED)

        assertEquals(response.body()!!.name, possibleAuthor.name)
        assertEquals(response.body()!!.email, possibleAuthor.email)
        assertEquals(response.body()!!.description, possibleAuthor.description)
        assertNotNull(response.body()!!.instantOfCreation)
        assertNotNull(response.body()!!.id)

        assertEquals(newAuthorRequest.name, possibleAuthor.name)
        assertEquals(newAuthorRequest.email, possibleAuthor.email)
        assertEquals(newAuthorRequest.description, possibleAuthor.description)
        assertNotNull(possibleAuthor.id)
        assertNotNull(possibleAuthor.instantOfCreation)
    }

    @Test
    fun `must return status 401 on request with invalid email`() {
        //        Cenary
        val newAuthorRequest = NewAuthorRequest("email.com.br", "Nome de Teste", "Descrição de teste.")

//        Action
        val request = HttpRequest.POST("/authors/", newAuthorRequest)


        val exceptionThrown = assertThrows(
            HttpClientResponseException::class.java
        ) { client.toBlocking().exchange(request, NewAuthorResponse::class.java) }

//        Validation
        assertEquals(exceptionThrown.status, HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `must return status 401 on request with empty name`() {
        //        Cenary
        val newAuthorRequest = NewAuthorRequest("email@email.com.br", "", "Descrição de teste.")

//        Action
        val request = HttpRequest.POST("/authors/", newAuthorRequest)


        val exceptionThrown = assertThrows(
            HttpClientResponseException::class.java
        ) { client.toBlocking().exchange(request, NewAuthorResponse::class.java) }

//        Validation
        assertEquals(exceptionThrown.status, HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `must return status 401 on request with empty description`() {
        //        Cenary
        val newAuthorRequest = NewAuthorRequest("email@email.com.br", "", "")

//        Action
        val request = HttpRequest.POST("/authors/", newAuthorRequest)


        val exceptionThrown = assertThrows(
            HttpClientResponseException::class.java
        ) { client.toBlocking().exchange(request, NewAuthorResponse::class.java) }

//        Validation
        assertEquals(exceptionThrown.status, HttpStatus.BAD_REQUEST)
    }

}