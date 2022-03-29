package com.itau.pix.domain.entities

import com.itau.pix.domain.enums.AccountType
import javax.persistence.*

@Entity
@Table(name = "accounts")
data class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1,

    @ManyToOne(cascade = [CascadeType.ALL])
    val client: Client,

    @Enumerated(EnumType.STRING)
    val accountType: AccountType,

    @Column(nullable = false)
    val agencyNumber: Int,

    @Column(nullable = false)
    val accountNumber: Int,

    ) : BaseEntity()