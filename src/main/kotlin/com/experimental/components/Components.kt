package com.experimental.components

import com.experimental.context.Context
import com.experimental.context.TypedValue

sealed interface Component

interface Expression : Component {
    fun evaluate(context: Context): TypedValue

}

interface Statement : Component {
    fun execute(context: Context)

}