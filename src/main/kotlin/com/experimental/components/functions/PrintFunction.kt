package com.experimental.components.functions

import com.experimental.context.Context
import com.experimental.context.FunName
import com.experimental.context.Function
import com.experimental.context.Nothing
import com.experimental.context.TypedValue

object PrintFunction : Function {
    override fun getName(): FunName = FunName("print")

    override fun execute(context: Context, argumentValues: List<TypedValue>): TypedValue {
        argumentValues.forEach { print(it.value) }
        return Nothing
    }
}

object PrintlnFunction : Function {
    override fun getName(): FunName = FunName("println")

    override fun execute(context: Context, argumentValues: List<TypedValue>): TypedValue {
        argumentValues.forEach { print(it.value) }
        println()
        return Nothing
    }
}