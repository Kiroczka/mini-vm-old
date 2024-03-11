package com.experimental.components

import com.experimental.compilation.SyntaxElement
import com.experimental.context.Context
import com.experimental.context.TypedValue

interface Statement : SyntaxElement {
    fun execute(context: Context)
}


interface Expression : Statement {
    fun evaluate(context: Context): TypedValue

    override fun execute(context: Context) {
        evaluate(context)
    }
}

data object NothingStatement : Statement {
    override fun execute(context: Context) {
    }
}