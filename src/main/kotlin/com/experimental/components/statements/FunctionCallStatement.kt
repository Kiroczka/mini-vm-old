package com.experimental.components.statements

import com.experimental.components.Statement
import com.experimental.components.expressions.FunctionCallExpression
import com.experimental.context.Context

class FunctionCallStatement(private val funCallExpression: FunctionCallExpression) : Statement {
    override fun execute(context: Context) {
        funCallExpression.evaluate(context)
    }

}