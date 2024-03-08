package com.experimental.components.expressions

import com.experimental.compilation.SyntaxElement
import com.experimental.components.Expression
import com.experimental.context.Context
import com.experimental.context.FunName
import com.experimental.context.TypedValue

data class FunctionCallExpression(private val funName: FunName, private val funCallArgs: FunCallArgs) :
    Expression {
    override fun evaluate(context: Context): TypedValue {
        val function = context.getFunction(funName)
        val argumentValues = funCallArgs.expressions.map { it.evaluate(context) }
        return function.execute(context, argumentValues)
    }
}

data class FunCallArgs(val expressions: List<Expression> = listOf()) : SyntaxElement