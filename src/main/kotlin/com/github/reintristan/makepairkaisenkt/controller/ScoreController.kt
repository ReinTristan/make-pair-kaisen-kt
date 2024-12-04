package com.github.reintristan.makepairkaisenkt.controller

import com.github.reintristan.makepairkaisenkt.view.MenuView
import com.github.reintristan.makepairkaisenkt.view.ScoreView
import javafx.event.ActionEvent
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.stage.Stage


class ScoreController(val view: ScoreView) {

    init {
        view.backButton.setOnAction(this::initMenu)
        ScoreDB.scores.forEach { t, u ->
            view.leftColumn.children.add(Label(t))
            view.rightColumn.children.add(Label(u.toString()))
        }
    }
    fun initMenu(e: ActionEvent) {
        val node = e.source as Node
        val stage = node.scene.window as Stage
        val menuView = MenuView()
        MenuController(menuView)
        stage.scene = menuView.scene
    }
}