package com.itau.pix.domain.entities

import com.itau.pix.domain.enums.AccountType
import com.itau.pix.domain.enums.TypePerson
import javax.persistence.*

@Entity
@Table(name = "accounts")
data class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1,

    @Enumerated(EnumType.STRING)
    val accountType: AccountType,

    val agencyNumber: Int,

    val accountNumber: Int,

    ) : BaseEntity()