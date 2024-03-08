package com.experimental.model

import com.experimental.compilation.SyntaxElement
import com.experimental.context.Context

interface Program : SyntaxElement {
    fun execute(context: Context)

}

data object EmptyProgram : Program {
    override fun execute(context: Context) {
    }
}
