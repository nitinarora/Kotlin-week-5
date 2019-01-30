package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/

const val DONT_SKIP = 999

fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
    val mutableList: MutableList<T> = this.filterNotNull().toMutableList()
    val mutableListtoreturn: MutableList<T> = mutableListOf()
    var skipIndex = DONT_SKIP

    mutableList.forEachIndexed { id, t ->
        when {
            (skipIndex==DONT_SKIP || id != skipIndex) && mutableList.elementAtOrNull(id+1) == t -> {
                mutableListtoreturn.add(merge(t))
                skipIndex = id + 1
            }
            skipIndex == DONT_SKIP || id != skipIndex -> {
                mutableListtoreturn.add(t)
                skipIndex = DONT_SKIP
            }
        }
    }

    return mutableListtoreturn.toList()
}

