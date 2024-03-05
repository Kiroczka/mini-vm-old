package com.experimental.components.expressions

import com.experimental.components.Expression
import com.experimental.context.Context
import com.experimental.context.IntValue
import com.experimental.context.TypedValue

sealed class ArithmeticExpression(
    private val first: Expression,
    private val second: Expression
) : Expression {
    override fun evaluate(context: Context): TypedValue {
        val firstValue = evaluateInt(context, first)
        val secondValue = evaluateInt(context, second)
        return IntValue(apply(firstValue, secondValue))
    }

    abstract fun apply(x: Int, y: Int): Int

    private fun evaluateInt(context: Context, expression: Expression): Int {
        val result = expression.evaluate(context)
        if (result !is IntValue) {
            throw ArithmeticException("Expressions should return Int to use arithmetic operators")
        }
        return result.value
    }
}

class PlusExpression(first: Expression, second: Expression) : ArithmeticExpression(first, second) {
    override fun apply(x: Int, y: Int): Int = x + y
}

class MinusExpression(first: Expression, second: Expression) : ArithmeticExpression(first, second) {
    override fun apply(x: Int, y: Int): Int = x - y
}


class MultiplyExpression(first: Expression, second: Expression) : ArithmeticExpression(first, second) {
    override fun apply(x: Int, y: Int): Int = x * y
}