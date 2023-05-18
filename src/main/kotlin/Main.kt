import java.lang.Error

/*
 * 16.	(10 балів). Для заданого натурального k виявити,
 * чи допускає скінчений автомат всі можливі слова
 * (виходячи з автоматного алфавіту) довжини k.
 */

val isRecursive = false
val doBreak = false

fun deltaHatIterative(dfa: DFA, input: List<Input>): State {
    var state = dfa.initialState
    for (character in input) {
        state = dfa.delta(state, character)
    }
    return state
}

fun deltaHat(dfa: DFA, input: List<Input>) : State = input.fold(dfa.initialState, dfa.delta)

fun main(args: Array<String>) {
    val dfa = dfa2()
    val alphabet = listOf(A, B)
    val wordLength = 5
    println(isAcceptingAllWords(dfa, alphabet, wordLength))
}

fun isAcceptingAllWords(dfa: DFA, alphabet: List<Input>, kLength: Int) : Boolean {
    var acceptsEveryWord = true

    val combinations = combinationWithRepetition(kLength, alphabet.size, alphabet.toList()) as List<List<Input>>

    for (word in combinations) {
        print("$word: ")
        var state: State
        var res = false

        if(!isRecursive)
            state = deltaHatIterative(dfa, word)
        else
            state = deltaHat(dfa, word)

        if (dfa.isErrorState(state)) {
            throw IllegalStateException("The word goes into unknown state")
        }

        res = dfa.isFinalState(state)

        println(res)
        if (!res) {
            acceptsEveryWord = false
            if (doBreak) break
        }

    }
    if (!acceptsEveryWord)
        println("DFA DOES NOT ACCEPT EVERY WORD!")
    return acceptsEveryWord
}

//Warning: Алгоритм зважає на порядок!
fun combinationWithRepetition(kLength: Int, inputSize: Int, items: List<*>) : List<List<*>> {
    fun generate(k: Int, combination: IntArray, res: MutableList<List<*>>): List<List<*>> {
        if (k >= kLength) {
            val word = emptyList<Any?>().toMutableList()
            for (i in 0 until kLength) word.add(items[combination[i]]);
            res.add(word.toList())
        }
        else {
            for (j in 0 until inputSize)
                if (k == 0 || j >= combination[k - 1]) {
                    combination[k] = j
                    generate(k + 1, combination, res)
                }
        }
        return res
    }

    val combination = IntArray(kLength)
    val res = emptyList<List<*>>().toMutableList()
    generate(0, combination, res)
    //println(res)
    return res
}
