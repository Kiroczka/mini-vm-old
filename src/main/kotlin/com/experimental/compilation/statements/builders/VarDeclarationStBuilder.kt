package com.experimental.compilation.statements.builders

import com.experimental.compilation.BuilderInput
import com.experimental.compilation.SyntaxElement
import com.experimental.compilation.Builder
import com.experimental.compilation.PartType
import com.experimental.compilation.StatementSyntaxElement
import com.experimental.context.Type
import com.experimental.context.VarDeclaration
import com.experimental.context.VarName

class VarDeclarationStBuilder : Builder {
    override fun build(input: BuilderInput): SyntaxElement {
        validateArgSize(2, input)
        val elements = input.elements
        val type = elements[0] as Type
        val name = elements[1] as VarName
        return VarDeclaration(name, type)
    }

    override fun getType(): PartType = StatementSyntaxElement.VAR_DECLARATION
}