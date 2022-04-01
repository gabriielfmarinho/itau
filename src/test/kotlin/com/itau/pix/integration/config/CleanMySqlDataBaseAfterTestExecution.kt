package com.itau.pix.integration.config

import com.itau.pix.log.loggerFor
import org.springframework.test.context.TestContext
import org.springframework.test.context.TestExecutionListener
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.util.function.Consumer
import javax.sql.DataSource

class CleanMySqlDataBaseAfterTestExecution : TestExecutionListener {

    companion object {
        private val log = loggerFor(CleanMySqlDataBaseAfterTestExecution::class.java)
    }

    private lateinit var dataSource: DataSource

    @Throws(Exception::class)
    override fun afterTestExecution(testContext: TestContext) {
        dataSource = getDataSource(testContext)
        cleanDataBase()
    }

    @Throws(SQLException::class)
    private fun cleanDataBase() {
        log.info("init clean database")
        val connection = dataSource.connection
        val statement = connection.createStatement()
        try {
            connection.use {
                statement.use {
                    execute(statement, "SET @@foreign_key_checks = 0")
                    val tableNamesToTrucate = executeQuery(statement, "SHOW TABLES")
                    getRows(tableNamesToTrucate)
                        ?.forEach(Consumer { tableName: String -> execute(statement, "TRUNCATE TABLE ${tableName}") })
                    val tableNamesToAlter = executeQuery(statement, "SHOW TABLES")
                    getRows(tableNamesToAlter)
                        ?.forEach(Consumer { tableName: String ->
                            execute(statement, "ALTER TABLE ${tableName} AUTO_INCREMENT = 1")
                        })
                    execute(statement, "SET @@foreign_key_checks = 1")
                }
            }
        } catch (e: Exception) {
            log.error("cannot be execute query", e)
        }
    }

    @Throws(SQLException::class)
    private fun executeQuery(statement: Statement, sql: String): ResultSet {
        return statement.executeQuery(sql)
    }

    private fun getRows(resultSet: ResultSet): Set<String>? {
        try {
            resultSet.use {
                val data = HashSet<String>()
                while (resultSet.next()) {
                    data.add(resultSet.getString(1))
                }
                return data
            }
        } catch (e: Exception) {
            log.error("cannot be extract rows", e)
            return null
        }
    }

    private fun getDataSource(testContext: TestContext) =
        testContext.applicationContext.getBean(DataSource::class.java)

    private fun execute(statement: Statement, sql: String) {
        try {
            statement.execute(sql)
        } catch (e: Exception) {
            log.error("cannot be execute query, ", e)
        }
    }
}