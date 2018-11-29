package com.example.georgeissac.mvp.util

/*
* Constructors of a sealed class are private by default.
All subclasses of a sealed class must be declared within the same file as private constructor.
Sealed classes are important in ensuring type safety by restricting the set of types at compile-time only.
*
*
*
* */

sealed class SealedTest {

    class ChildOne : SealedTest(){

        init {
            println("child")
        }
    }

    class ChildTwo : SealedTest(){

        init {
            println("child")
        }
        class ChildTwoTwo : SealedTest(){}
    }

    class ChildThree(var int: Int) : SealedTest(){

        init {
            println("child")
        }
    }

    object ObjectFour : SealedTest(){

        init {
            println("child")
        }
    }


}

