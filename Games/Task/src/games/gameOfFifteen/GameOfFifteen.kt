package games.gameOfFifteen

import board.Cell
import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

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
                gb.getAllCells().forEachIndexed { id, cell ->
                    when {
                        id < 15 -> gb[cell] = currentPermutation[id]
                        else -> gb[cell] = null
                    }
                }
            }

            override fun canMove(): Boolean = isEven(currentPermutation)

            override fun hasWon(): Boolean {
                gb.getAllCells().forEachIndexed { i, cell ->
                    if (i < 15 && gb[cell] != i + 1) {
                        return false
                    }
                }
                return true
            }

            override fun processMove(direction: Direction) {
                //find out the null cell
                var nullCell: Cell = gb.getAllCells().first { gb[it] == null }
                //depending on the direction swap the value with the neighbour cell
                when (direction) {
                    Direction.UP -> {
                        val neighbourCell = gb.getCellOrNull(nullCell.i + 1, nullCell.j)
                        if (neighbourCell != null) {
                            swapWithNeighbourCell(nullCell, neighbourCell)
                        }
                    }
                    Direction.DOWN -> {
                        val neighbourCell = gb.getCellOrNull(nullCell.i - 1, nullCell.j)
                        if (neighbourCell != null) {
                            swapWithNeighbourCell(nullCell, neighbourCell)
                        }
                    }
                    Direction.LEFT -> {
                        val neighbourCell = gb.getCellOrNull(nullCell.i, nullCell.j + 1)
                        if (neighbourCell != null) {
                            swapWithNeighbourCell(nullCell, neighbourCell)
                        }
                    }
                    Direction.RIGHT -> {
                        val neighbourCell = gb.getCellOrNull(nullCell.i, nullCell.j - 1)
                        if (neighbourCell != null) {
                            swapWithNeighbourCell(nullCell, neighbourCell)
                        }
                    }
                }
            }

            private fun swapWithNeighbourCell(nullCell: Cell, neighbourCell: Cell) {
                gb[nullCell] = gb[neighbourCell]
                gb[neighbourCell] = null
            }

            override fun get(i: Int, j: Int): Int? {
                val cell = gb.getCell(i, j)
                return gb[cell]
            }
        }

