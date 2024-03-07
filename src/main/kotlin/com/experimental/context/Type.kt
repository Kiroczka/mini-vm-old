package com.experimental.context

import com.experimental.compilation.SyntaxElement

enum class Type : SyntaxElement {
    INT,
    TEXT,
    NOT_SPECIFIED,
    NOTHING
}

sealed class TypedValue(val type: Type, open val value: Any)
data object Nothing : TypedValue(Type.NOTHING, Unit)

class IntValue(override val value: Int) : TypedValue(type = Type.INT, value) {
    override fun toString(): String {
        return "IntValue(value=$value)"
    }
}

class TextValue(override val value: String) : TypedValue(type = Type.TEXT, value) {
    override fun toString(): String {
        return "TextValue(value='$value')"
    }
}