package com.experimental.components.statements

import com.experimental.components.Expression
import com.experimental.components.Statement
import com.experimental.context.Context
import com.experimental.context.Type
import com.experimental.context.VarName
import com.experimental.context.Variable
import com.experimental.exceptions.VariableHasIncorrectTypeException

data class VarInitialization(
    private val varName: VarName,
    private val type: Type,
    private val expression: Expression
) : Statement {
    override fun execute(context: Context) {
        val value = expression.evaluate(context)
        if (type != Type.NOT_SPECIFIED && value.type != type) {
            throw VariableHasIncorrectTypeException(varName, type, value.type)
        }
        context.addVariable(Variable(varName, value))
    }
}