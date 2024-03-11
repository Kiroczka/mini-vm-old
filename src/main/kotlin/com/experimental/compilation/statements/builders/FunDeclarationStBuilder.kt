package com.experimental.compilation.statements.builders

import com.experimental.compilation.BuilderInput
import com.experimental.compilation.SyntaxElement
import com.experimental.compilation.Builder
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.StatementSyntaxType
import com.experimental.components.Expression
import com.experimental.components.statements.FunDeclaration
import com.experimental.context.Arguments
import com.experimental.context.FunName
import com.experimental.context.FunctionImpl
import com.experimental.model.Program

class FunDeclarationStBuilder : Builder {
    override fun getType(): SyntaxType = StatementSyntaxType.FUN_DECLARATION

    override fun build(input: BuilderInput): SyntaxElement {
        validateArgSize(3,4, input)
        val elements = input.elements
        val funName = elements[0] as FunName
        val arguments = elements[1] as Arguments
        val body = elements[2] as Program
        val returnExpression = if (elements.size > 3) elements[3] as Expression else null
        return FunDeclaration(FunctionImpl(funName, arguments, body, returnExpression))
    }
}