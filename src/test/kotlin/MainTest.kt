import org.junit.jupiter.api.assertThrows
import kotlin.test.*

class MainTest {

    @Test
    fun delta_IterativeEqualsRecursive_True(){
        val dfa = dfa3()
        val alphabet = listOf(A, B, A)
        assert(deltaHat(dfa, alphabet) == deltaHatIterative(dfa, alphabet))
    }

    @Test
    fun dfaFinalStates_EveryStateIsFinal_DoesReverseCombination(){
        val dfa = dfaFinalStates()
        val input = listOf(A, B)
        val kLength = 5
        assert(isAcceptingAllWords(dfa, input, kLength))
    }

    @Test
    fun dfa1_OutOfBound_isErrorState() {
        val dfa = dfa1()
        val input = listOf(A, A, A)
        assert(dfa.isErrorState(deltaHat(dfa, input)))
    }

    @Test
    fun iterateCombinations_ErrorState_ThrowsException(){
        val dfa = dfa1()
        val input = listOf(listOf(A, A, A))
        assertThrows<IllegalStateException> {
            iterateCombinations(dfa, input, isRecursive = true, doBreak = false)
        }
    }

    @Test
    fun dfa1_isFinal() {
        val dfa = dfa1()
        val input = listOf(A, B, B, A)
        assert(dfa.isFinalState(deltaHat(dfa, input)))
    }

    @Test
    fun dfa2_isFinal() {
        val dfa = dfa2()
        val input = listOf(B, A, A, B, B, A, B, B)
        assert(dfa.isFinalState(deltaHat(dfa, input)))
    }

    @Test
    fun dfa3_isFinal() {
        val dfa = dfa3()
        val input = listOf(B, A, B, B, A, A, A, A)
        assert(dfa.isFinalState(deltaHat(dfa, input)))
    }

    @Test
    fun dfa1_EmptyWord_InitialState() {
        val dfa = dfa1()
        val input = listOf<Input>()
        assertSame(dfa.initialState, deltaHat(dfa, input))
    }

    @Test
    fun dfa2_EmptyWord_InitialState() {
        val dfa = dfa2()
        val input = listOf<Input>()
        assertSame(dfa.initialState, deltaHat(dfa, input))
    }

    @Test
    fun dfa3_EmptyWord_InitialState() {
        val dfa = dfa3()
        val input = listOf<Input>()
        assertSame(dfa.initialState, deltaHat(dfa, input))
    }

    @Test()
    fun dfa1_UnknownLetter_ErrorState() {
        val dfa = dfa1()
        val input = listOf(C)
        assert(dfa.isErrorState(deltaHat(dfa, input)))
    }

    @Test()
    fun dfa2_UnknownLetter_ErrorState() {
        val dfa = dfa2()
        val input = listOf(C)
        assert(dfa.isErrorState(deltaHat(dfa, input)))
    }

    @Test()
    fun dfa3_UnknownLetter_ErrorState() {
        val dfa = dfa3()
        val input = listOf(C)
        assert(dfa.isErrorState(deltaHat(dfa, input)))
    }

}