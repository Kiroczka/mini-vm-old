package com.experimental.impl

import com.experimental.components.expressions.ArithmeticExpression
import com.experimental.components.expressions.FunCallArgs
import com.experimental.components.expressions.FunctionCallExpression
import com.experimental.components.expressions.MinusOperator
import com.experimental.components.expressions.MultiplyOperator
import com.experimental.components.expressions.PlusOperator
import com.experimental.components.expressions.VariableExpression
import com.experimental.components.statements.VarInitialization
import com.experimental.context.Arguments
import com.experimental.context.FunName
import com.experimental.context.FunctionImpl
import com.experimental.context.Type
import com.experimental.context.VarDeclaration
import com.experimental.context.VarName
import kotlin.test.Test

class FunctionsTest : MiniVMTest() {

    @Test
    fun testFunDeclarations() {
        val functions = listOf(
            FunctionImpl(
                name = FunName("sum"),
                arguments = Arguments(
                    listOf(
                        VarDeclaration(VarName("q"), Type.INT),
                        VarDeclaration(VarName("w"), Type.INT)
                    )
                ),
                statements = ProgramImpl(
                    listOf(
                        VarInitialization(
                            VarName("e"),
                            Type.INT,
                            ArithmeticExpression(
                                VariableExpression(VarName("q")),
                                VariableExpression(VarName("w")),
                                PlusOperator
                            )
                        )
                    )
                ),
                returnExpression = VariableExpression(VarName("e"))
            ),

            FunctionImpl(
                name = FunName("diff"),
                arguments = Arguments(
                    listOf(
                        VarDeclaration(VarName("r"), Type.INT),
                        VarDeclaration(VarName("t"), Type.INT)
                    )
                ),
                statements = ProgramImpl(),
                returnExpression = ArithmeticExpression(
                    VariableExpression(VarName("r")),
                    VariableExpression(VarName("t")),
                    MinusOperator
                ),
            ),

            FunctionImpl(
                name = FunName("product"),
                arguments = Arguments(
                    listOf(
                        VarDeclaration(VarName("y"), Type.INT),
                        VarDeclaration(VarName("u"), Type.INT),
                        VarDeclaration(VarName("i"), Type.TEXT)
                    )
                ),
                statements = ProgramImpl(
                    listOf(
                        VarInitialization(
                            VarName("haha"),
                            Type.INT,
                            ArithmeticExpression(
                                VariableExpression(VarName("y")),
                                VariableExpression(VarName("u")),
                                PlusOperator
                            )
                        ),
                        FunctionCallExpression(
                            FunName("println"),
                            FunCallArgs(
                                listOf(VariableExpression(VarName("i")))
                            )
                        )
                    )
                ),
                returnExpression = ArithmeticExpression(
                    VariableExpression(VarName("y")),
                    VariableExpression(VarName("u")),
                    MultiplyOperator
                ),
            ),

            FunctionImpl(
                name = FunName("squareDiff"),
                arguments = Arguments(
                    listOf(
                        VarDeclaration(VarName("a"), Type.INT),
                        VarDeclaration(VarName("b"), Type.INT)
                    )
                ),
                statements = ProgramImpl(),
                returnExpression = ArithmeticExpression(
                    ArithmeticExpression(
                        VariableExpression(VarName("a")),
                        VariableExpression(VarName("a")),
                        MultiplyOperator
                    ),
                    ArithmeticExpression(
                        VariableExpression(VarName("b")),
                        VariableExpression(VarName("b")),
                        MultiplyOperator
                    ),
                    MinusOperator
                ),
            ),
        )
        runTest("fun-dec/fun-dec.mvm", buildContext(functions = functions))
    }
}