package com.github.reintristan.makepairkaisenkt.controller

import com.github.reintristan.makepairkaisenkt.view.EndgameView
import com.github.reintristan.makepairkaisenkt.view.MenuView
import javafx.event.ActionEvent
import javafx.scene.Node
import javafx.stage.Stage

class EndGameController(view: EndgameView, val score: Int) {
    init {
        view.submit.setOnAction { e ->
            submitScore(view.nameInput.text)
            returnToMenu(e)
        }
        view.scoreText.text = "Your score is:\n $score"
    }
    fun submitScore(name: String) {
        ScoreDB.scores[name] = this.score
    }
    fun returnToMenu(e: ActionEvent) {
        val node = e.source as Node
        val stage = node.scene.window as Stage
        val memuView = MenuView()
        MenuController(memuView)
        stage.scene = memuView.scene
    }

}