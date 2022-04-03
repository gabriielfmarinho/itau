package com.itau.pix.resources.v1.request

import com.itau.pix.domain.enums.TypePerson
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateClientRequest(
    @field:NotNull
    val typePerson: TypePerson,

    @field:NotBlank
    @field:Size(min = 1, max = 30)
    var accountHolderName: String,

    @field:Size(min = 0, max = 77)
    var accountHolderLastName: String?
)
