sealed class State

object S0 : State()
object S1 : State()
object S2 : State()
object S3 : State()

//Якщо слово не може більше нікуди йти
object S_ERROR : State()

sealed class Input(private val letter: String) {
    override fun toString(): String {
        return letter
    }
}

object A : Input("a")
object B : Input("b")
object C : Input("c")

data class DFA(
    val states: Set<State>,
    val inputs: Set<Input>,
    val delta: (State, Input) -> State,
    val initialState: State,
    val isFinalState: (State) -> Boolean,
    val isErrorState: (State) -> Boolean
)

fun dfaFinalStates(): DFA {
    val dfa = DFA(
        states = setOf(S0, S1, S2, S3),
        inputs = setOf(A, B),
        delta = { state, input ->
            // Для алфавіту input описуємо переходи delta
            when (input) {
                A -> when (state) {
                    S0 -> S1
                    S1 -> S0

                    S2 -> S2
                    S3 -> S3

                    // Неописаний перехід йде в абстрактний стан помилок
                    else -> S_ERROR
                }

                B -> when (state) {
                    S0 -> S2
                    S2 -> S0

                    S1 -> S1
                    S3 -> S3

                    else -> S_ERROR
                }

                else -> S_ERROR
            }
        },
        initialState = S0,
        isFinalState = { it == S1 || it == S2 || it == S3 || it == S0 },
        isErrorState = { it == S_ERROR}
    )
    return dfa
}

fun dfa1() : DFA {
    val dfa = DFA(
        states = setOf(S0, S1, S2, S3),
        inputs = setOf(A, B),
        delta = { state, input ->
            // Для алфавіту input описуємо переходи delta
            when (input) {
                A -> when (state) {
                    S0 -> S1
                    S1 -> S3

                    S2 -> S2
                    else -> S_ERROR
                }

                B -> when (state) {
                    S0 -> S2
                    S2 -> S3

                    S1 -> S1
                    else -> S_ERROR
                }

                else -> S_ERROR
            }
        },
        initialState = S0,
        isFinalState = { it == S3 },
        isErrorState = { it == S_ERROR}
    )
    return dfa
}

fun dfa2() : DFA {
    val dfa = DFA(
        states = setOf(S0, S1, S2, S3),
        inputs = setOf(A, B),
        delta = { state, input ->
            // Для алфавіту input описуємо переходи delta
            when (input) {
                A -> when (state) {
                    S0 -> S1
                    S1 -> S3

                    S2 -> S2
                    S3 -> S3
                    else -> S_ERROR
                }

                B -> when (state) {
                    S0 -> S2
                    S2 -> S3

                    S1 -> S1
                    S3 -> S3
                    else -> S_ERROR
                }

                else -> S_ERROR
            }
        },
        initialState = S0,
        isFinalState = { it == S3 },
        isErrorState = { it == S_ERROR}
    )
    return dfa
}

fun dfa3() : DFA {
    val dfa = DFA(
        states = setOf(S0, S1, S2, S3),
        inputs = setOf(A, B),
        delta = { state, input ->
            // Для алфавіту input описуємо переходи delta
            when (input) {
                A -> when (state) {
                    S0 -> S1
                    S1 -> S3
                    S3 -> S1

                    S2 -> S2
                    else -> S_ERROR
                }

                B -> when (state) {
                    S0 -> S2
                    S2 -> S3

                    S1 -> S1
                    S3 -> S3
                    else -> S_ERROR
                }

                else -> S_ERROR
            }
        },
        initialState = S0,
        isFinalState = { it == S3 },
        isErrorState = { it == S_ERROR }
    )
    return dfa
}