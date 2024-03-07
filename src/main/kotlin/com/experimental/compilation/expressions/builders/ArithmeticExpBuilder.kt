package com.experimental.compilation.expressions.builders

import com.experimental.compilation.BuilderInput
import com.experimental.compilation.ExpressionSyntaxElement
import com.experimental.compilation.SyntaxElement
import com.experimental.compilation.Builder
import com.experimental.compilation.PartType
import com.experimental.components.Expression
import com.experimental.components.expressions.ArithmeticExpression
import com.experimental.components.expressions.Operator

class ArithmeticExpBuilder : Builder {
    override fun build(input: BuilderInput): SyntaxElement {
        validateArgSize(3, input)
        val elements = input.elements
        val ex1 = elements[0] as Expression
        val ex2 = elements[1] as Expression
        val operator = elements[2] as Operator
        return ArithmeticExpression(ex1, ex2, operator)
    }

    override fun getType(): PartType = ExpressionSyntaxElement.ARITHMETIC
}