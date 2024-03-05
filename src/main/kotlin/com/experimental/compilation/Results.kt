package com.experimental.compilation

import com.experimental.components.Component
import com.experimental.components.Expression
import com.experimental.components.Statement
import com.experimental.components.expressions.FunctionCallExpression

sealed interface Result<T : Component>
sealed class SuccessResult<T : Component>(val value: T, val lastIndex: Int) : Result<T>
class StatementSuccessResult(value: Statement, lastIndex: Int) : SuccessResult<Statement>(value, lastIndex)
class ExpressionSuccessResult(value: Expression, lastIndex: Int) : SuccessResult<Expression>(value, lastIndex)
sealed class FailedResult<T : Component> : Result<T>
data object StatementFailedResult : FailedResult<Statement>()
data object ExpressionFailedResult : FailedResult<Expression>()