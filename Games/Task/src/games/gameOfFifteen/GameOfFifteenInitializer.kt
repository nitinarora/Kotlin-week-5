package games.gameOfFifteen

import java.util.*

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    override val initialPermutation by lazy {
        var shuffled: List<Int>
        do {
            shuffled = listOf(1..15).flatten().shuffled()
            isEven(shuffled)
        } while (!isEven(shuffled))
        shuffled
    }

}

fun main() {
    val randomGameInitializer = RandomGameInitializer()
    println(randomGameInitializer.initialPermutation)
    println(randomGameInitializer.initialPermutation)
    println(randomGameInitializer.initialPermutation)
    assert(isEven(randomGameInitializer.initialPermutation))
}


