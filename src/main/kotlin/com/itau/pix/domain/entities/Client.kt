package com.itau.pix.domain.entities

import com.itau.pix.domain.enums.TypePerson
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "clients")
data class Client(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1,

    @Enumerated(EnumType.STRING)
    val typePerson: TypePerson,

    @Column(nullable = false)
    val firstName: String,

    val lastName: String = "",

    ) : BaseEntity()
