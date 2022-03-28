package com.itau.pix.domain.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

open class BaseEntity(

    @CreationTimestamp
    val dateCreate: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    val dateUpdate: LocalDateTime = LocalDateTime.now(),
)
