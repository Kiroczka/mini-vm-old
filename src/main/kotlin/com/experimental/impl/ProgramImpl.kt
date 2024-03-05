package com.experimental.impl

import com.experimental.components.Statement
import com.experimental.context.Context
import com.experimental.model.Program

class ProgramImpl(
    private val statements: List<Statement>,
    private val context: Context = Context()
) : Program {
    override fun execute() {
        statements.forEach { it.execute(context) }
        println(context)
    }
}