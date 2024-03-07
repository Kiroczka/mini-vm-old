package com.experimental.compilation.statements.builders

import com.experimental.compilation.BuilderInput
import com.experimental.compilation.SyntaxElement
import com.experimental.compilation.Builder
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.StatementSyntaxType
import com.experimental.context.Type
import com.experimental.context.VarDeclaration
import com.experimental.context.VarName

class VarDeclarationStBuilder : Builder {
    override fun getType(): SyntaxType = StatementSyntaxType.VAR_DECLARATION

    override fun build(input: BuilderInput): SyntaxElement {
        validateArgSize(2, input)
        val elements = input.elements
        val type = elements[0] as Type
        val name = elements[1] as VarName
        return VarDeclaration(name, type)
    }
}