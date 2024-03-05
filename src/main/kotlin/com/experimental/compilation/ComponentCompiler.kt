package com.experimental.compilation

import com.experimental.components.Component
import com.experimental.exceptions.UnknownComponentException

open class ComponentCompiler<T : Component>(private val concreteCompilers: List<ConcreteComponentCompiler<T>>) {
    fun compile(code: String): SuccessResult<T> {
        concreteCompilers.forEach { parser ->
            parser.compile(code).let {
                if (it is SuccessResult<T>) {
                    return it
                }
            }
        }
        val firstNonEmptyLine = code.split("\n").find { it.isNotEmpty() } ?: ""
        throw UnknownComponentException(firstNonEmptyLine)
    }

}
