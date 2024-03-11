package com.experimental.context

import com.experimental.compilation.SyntaxElement
import com.experimental.components.Expression
import com.experimental.exceptions.IncorrectFunArgTypesException
import com.experimental.exceptions.IncorrectFunArgsSizeException
import com.experimental.model.EmptyProgram
import com.experimental.model.Program

interface Function {
    fun getName(): FunName
    fun execute(context: Context, argumentValues: List<TypedValue>): TypedValue

}

data class FunctionImpl(
    private val name: FunName,
    private val arguments: Arguments,
    private val statements: Program = EmptyProgram,
    private val returnExpression: Expression? = null
) : Function {
    override fun getName(): FunName = name

    override fun execute(context: Context, argumentValues: List<TypedValue>): TypedValue {
        validateArgSize(argumentValues)
        val variables: List<Variable> = (argumentValues.indices).map {
            val typedValue = argumentValues[it]
            createVariable(typedValue, it)
        }.toList()
        val functionContext = context.withVariables(variables)
        statements.execute(functionContext)
        return returnExpression?.evaluate(functionContext) ?: Nothing
    }

    private fun createVariable(typedValue: TypedValue, it: Int): Variable {
        val argument = arguments.declarations[it]
        validateArgType(argument, typedValue, it)
        return Variable(argument.varName, typedValue)
    }

    private fun validateArgType(argument: VarDeclaration, typedValue: TypedValue, it: Int) {
        if (argument.type != null && argument.type != typedValue.type) {
            throw IncorrectFunArgTypesException(name, it + 1, argument.type, typedValue.type)
        }
    }

    private fun validateArgSize(argumentValues: List<TypedValue>) {
        if (arguments.declarations.size != argumentValues.size) {
            throw IncorrectFunArgsSizeException(name, arguments.declarations.size, argumentValues.size)
        }
    }
}

data class FunName(val name: String) : SyntaxElement

data class Arguments(val declarations: List<VarDeclaration> = listOf()) : SyntaxElement