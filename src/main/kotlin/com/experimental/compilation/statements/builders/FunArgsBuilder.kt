package com.experimental.compilation.statements.builders

import com.experimental.compilation.BuilderInput
import com.experimental.compilation.ContextSyntaxElement
import com.experimental.compilation.SyntaxElement
import com.experimental.compilation.Builder
import com.experimental.compilation.PartType
import com.experimental.context.Arguments
import com.experimental.context.VarDeclaration

class FunArgsBuilder : Builder {
    override fun getType(): PartType = ContextSyntaxElement.ARGS

    override fun build(input: BuilderInput): SyntaxElement {
        return Arguments(input.elements.map { it as VarDeclaration })

    }
}