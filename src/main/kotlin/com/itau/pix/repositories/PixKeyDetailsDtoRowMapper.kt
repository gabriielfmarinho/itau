package com.itau.pix.repositories

import com.itau.pix.domain.dtos.PixKeyDetailsDto
import com.itau.pix.domain.enums.AccountType
import com.itau.pix.domain.enums.KeyType
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class PixKeyDetailsDtoRowMapper : RowMapper<PixKeyDetailsDto> {
    override fun mapRow(rs: ResultSet, rowNum: Int): PixKeyDetailsDto? {
        return PixKeyDetailsDto(
            keyType = KeyType.valueOf(rs.getString(KEY_TYPE_COLUMN)),
            keyValue = rs.getString(KEY_VALUE_COLUMN),
            accountType = AccountType.valueOf(rs.getString(ACCOUNT_TYPE_COLUMN)),
            agencyNumber = rs.getInt(AGENCY_NUMBER_COLUMN),
            accountNumber = rs.getInt(ACCOUNT_NUMBER_COLUMN),
            accountHolderName = rs.getString(FIRST_NAME_COLUMN),
            accountHolderLastName = rs.getString(LAST_NAME_COLUMN)
        );
    }

    companion object {
        private const val KEY_TYPE_COLUMN = "key_type"
        private const val KEY_VALUE_COLUMN = "key_value"
        private const val ACCOUNT_TYPE_COLUMN = "account_type"
        private const val AGENCY_NUMBER_COLUMN = "agency_number"
        private const val ACCOUNT_NUMBER_COLUMN = "account_number"
        private const val FIRST_NAME_COLUMN = "first_name"
        private const val LAST_NAME_COLUMN = "last_name"
    }
}