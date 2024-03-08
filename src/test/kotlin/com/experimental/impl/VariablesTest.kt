package com.experimental.impl

import com.experimental.context.IntValue
import com.experimental.context.TextValue
import com.experimental.context.VarName
import com.experimental.context.Variable
import kotlin.test.Test

class VariablesTest : MiniVMTest() {

    @Test
    fun testIntDeclarations() {
        val variables = listOf(
            Variable(
                VarName("a"),
                IntValue(5)
            ),
            Variable(
                VarName("b"),
                IntValue(10)
            ),
            Variable(
                VarName("c"),
                IntValue(10000000)
            ),
        )
        runTest("var-init/var-init-int.mvm", buildContext(variables = variables))
    }
    @Test
    fun testTextDeclarations() {
        val variables = listOf(
            Variable(
                VarName("a"),
                TextValue("Hello")
            ),
            Variable(
                VarName("b"),
                TextValue("Hyperexponential")
            ),
            Variable(
                VarName("c"),
                TextValue("Developer")
            ),
        )
        runTest("var-init/var-init-text.mvm", buildContext(variables = variables))
    }

    @Test
    fun testAllDeclarations() {
        val variables = listOf(
            Variable(
                VarName("a"),
                TextValue("Hello")
            ),
            Variable(
                VarName("bc"),
                IntValue(123)
            ),
            Variable(
                VarName("qwerty"),
                TextValue("World")
            ),
            Variable(
                VarName("d"),
                IntValue(1234)
            ),
        )
        runTest("var-init/var-init-all.mvm", buildContext(variables = variables))
    }
}