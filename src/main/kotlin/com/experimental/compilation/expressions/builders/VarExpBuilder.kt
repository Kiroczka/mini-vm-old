package com.experimental.compilation.expressions.builders

import com.experimental.compilation.BuilderInput
import com.experimental.compilation.ExpressionSyntaxType
import com.experimental.compilation.SyntaxElement
import com.experimental.compilation.Builder
import com.experimental.compilation.SyntaxType
import com.experimental.components.expressions.VariableExpression
import com.experimental.context.VarName

class VarExpBuilder : Builder {
    override fun getType(): SyntaxType = ExpressionSyntaxType.VAR

    override fun build(input: BuilderInput): SyntaxElement {
        val elements = input.elements
        validateArgSize(1, input)
        val part = elements[0] as VarName
        return VariableExpression(part)
    }
}