package com.levox.data.entity

import com.levox.api.entity.SourceApiEntity
import com.levox.domain.entity.Source

fun SourceApiEntity.toDomain() = Source(name = name)