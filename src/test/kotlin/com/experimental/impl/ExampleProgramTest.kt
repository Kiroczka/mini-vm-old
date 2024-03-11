package com.experimental.impl

import com.experimental.components.expressions.ArithmeticExpression
import com.experimental.components.expressions.FunCallArgs
import com.experimental.components.expressions.FunctionCallExpression
import com.experimental.components.expressions.LiteralExpression
import com.experimental.components.expressions.MinusOperator
import com.experimental.components.expressions.MultiplyOperator
import com.experimental.components.expressions.VariableExpression
import com.experimental.components.statements.VarInitialization
import com.experimental.context.Arguments
import com.experimental.context.FunName
import com.experimental.context.FunctionImpl
import com.experimental.context.IntValue
import com.experimental.context.TextValue
import com.experimental.context.Type
import com.experimental.context.VarDeclaration
import com.experimental.context.VarName
import com.experimental.context.Variable
import kotlin.test.Test

class ExampleProgramTest : MiniVMTest() {

    @Test
    fun testFunDeclarations() {
        val functions = listOf(
            FunctionImpl(
                name = FunName("heronasFormula"),
                arguments = Arguments(
                    listOf(
                        VarDeclaration(VarName("p"), Type.INT),
                        VarDeclaration(VarName("a"), Type.INT),
                        VarDeclaration(VarName("b"), Type.INT),
                        VarDeclaration(VarName("c"), Type.INT),
                    )
                ),
                statements = ProgramImpl(
                    listOf(
                        FunctionCallExpression(
                            FunName("println"),
                            FunCallArgs(
                                listOf(
                                    LiteralExpression(
                                        TextValue("Calculating...")
                                    )
                                )
                            )
                        ),
                        VarInitialization(
                            VarName("x"),
                            Type.INT,
                            ArithmeticExpression(
                                VariableExpression(VarName("p")),
                                VariableExpression(VarName("a")),
                                MinusOperator
                            )
                        ),

                        VarInitialization(
                            VarName("y"),
                            Type.INT,
                            ArithmeticExpression(
                                VariableExpression(VarName("p")),
                                VariableExpression(VarName("b")),
                                MinusOperator
                            )
                        ),

                        VarInitialization(
                            VarName("z"),
                            Type.INT,
                            ArithmeticExpression(
                                VariableExpression(VarName("p")),
                                VariableExpression(VarName("c")),
                                MinusOperator
                            )
                        ),
                    )
                ),
                returnExpression = ArithmeticExpression(
                    ArithmeticExpression(
                        ArithmeticExpression(
                            VariableExpression(VarName("p")),
                            VariableExpression(VarName("x")),
                            MultiplyOperator
                        ),
                        VariableExpression(VarName("y")),
                        MultiplyOperator
                    ),
                    VariableExpression(VarName("z")),
                    MultiplyOperator
                )
            ),
        )
        val variables = listOf(
            Variable(
                VarName("area"),
                IntValue(5040)
            ),
        )
        runTest("example-program.mvm", buildContext(variables = variables, functions = functions))
    }
}