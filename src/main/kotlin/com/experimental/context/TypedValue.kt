package com.experimental.context


sealed class TypedValue(val type: Type, open val value: Any)
data object Nothing : TypedValue(Type.NOTHING, Unit)

data class IntValue(override val value: Int) : TypedValue(type = Type.INT, value)

data class TextValue(override val value: String) : TypedValue(type = Type.TEXT, value)