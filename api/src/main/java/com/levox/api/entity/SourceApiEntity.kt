package com.levox.api.entity

import kotlinx.serialization.Serializable

@Serializable
data class SourceApiEntity(
    val id: String,
    val name: String
)
