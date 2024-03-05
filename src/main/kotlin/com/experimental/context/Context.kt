package com.experimental.context

import com.experimental.components.functions.PrintFunction
import com.experimental.components.functions.PrintlnFunction
import com.experimental.exceptions.ContextCreationException
import com.experimental.exceptions.FunctionNotFoundException
import com.experimental.exceptions.VariableNotFoundException

class Context(
    private val variables: MutableMap<VarName, Variable> = mutableMapOf(),
    private val functions: MutableMap<FunName, Function> = predefinedFunctions(),
) {
    fun addVariable(variable: Variable) {
        variables[variable.name] = variable
    }

    fun withVariables(variablesToAdd: List<Variable>): Context {
        val newVariables = variables.toMutableMap()
        variablesToAdd.forEach {
            if (newVariables.containsKey(it.name)) {
                throw ContextCreationException("Variable names collision: ${it.name}")
            }
            newVariables[it.name] = it
        }
        return Context(newVariables)
    }

    fun getValue(varName: VarName): TypedValue {
        return variables[varName]?.value ?: throw VariableNotFoundException(varName.name)
    }
//
//    private fun getVariable(varName: VarName): Variable {
//        return variables[varName] ?: throw VariableNotFoundException(varName.name)
//    }

    fun addFunction(function: Function) {
        functions[function.name] = function
    }

    fun getFunction(funName: FunName): Function {
        return functions[funName] ?: throw FunctionNotFoundException(funName.name)
    }

    override fun toString(): String {
        return "Context(variables=${variables.values.joinToString("\n")})"
    }


}

fun predefinedFunctions(): MutableMap<FunName, Function> = mutableMapOf(
    PrintFunction.name to PrintFunction,
    PrintlnFunction.name to PrintlnFunction
)
