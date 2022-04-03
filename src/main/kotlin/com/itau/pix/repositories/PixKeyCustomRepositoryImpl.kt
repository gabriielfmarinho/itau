package com.itau.pix.repositories

import com.itau.pix.domain.dtos.PixKeyDetailsDto
import com.itau.pix.repositories.PixKeyCustomRepository.Companion.COUNT_PIX_KEY_DETAILS
import com.itau.pix.repositories.PixKeyCustomRepository.Companion.FIND_PAGINATED_PIX_KEY_DETAILS
import com.itau.pix.resources.v1.request.Params
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.*

class PixKeyCustomRepositoryImpl(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate

) : PixKeyCustomRepository {

    override fun findPaged(params: Params): Optional<Page<PixKeyDetailsDto>> {
        val parameters = getParameters(params)
        val total = countTotal(parameters)
        if (total <= 0) {
            return Optional.empty()
        }
        val pixKeys = findPixKeys(parameters)
        return Optional.of(getPage(params, pixKeys, total))
    }

    private fun getPage(
        params: Params,
        pixKeys: MutableList<PixKeyDetailsDto>,
        total: Long
    ): Page<PixKeyDetailsDto> {
        val page = PageRequest.of(params.page, params.size)
        return PageImpl(pixKeys, page, total)
    }

    private fun findPixKeys(parameters: MapSqlParameterSource): MutableList<PixKeyDetailsDto> =
        namedParameterJdbcTemplate.query(FIND_PAGINATED_PIX_KEY_DETAILS, parameters, PixKeyDetailsDtoRowMapper())

    private fun countTotal(parameters: MapSqlParameterSource): Long {
        return namedParameterJdbcTemplate
            .queryForObject(COUNT_PIX_KEY_DETAILS, parameters, Long::class.java) as Long
    }

    private fun getOffSet(params: Params) = params.page * params.size

    private fun getParameters(
        params: Params
    ): MapSqlParameterSource {
        val parameters = MapSqlParameterSource()
        parameters.addValue(AGENCY_NUMBER_FIELD, params.agencyNumber)
        parameters.addValue(ACCOUNT_NUMBER_FIELD, params.accountNumber)
        parameters.addValue(KEY_TYPE_FIELD, params.keyType?.name)
        parameters.addValue(LIMIT_PARAMETER, params.size)
        parameters.addValue(OFFSET_PARAMETER, getOffSet(params))
        return parameters
    }

    companion object {
        private const val AGENCY_NUMBER_FIELD = "agencyNumber"
        private const val ACCOUNT_NUMBER_FIELD = "accountNumber"
        private const val KEY_TYPE_FIELD = "keyType"
        private const val LIMIT_PARAMETER = "limit"
        private const val OFFSET_PARAMETER = "offset"
    }
}
