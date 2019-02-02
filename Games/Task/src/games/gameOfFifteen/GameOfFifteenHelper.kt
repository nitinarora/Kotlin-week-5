package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    var count = 0
    val mutableList = permutation.toMutableList()
    for(i in 0..permutation.size-2) {
        for(j in 1..permutation.size-1) {
            when {
                i < j && mutableList[i] > mutableList[j] -> {
                    count+=1
                    val placeholder = mutableList[j]
                    mutableList[j] = mutableList[i]
                    mutableList[i] = placeholder
                }
            }
        }
    }
    return count % 2 == 0
}