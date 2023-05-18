import kotlin.test.assertEquals
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Test {

    @Test
    fun dfa1GoesNowhere() {
        val dfa = dfa1()
        val input = listOf(A, A, A)
        assertTrue {
            dfa.isErrorState(deltaHat(dfa, input))
        }
    }

    @Test
    fun dfa1GoesFinal() {
        val dfa = dfa1()
        val input = listOf(A, B, B, A)
        assertTrue {
            dfa.isFinalState(deltaHat(dfa, input))
        }
    }

    @Test
    fun dfa2GoesFinal() {
        val dfa = dfa2()
        val input = listOf(B, A, A, B, B, A, B, B)
        assertTrue {
            dfa.isFinalState(deltaHat(dfa, input))
        }
    }

    @Test
    fun dfa3GoesFinal() {
        val dfa = dfa3()
        val input = listOf(B, A, B, B, A, A, A, A)
        assertTrue {
            dfa.isFinalState(deltaHat(dfa, input))
        }
    }

    @Test
    fun dfa3EmptyWord() {
        val dfa = dfa3()
        val input = listOf<Input>()
        assertEquals(S0, deltaHat(dfa, input))
    }

    @Test()
    fun dfa3UnknownLetter() {
        val dfa = dfa3()
        val input = listOf<Input>(C)
        assertTrue {
            dfa.isErrorState(deltaHat(dfa, input))
        }
    }

    @Test()
    fun dfa2IterateAllKCombinations() {
        val dfa = dfa2()
        val input = listOf<Input>(A, B)
        val k = 3
        assertFalse {
            isAcceptingAllWords(dfa, input, k)
        }
    }

}