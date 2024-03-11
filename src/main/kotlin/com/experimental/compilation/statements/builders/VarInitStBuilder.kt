package com.experimental.compilation.statements.builders

import com.experimental.compilation.BuilderInput
import com.experimental.compilation.SyntaxElement
import com.experimental.compilation.Builder
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.StatementSyntaxType
import com.experimental.components.Expression
import com.experimental.components.statements.VarInitialization
import com.experimental.context.Type
import com.experimental.context.VarName

class VarInitStBuilder : Builder {
    override fun getType(): SyntaxType = StatementSyntaxType.VAR_INIT

    override fun build(input: BuilderInput): SyntaxElement {
        validateArgSize(3, input)
        val elements = input.elements
        val type = elements[0] as Type
        val name = elements[1] as VarName
        val expression = elements[2] as Expression
        return VarInitialization(name, type, expression)
    }
}