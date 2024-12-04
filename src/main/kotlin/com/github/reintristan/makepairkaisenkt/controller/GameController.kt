package com.github.reintristan.makepairkaisenkt.controller


import com.github.reintristan.makepairkaisenkt.model.MenuOptions
import com.github.reintristan.makepairkaisenkt.view.EndgameView
import com.github.reintristan.makepairkaisenkt.view.InfiniteModeView
import com.github.reintristan.makepairkaisenkt.view.MenuView
import com.github.reintristan.makepairkaisenkt.view.TimeModeView
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.scene.Node
import javafx.stage.Stage
import java.util.Timer
import kotlin.concurrent.timerTask

// This class is used to store the game state
open class GameController(
    val viewInfinite: InfiniteModeView?,
    val viewTime: TimeModeView?,
    val initialMode: MenuOptions
) {
    var lives = 4
    var score = 0
    var time = 180
    var gameOver = false
    var board: Board
    var mode: MenuOptions

    init {
        mode = initialMode
        board = Board(this)
        setEvents()
        if (mode == MenuOptions.INFINTE) {
            board.delayedGameStart(viewInfinite?.cardsList!!)
        }
        if (mode == MenuOptions.TIME) {
            viewTime?.timeText?.text = Utils.formatTime(time)
            updateTime()
        }
    }

    fun setEvents() {
        val cardArr = board.generateBoard()
        if (mode == MenuOptions.INFINTE) {
            viewInfinite?.earlyEndButton?.setOnAction { e ->
                if ( score <= 0) {
                    backToMenu(e)
                }
                if(score > 0){
                    toEndGame(e,null)
                }
            }
            viewInfinite?.cardsList = cardArr
            viewInfinite?.renderBoard()
            viewInfinite?.cardsList?.forEachIndexed { index, gameCard ->
                viewInfinite.cardImagesViewList.get(index).setOnMouseClicked {
                    board.handleSelectCard(gameCard)
                }
            }
        }
        if (mode == MenuOptions.TIME) {
            viewTime?.earlyEndButton?.setOnAction { e ->
                if (score <= 0) {
                    backToMenu(e)

                }

                if(score > 0){
                    toEndGame(e, null)
                }
            }
            viewTime?.cardsList = cardArr
            viewTime?.renderBoard()
            viewTime?.cardsList?.forEachIndexed { index, gameCard ->
                viewTime.cardImagesViewList.get(index).setOnMouseClicked {
                    board.handleSelectCard(gameCard)
                }
            }
        }
    }

    fun updateTitle(newTitle: String) {
        if (mode == MenuOptions.INFINTE) {
            viewInfinite?.title?.text = newTitle
        }
        if (mode == MenuOptions.TIME) {
            viewTime?.title?.text = newTitle
        }

    }

    fun updateScore() {
        if (mode == MenuOptions.INFINTE) {
            viewInfinite?.scoreText?.text = "Score:\n $score"
        }
        if (mode == MenuOptions.TIME) {
            viewTime?.scoreText?.text = "Score:\n $score"
        }
    }

    fun updateLives() {
        if (mode == MenuOptions.INFINTE) {
            val livesText = if (lives < 0) 0 else lives
            viewInfinite?.livesText?.text = "X $livesText"
        }
    }
    fun updateTime() {
        if (mode == MenuOptions.TIME) {
            if(time <= 0) {
                gameOver = true
                verifyGameOver()
                return
            }
            Timer().schedule(timerTask {
                run {
                    Platform.runLater {
                        time--
                        if (time <= 0) {
                            gameOver = true
                            verifyGameOver()
                        }
                        viewTime?.timeText?.text = Utils.formatTime(time)
                        updateTime()
                    }
                }
            }, 1000)
        }
    }
    fun resetBoard() {
        board = Board(this)
        setEvents()
        if (mode == MenuOptions.INFINTE) {
            board.delayedGameStart(viewInfinite?.cardsList!!)
        }
    }

    /**
     * This function is used to verify if the game is over
     */
    fun verifyGameOver() {
        if (gameOver) {
            updateTitle("Game Over")
            Timer().schedule(timerTask {
                run {
                    Platform.runLater {
                        mode = MenuOptions.START
                        lives = 4
                        time = 180
                        score = 0
                        gameOver = false
                    }
                }
            }, 3000)
        }
    }

    fun toEndGame(e: ActionEvent?, currentStage: Stage?) {
        var stage = currentStage
        if (stage == null) {
            val node = e?.source as Node
            stage = node.scene.window as Stage
        }
        val endGame = EndgameView()
        EndGameController(endGame, score)
        stage.scene = endGame.scene

    }

    fun backToMenu(e: ActionEvent) {
        if (gameOver) return
        val node = e.source as Node
        val stage = node.scene.window as Stage
        val menuView = MenuView()
        MenuController(menuView)
        stage.scene = menuView.scene

    }

}