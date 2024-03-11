package com.experimental.components.expressions

import com.experimental.components.Expression
import com.experimental.context.Context
import com.experimental.context.TypedValue
import com.experimental.context.VarName

data class VariableExpression(private val varName: VarName) : Expression {
    override fun evaluate(context: Context): TypedValue {
        return context.getValue(varName)
    }
}