package com.itau.pix.domain.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(

    @CreationTimestamp
    val dateCreate: LocalDateTime? = null,

    @UpdateTimestamp
    val dateUpdate: LocalDateTime? = null,
)
