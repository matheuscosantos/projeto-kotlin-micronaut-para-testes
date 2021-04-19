package br.com.mcos.casaDoCodigo.createAuthor

import java.time.LocalDateTime

class NewAuthorResponse(
    val id: Long?,
    val name: String,
    val description: String,
    val email: String,
    val instantOfCreation: LocalDateTime
)