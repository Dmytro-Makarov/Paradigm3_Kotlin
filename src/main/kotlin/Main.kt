
/*
 * 16.	(10 балів). Для заданого натурального k виявити,
 * чи допускає скінчений автомат всі можливі слова
 * (виходячи з автоматного алфавіту) довжини k.
 */

fun deltaHatIterative(dfa: DFA, input: List<Input>): State {
    var state = dfa.initialState
    for (character in input) {
        state = dfa.delta(state, character)
    }
    return state
}

fun deltaHat(dfa: DFA, input: List<Input>) : State = input.fold(dfa.initialState, dfa.delta)

fun main() {
    val dfa = dfa3()
    val alphabet = listOf(A, B)
    val wordLength = 5
    println(isAcceptingAllWords(dfa, alphabet, wordLength))
}

fun isAcceptingAllWords(dfa: DFA, alphabet: List<Input>, kLength: Int) : Boolean {

    var acceptsEveryWord = iterateCombinations(dfa,
        combinationWithRepetition(kLength, alphabet.size, alphabet.toList()))

    if (!acceptsEveryWord)
        println("DFA DOES NOT ACCEPT EVERY WORD!")
    else
        //Перевіримо обернений хід
        acceptsEveryWord = iterateCombinations(dfa,
            combinationWithRepetition(kLength, alphabet.size, alphabet.toList().reversed()))


    return acceptsEveryWord
}

fun iterateCombinations(dfa: DFA, combinations : List<List<Input>>,
                        isRecursive : Boolean = false, doBreak: Boolean = false) : Boolean {
    var check = true
    for (word in combinations) {
        print("$word: ")

        val state : State = if(!isRecursive)
            deltaHatIterative(dfa, word)
        else
            deltaHat(dfa, word)

        if (dfa.isErrorState(state)) {
            throw IllegalStateException("The word goes into unknown state")
        }

        val res : Boolean = dfa.isFinalState(state)

        println(res)
        if (!res) {
            check = false
            if (doBreak) break
        }
    }
    return check
}

//Warning: Алгоритм зважає на порядок!
fun combinationWithRepetition(kLength: Int, inputSize: Int, items: List<Input>) : List<List<Input>> {
    fun generate(k: Int, combination: IntArray, res: MutableList<List<Input>>): List<List<Input>> {
        if (k >= kLength) {
            val word = emptyList<Input>().toMutableList()
            for (i in 0 until kLength) word.add(items[combination[i]])
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
    val res = emptyList<List<Input>>().toMutableList()
    generate(0, combination, res)
    //println(res)
    return res
}
