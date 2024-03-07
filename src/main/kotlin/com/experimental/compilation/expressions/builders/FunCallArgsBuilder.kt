package com.experimental.compilation.expressions.builders

import com.experimental.compilation.BuilderInput
import com.experimental.compilation.ExpressionSyntaxElement
import com.experimental.compilation.SyntaxElement
import com.experimental.compilation.Builder
import com.experimental.compilation.PartType
import com.experimental.components.Expression
import com.experimental.components.expressions.FunCallArgs

class FunCallArgsBuilder : Builder {
    override fun getType(): PartType = ExpressionSyntaxElement.FUN_CALL_ARGS

    override fun build(input: BuilderInput): SyntaxElement {
        return FunCallArgs(input.elements.map { it as Expression })

    }
}