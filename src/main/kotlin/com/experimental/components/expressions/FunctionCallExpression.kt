package com.experimental.components.expressions

import com.experimental.components.Expression
import com.experimental.context.Context
import com.experimental.context.FunName
import com.experimental.context.TypedValue

class FunctionCallExpression(private val funName: FunName, private val arguments: List<Expression>) : Expression {
    override fun evaluate(context: Context): TypedValue {
        val function = context.getFunction(funName)
        val argumentValues = arguments.map { it.evaluate(context) }
        return function.execute(context, argumentValues)
    }
}