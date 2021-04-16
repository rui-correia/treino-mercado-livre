package com.braz.rui.adapters.endpoints

import com.braz.rui.NovoUsuarioRequest
import com.braz.rui.NovoUsuarioResponse
import com.braz.rui.TreinoMercadoLivreServiceGrpcKt
import com.braz.rui.adapters.services.UsuarioService
import javax.inject.Singleton

@Singleton
class UsuarioEndpoint(val usuarioService: UsuarioService) :
    TreinoMercadoLivreServiceGrpcKt.TreinoMercadoLivreServiceCoroutineImplBase() {
    override suspend fun novoUsuario(request: NovoUsuarioRequest): NovoUsuarioResponse {
        val usuario = usuarioService.cadastraUsuario(request)
        return usuario.toResponse()
    }

}