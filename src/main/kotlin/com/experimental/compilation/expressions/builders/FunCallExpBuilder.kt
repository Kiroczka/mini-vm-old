package com.experimental.compilation.expressions.builders

import com.experimental.compilation.BuilderInput
import com.experimental.compilation.ExpressionSyntaxElement
import com.experimental.compilation.SyntaxElement
import com.experimental.compilation.Builder
import com.experimental.compilation.PartType
import com.experimental.components.expressions.FunCallArgs
import com.experimental.components.expressions.FunctionCallExpression
import com.experimental.context.FunName

class FunCallExpBuilder : Builder {
    override fun build(input: BuilderInput): SyntaxElement {
        val elements = input.elements
        validateArgSize(2, input)
        val funName = elements[0] as FunName
        val funCallArgs = elements[1] as FunCallArgs
        return FunctionCallExpression(funName, funCallArgs)
    }

    override fun getType(): PartType = ExpressionSyntaxElement.FUN_CALL
}