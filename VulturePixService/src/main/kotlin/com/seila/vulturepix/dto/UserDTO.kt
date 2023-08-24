package com.seila.vulturepix.dto

import com.seila.vulturepix.infrastructure.database.entity.User

data class UserDTO(
    val email: String,
    val name: String,
    val password: String,
) {

    companion object {
        fun createDtoFromUser(userDatabase: User) : UserDTO {
            return UserDTO(
                email = userDatabase.email,
                name = userDatabase.name,
                password = userDatabase.password
            )
        }

        fun createUserFromDTO(userDTO: UserDTO) : User {
            return User(
                email = userDTO.email,
                name = userDTO.name,
                password = userDTO.password
            )
        }
    }

}
