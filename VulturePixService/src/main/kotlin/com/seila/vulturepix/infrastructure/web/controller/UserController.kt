package com.seila.vulturepix.infrastructure.web.controller

import com.seila.vulturepix.dto.UserDTO
import com.seila.vulturepix.infrastructure.database.entity.User
import com.seila.vulturepix.infrastructure.database.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
     val userRepository: UserRepository? = null

    @PostMapping()
    fun createUser(@RequestBody userDTO: UserDTO): ResponseEntity<User> {
        val userDatabase = UserDTO.createUserFromDTO(userDTO)
        userRepository?.save(userDatabase)
        return ResponseEntity.status(HttpStatus.CREATED).body(userDatabase)

    }



}