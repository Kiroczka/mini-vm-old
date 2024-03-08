package com.experimental.components.expressions

import com.experimental.compilation.SyntaxElement
import com.experimental.components.Expression
import com.experimental.context.Context
import com.experimental.context.IntValue
import com.experimental.context.TypedValue

data class ArithmeticExpression(
    private val first: Expression,
    private val second: Expression,
    private val operator: Operator
) : Expression {
    override fun evaluate(context: Context): TypedValue {
        val firstValue = evaluateInt(context, first)
        val secondValue = evaluateInt(context, second)
        return IntValue(operator.apply(firstValue, secondValue))
    }

    private fun evaluateInt(context: Context, expression: Expression): Int {
        val result = expression.evaluate(context)
        if (result !is IntValue) {
            throw ArithmeticException("Expressions should return Int to use arithmetic operators")
        }
        return result.value
    }
}

sealed interface Operator : SyntaxElement {
    fun apply(x: Int, y: Int): Int
}

data object PlusOperator : Operator {
    override fun apply(x: Int, y: Int): Int = x + y
}

data object MinusOperator : Operator {
    override fun apply(x: Int, y: Int): Int = x - y
}


data object MultiplyOperator : Operator {
    override fun apply(x: Int, y: Int): Int = x * y
}