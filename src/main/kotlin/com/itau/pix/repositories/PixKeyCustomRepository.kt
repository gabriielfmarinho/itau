package com.itau.pix.repositories

import com.itau.pix.domain.dtos.PixKeyDetailsDto
import com.itau.pix.resources.v1.request.Params
import org.springframework.data.domain.Page
import java.util.*

interface PixKeyCustomRepository {

    fun findPaged(params: Params): Optional<Page<PixKeyDetailsDto>>

    companion object {
        const val FIND_PAGINATED_PIX_KEY_DETAILS: String =
            """
                select kp.id,
                       kp.key_type, 
                       kp.key_value,
                       ac.account_type,
                       ac.agency_number,
                       ac.account_number, 
                       c.first_name,
                       c.last_name, 
                       kp.date_create
                from keys_pix kp
                inner join accounts ac
                on kp.account_id = ac.id
                inner join clients c 
                on ac.client_id = c.id
                where ( ac.agency_number = :agencyNumber or :agencyNumber is null ) 
                and ( ac.account_number = :accountNumber or :accountNumber is null )
                and ( kp.key_type = :keyType or :keyType is null )
                and kp.inactive = false
                limit :offset, :limit
            """

        const val COUNT_PIX_KEY_DETAILS: String =
            """
                select count(*) 
                from keys_pix kp
                inner join accounts ac
                on kp.account_id = ac.id
                where ( ac.agency_number = :agencyNumber or :agencyNumber is null) 
                and (ac.account_number = :accountNumber or :accountNumber is null )
                and ( kp.key_type = :keyType or :keyType is null )
                and kp.inactive = false
            """
    }

}
