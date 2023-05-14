package com.mobile.dto

import com.mobile.models.Type
import kotlinx.datetime.Instant
import java.time.LocalDate

data class TodoResponse(val name: String, val creationDate: LocalDate,
                        val priority: Int, val description: String?, val type: Type
)