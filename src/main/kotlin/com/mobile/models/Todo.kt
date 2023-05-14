package com.mobile.models

import kotlinx.datetime.Instant
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate


data class Todo(val id: Long,
                var name:String,
                var creationDate: LocalDate,
                var user: User){
    var priority: Int = 2
    var type: Type = Type.GLOBAL
    var description: String? = null
    var endDate: LocalDate? = null
}

object Todos : Table(){
    val id = long("id").autoIncrement()
    val name = text("name")
    val creationDate = date("creation_date")
    val priority = integer("priority")
    val type = enumerationByName<Type>("type", 50)
    val description = text("description").nullable()
    val endDate = date("end_date").nullable()
    val user = long("user_id").references(Users.id)

    override val primaryKey = PrimaryKey(id)
}
