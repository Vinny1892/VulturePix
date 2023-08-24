package com.seila.vulturepix.infrastructure.database.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

import java.time.LocalDateTime
import java.util.UUID

@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "users")
open class User(
    @Column()
   open var name: String,
    @Column()
    open var email: String,

    @Column()
    open var password: String,
) {
    @Temporal(TemporalType.TIMESTAMP)
    open var createdAt: LocalDateTime? = null

    @Temporal(TemporalType.TIMESTAMP)
    open var  updatedAt: LocalDateTime? = null

    @Column(nullable = true)
    open var verifiedAt: LocalDateTime? = null


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    open lateinit var id: UUID

    @PrePersist
    protected fun onCreate() {
        this.updatedAt = LocalDateTime.now()
        this.createdAt = LocalDateTime.now()
    }

    @PreUpdate
    protected fun onUpdate() {
        this.updatedAt = LocalDateTime.now()
    }
}