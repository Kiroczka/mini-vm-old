package com.experimental.compilation.factories

import com.experimental.compilation.Builder
import com.experimental.compilation.PartType
import com.experimental.exceptions.BuilderNotFoundException

class BuildersFactory(builders: List<Builder>) {
    private val builders: Map<PartType, Builder> = builders.groupBy { it.getType() }.mapValues { it.value.first() }

    fun getBuilder(type: PartType): Builder = builders[type] ?: throw BuilderNotFoundException(type)
}