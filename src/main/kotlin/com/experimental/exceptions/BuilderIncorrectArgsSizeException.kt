package com.experimental.exceptions

class BuilderIncorrectArgsSizeException(expectedMin: Int, expectedMax: Int, actual: Int) :
    CompilationException(
        "Builder expects to args size in between: $expectedMin-$expectedMax  but got: $actual." +
                "Check corresponding compiler implementation"
    )