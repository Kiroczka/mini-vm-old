package com.experimental.context

import com.experimental.components.Expression
import com.experimental.components.Statement
import com.experimental.exceptions.FunctionInvocationException

open class Function(
    val name: FunName,
    private val arguments: List<Argument>,
    private val statements: List<Statement> = listOf(),
    private val returnExpression: Expression? = null
) {

    open fun execute(context: Context, argumentValues: List<TypedValue>): TypedValue {
        if (arguments.size != argumentValues.size) {
            throw FunctionInvocationException(
                "Function: ${name.name} expected ${arguments.size} arguments but got ${argumentValues.size}"
            )
        }
        val variables: List<Variable> = (argumentValues.indices).map {
            val argument = arguments[it]
            val typedValue = argumentValues[it]
            if (argument.type != null && argument.type != typedValue.type) {
                throw FunctionInvocationException(
                    "Function: ${name.name} expected ${it + 1} argument with type:${argument.type} " +
                            "but got ${typedValue.type}"
                )
            }
            Variable(argument.varName, typedValue)
        }.toList()
        val functionContext = context.withVariables(variables)
        statements.forEach { it.execute(functionContext) }
        return returnExpression?.evaluate(functionContext) ?: Nothing
    }
}

data class FunName(val name: String)