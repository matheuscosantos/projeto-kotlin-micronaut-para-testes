package br.com.mcos.casaDoCodigo.createAuthor

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Author(
    val email: String,
    val name: String,
    val description: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val instantOfCreation = LocalDateTime.now()

    fun toAuthorResponse(): NewAuthorResponse {
        return NewAuthorResponse(
            this.id,
            this.name,
            this.description,
            this.email,
            this.instantOfCreation
        )
    }
}
