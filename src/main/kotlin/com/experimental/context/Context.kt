package com.experimental.context

import com.experimental.components.functions.PrintFunction
import com.experimental.components.functions.PrintlnFunction
import com.experimental.exceptions.FunctionNotFoundException
import com.experimental.exceptions.VarNameCollisionException
import com.experimental.exceptions.VariableNotFoundException

data class Context(
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
                throw VarNameCollisionException(it.name)
            }
            newVariables[it.name] = it
        }
        return Context(newVariables, functions.toMutableMap())
    }

    fun withFunctions(functionsToAdd: List<Function>): Context {
        val newFunctions =
            (functionsToAdd + functions.values)
                .groupBy { it.getName() }
                .mapValues { entry -> entry.value.first() }
                .toMutableMap()

        return Context(variables.toMutableMap(), newFunctions)
    }

    fun getValue(varName: VarName): TypedValue {
        return variables[varName]?.value ?: throw VariableNotFoundException(varName.name)
    }

    fun addFunction(function: Function) {
        functions[function.getName()] = function
    }

    fun getFunction(funName: FunName): Function {
        return functions[funName] ?: throw FunctionNotFoundException(funName.name)
    }

}

fun predefinedFunctions(): MutableMap<FunName, Function> = mutableMapOf(
    PrintFunction.getName() to PrintFunction,
    PrintlnFunction.getName() to PrintlnFunction
)
