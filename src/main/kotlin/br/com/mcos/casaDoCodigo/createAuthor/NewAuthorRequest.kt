package br.com.mcos.casaDoCodigo.createAuthor

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Introspected
class NewAuthorRequest(
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank val name: String,
    @field:NotBlank val description: String
) {
    fun toAuthor(): Author {
        return Author(email, name, description)
    }
}