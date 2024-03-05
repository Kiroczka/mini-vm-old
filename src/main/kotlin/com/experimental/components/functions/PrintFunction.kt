package com.experimental.components.functions

import com.experimental.context.Argument
import com.experimental.context.Context
import com.experimental.context.FunName
import com.experimental.context.Function
import com.experimental.context.Nothing
import com.experimental.context.Type
import com.experimental.context.TypedValue
import com.experimental.context.VarName
import java.util.*

object PrintFunction : Function(
    name = FunName("print"),
    arguments = listOf(
        Argument(VarName(UUID.randomUUID().toString()), Type.TEXT),
    ),
) {
    override fun execute(context: Context, argumentValues: List<TypedValue>): TypedValue {
        argumentValues.forEach { print(it.value) }
        return Nothing
    }
}

object PrintlnFunction : Function(
    name = FunName("println"),
    arguments = listOf(
        Argument(VarName(UUID.randomUUID().toString()), Type.TEXT),
    ),
) {
    override fun execute(context: Context, argumentValues: List<TypedValue>): TypedValue {
        argumentValues.forEach { print(it.value) }
        println()
        return Nothing
    }
}