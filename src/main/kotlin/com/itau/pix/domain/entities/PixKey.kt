package com.itau.pix.domain.entities

import com.itau.pix.domain.enums.KeyType
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "keys_pix")
class PixKey(

    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(cascade = [CascadeType.ALL])
    val account: Account,

    @Enumerated(EnumType.STRING)
    val keyType: KeyType,

    @Column(unique = true)
    val keyValue: String,

    ) : BaseEntity()