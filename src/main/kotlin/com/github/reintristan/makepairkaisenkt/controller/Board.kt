package com.github.reintristan.makepairkaisenkt.controller

import com.github.reintristan.makepairkaisenkt.model.*
import javafx.application.Platform
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask

/**
 * This class is used to have the board and class logic
 */
class Board(val game: GameController) {
    // just for infinite game mode
    var gameStarted = false
    var waitForSelect = false
    var completedCount = 0
    var selectedCards: SelectedCards = SelectedCards(null, null)
    var completedCards = mutableMapOf(
        "yuji" to false,
        "nobara" to false,
        "megumi" to false,
        "gojo" to false,
        "nanami" to false,
        "panda" to false,
        "toge" to false,
        "maki" to false,
        "suguru" to false
    )

    // This function generates the board for the game
    fun generateBoard(): ArrayList<GameCards> {
        val positions = arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 6, 7, 8)
        var cards = arrayListOf<GameCards>()
        positions.shuffle()
        positions.forEachIndexed { index, it: Int ->
            run {
                var isFlipped = game.mode == MenuOptions.INFINTE
                cards.add(GameCards(Images.imageList[it].image, isFlipped, Images.imageList[it].name, index, it))
            }
        }
        return cards
    }

    /**
     * This function is used to wait a momment before starting the game in infinite mode
     */
    fun delayedGameStart(board: ArrayList<GameCards>) {
        println("game start $gameStarted")
        Timer().schedule(timerTask {
            run {
                Platform.runLater {
                    println("time to start the game end")

                    gameStarted = true
                    flipCards(board)
                    println("game start $gameStarted")

                }
            }
        }, 2000)

    }

    /**
     * This function is used to flip the cards in infinite mode after the game starts
     */
    fun flipCards(board: ArrayList<GameCards>) {
        if (gameStarted) {
            board.forEach { it: GameCards ->
                run {

                    it.isFlipped = false
                    if (game.mode == MenuOptions.INFINTE) {
                        game.viewInfinite?.cardImagesViewList?.get(it.position)?.image = Images.logoImage
                    }
                }
            }

        }
    }

    /**
     * This function is used to handle the selection of a card
     */
    fun handleSelectCard(card: GameCards) {
        if (game.mode == MenuOptions.INFINTE && !gameStarted) return
        if (game.gameOver) return
        if (waitForSelect) return
        if (completedCards.get(card.name) == true) return

        if (selectedCards.card1 == null) {
            selectedCards.card1 = card
        } else if(selectedCards.card2 == null) {
            if (selectedCards.card1?.position == card.position) return
            selectedCards.card2 = card
            verifyPair()
        }
        card.isFlipped = !card.isFlipped

        if (game.mode == MenuOptions.INFINTE) {
           val imageView = game.viewInfinite?.cardImagesViewList?.get(card.position)
            if (card.isFlipped) {
                imageView?.image = card.sprite
            } else {
                game.viewInfinite?.cardImagesViewList?.get(card.position)?.image = Images.logoImage
            }
        }
        if (game.mode == MenuOptions.TIME) {
            val imageView = game.viewTime?.cardImagesViewList?.get(card.position)
            if (card.isFlipped) {
                imageView?.image = card.sprite
            } else {

                imageView?.image = Images.logoImage
            }
        }

    }
    /**
     * This function is used to verify if the selected cards are a pair
     */
    fun verifyPair() {
        if (selectedCards.card1 == null || selectedCards.card2 == null) return
        if (selectedCards.isPair() == true) {
            completedCards[selectedCards.card1!!.name] = true
            completedCount += 1
            game.score += 100
            game.updateScore()
            if (game.mode == MenuOptions.INFINTE) {
                if (completedCount > 8) {
                    game.updateTitle("Board Completed")
                    waitForSelect = true
                    Timer().schedule(timerTask {
                        run {
                            Platform.runLater {
                                completedCount = 0
                                completedCards.clear()
                                waitForSelect = false
                                gameStarted = false
                                game.resetBoard()
                                game.updateTitle(Constants.INFINITE_MODE)
                            }
                        }
                    }, 2000)
                }
            }
            selectedCards.card1 = null
            selectedCards.card2 = null
        } else {

            waitForSelect = true
            if(game.mode == MenuOptions.INFINTE) {
                game.lives--
                game.updateLives()
                if (game.lives < 0) {
                    game.gameOver = true
                    game.verifyGameOver()
                    return
                }
            }
            Timer().schedule(timerTask {
                run {
                    Platform.runLater {
                        if(game.mode == MenuOptions.INFINTE) {
                            
                            game.viewInfinite?.cardsList?.get(selectedCards.card1!!.position)?.isFlipped = false
                            game.viewInfinite?.cardsList?.get(selectedCards.card2!!.position)?.isFlipped = false
                            game.viewInfinite?.cardImagesViewList?.get(selectedCards.card1!!.position)?.image = Images.logoImage
                            game.viewInfinite?.cardImagesViewList?.get(selectedCards.card2!!.position)?.image = Images.logoImage
                        }
                        if(game.mode == MenuOptions.TIME) {
                            game.viewTime?.cardsList?.get(selectedCards.card1!!.position)?.isFlipped = false
                            game.viewTime?.cardsList?.get(selectedCards.card2!!.position)?.isFlipped = false
                            game.viewTime?.cardImagesViewList?.get(selectedCards.card1!!.position)?.image =
                                Images.logoImage
                            game.viewTime?.cardImagesViewList?.get(selectedCards.card2!!.position)?.image =
                                Images.logoImage
                        }
                        waitForSelect = false
                        selectedCards.card1 = null
                        selectedCards.card2 = null
                    }
                }
            }, 2000)
        }

    }

}

