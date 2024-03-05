package com.experimental.compilation

import com.experimental.Keywords
import com.experimental.context.Type
import com.experimental.exceptions.UnknownTypeException

class TypeCompiler {

    fun compile(type: String): Type? {
        if (type == Keywords.VAR_KEYWORD) {
            return null
        }
        if (!Type.entries.map { it.name }.toList().contains(type.uppercase())) {
            throw UnknownTypeException(type)
        }
        return Type.valueOf(type.uppercase())
    }
}