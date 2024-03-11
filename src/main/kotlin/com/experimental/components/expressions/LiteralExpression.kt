package com.experimental.components.expressions

import com.experimental.components.Expression
import com.experimental.context.Context
import com.experimental.context.TypedValue

data class LiteralExpression(private val typedValue: TypedValue) : Expression {
    override fun evaluate(context: Context): TypedValue {
        return typedValue
    }
}