package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game
import kotlin.random.Random
import kotlin.random.nextInt

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
        object : Game {
            private var gb: GameBoard<Int?> = createGameBoard(4)
            lateinit var currentPermutation: List<Int>

            override fun initialize() {
                currentPermutation = initializer.initialPermutation
                gb.getAllCells().forEachIndexed{ id, cell ->
                    when {
                        id < 15 -> gb[cell] = currentPermutation[id]
                        else -> gb[cell] = null
                    }
                }
            }

            override fun canMove(): Boolean = isEven(currentPermutation)

            override fun hasWon(): Boolean {
                val allCells = gb.getAllCells()

                allCells.forEachIndexed { index, cell ->
                    if (index <15 && gb[cell] != index+1) {
                        return false
                    }
                }
                return true
            }

            override fun processMove(direction: Direction) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun get(i: Int, j: Int): Int? {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }

