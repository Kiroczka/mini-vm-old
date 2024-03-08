package com.experimental.components.statements

import com.experimental.components.Statement
import com.experimental.context.Context
import com.experimental.context.Function

data class FunDeclaration(private val function: Function) : Statement {
    override fun execute(context: Context) {
        context.addFunction(function)
    }
}