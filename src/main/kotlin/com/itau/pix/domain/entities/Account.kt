package com.itau.pix.domain.entities

import com.itau.pix.domain.enums.AccountType
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "accounts")
data class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(cascade = [CascadeType.ALL])
    val client: Client,

    @Enumerated(EnumType.STRING)
    val accountType: AccountType,

    @Column(nullable = false)
    val agencyNumber: Int,

    @Column(nullable = false)
    val accountNumber: Int,

    ) : BaseEntity()
