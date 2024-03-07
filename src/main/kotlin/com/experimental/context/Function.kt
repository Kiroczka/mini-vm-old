package com.experimental.context

import com.experimental.compilation.SyntaxElement
import com.experimental.components.Expression
import com.experimental.exceptions.FunctionInvocationException
import com.experimental.model.EmptyProgram
import com.experimental.model.Program

open class Function(
    val name: FunName,
    arguments: Arguments,
    private val statements: Program = EmptyProgram,
    private val returnExpression: Expression? = null
) {
    private val arguments = arguments.arguments

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
        statements.execute(functionContext)
        return returnExpression?.evaluate(functionContext) ?: Nothing
    }

    override fun toString(): String {
        return "Function(name=$name, statements=$statements, returnExpression=$returnExpression, arguments=$arguments)"
    }
}

data class FunName(val name: String) : SyntaxElement

data class Arguments(val arguments: List<VarDeclaration> = listOf()) : SyntaxElement