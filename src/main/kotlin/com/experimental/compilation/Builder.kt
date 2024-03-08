package com.experimental.compilation

import com.experimental.exceptions.BuilderIncorrectArgsSizeException

interface Builder {
    fun build(input: BuilderInput): SyntaxElement
    fun getType(): SyntaxType

    fun validateArgSize(count: Int, input: BuilderInput) {
        validateArgSize(count, count, input)
    }

    fun validateArgSize(minCount: Int, maxCount: Int, input: BuilderInput) {
        val size = input.elements.size
        if (size > maxCount || size < minCount) {
            throw BuilderIncorrectArgsSizeException(minCount, maxCount, size)
        }
    }

}

class BuilderInput(
    val elements: List<SyntaxElement>,
)
