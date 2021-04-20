package com.braz.rui.adapters.repositories

import com.braz.rui.application.domain.Usuario
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface UsuarioRepository: JpaRepository<Usuario, Int> {

    fun findByLogin(login: String): Usuario
    fun existsByLogin(login: String): Boolean
}