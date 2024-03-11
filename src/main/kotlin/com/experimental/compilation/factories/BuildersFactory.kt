package com.experimental.compilation.factories

import com.experimental.compilation.Builder
import com.experimental.compilation.SyntaxType
import com.experimental.exceptions.BuilderNotFoundException

class BuildersFactory(builders: List<Builder>) {
    private val builders: Map<SyntaxType, Builder> = builders.groupBy { it.getType() }.mapValues { it.value.first() }

    fun getBuilder(type: SyntaxType): Builder = builders[type] ?: throw BuilderNotFoundException(type)
}