package com.experimental.impl

import com.experimental.components.Statement
import com.experimental.context.Context
import com.experimental.model.Program

class ProgramImpl(
    private val statements: List<Statement>,
) : Program {
    override fun execute(context: Context) {
        statements.forEach { it.execute(context) }
    }
}