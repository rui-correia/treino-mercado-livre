package com.braz.rui.application.domain

import com.braz.rui.NovoUsuarioRequest
import com.braz.rui.NovoUsuarioResponse
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "t_usuario")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Integer?,
    val idExterno: String,
    val login: String,
    val senha: String,
    val dataCadastro: LocalDateTime = LocalDateTime.now()
) {
    constructor(usuarioRequest: NovoUsuarioRequest) :
            this(
                null,
                UUID.randomUUID().toString(),
                usuarioRequest.login,
                usuarioRequest.senha
            )

    fun toResponse(): NovoUsuarioResponse {
        return NovoUsuarioResponse.newBuilder()
            .setLogin(this.login)
            .build()
    }
}
