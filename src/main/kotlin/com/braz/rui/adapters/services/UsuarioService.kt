package com.braz.rui.adapters.services

import com.braz.rui.NovoUsuarioRequest
import com.braz.rui.adapters.repositories.UsuarioRepository
import com.braz.rui.application.domain.Usuario
import io.grpc.Status
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Singleton
import javax.persistence.PersistenceException


const val ERRO_USUARIO_SEM_LOGIN = "Login do usuario não pode ficar em branco."
const val ERRO_DESCONHECIDO = "Erro desconhecido."

@Singleton
class UsuarioService(private val usuarioRepository: UsuarioRepository) {

    fun cadastraUsuario(request: NovoUsuarioRequest): Usuario {
        var usuario = Usuario(request)

        if (usuario.login.isBlank()) {
            throw buildError("Login em branco.")
        }
        usuario.senha = hashSenha(usuario.senha)

        try {
            usuario = usuarioRepository.save(usuario)
        } catch (exception: PersistenceException) {
            throw Status.INTERNAL
                .withDescription("Login ja cadastrado.")
                .asException()
        }
        return usuario
    }

    private fun hashSenha(senha: String): String {

        return BCrypt.hashpw(senha, BCrypt.gensalt())
    }

    private fun buildError(error: String) = when (error) {
        ERRO_USUARIO_SEM_LOGIN -> Status.FAILED_PRECONDITION
            .withDescription(ERRO_USUARIO_SEM_LOGIN)
            .asException()

        else -> Status.UNKNOWN
            .withDescription(ERRO_DESCONHECIDO)
            .asException()
    }

}