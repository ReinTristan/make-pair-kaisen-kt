package com.github.reintristan.makepairkaisenkt.controller

import com.github.reintristan.makepairkaisenkt.model.MenuOptions
import com.github.reintristan.makepairkaisenkt.view.InfiniteModeView
import com.github.reintristan.makepairkaisenkt.view.MenuView
import com.github.reintristan.makepairkaisenkt.view.ScoreView
import com.github.reintristan.makepairkaisenkt.view.TimeModeView
import javafx.scene.Node
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.event.ActionEvent

class MenuController(val view: MenuView) {

    init {
        println("entro al controller del menu")
        view.infiniteModeButton.setOnAction(this::initInfiniteMode)
        view.timeModeButton.setOnAction(this::initTimeMode)
        view.scoreButton.setOnAction(this::initScore)
        println(view.infiniteModeButton.onAction)
    }

    private fun initInfiniteMode(e: ActionEvent) {
        val node = e.source as Node
        val stage = node.scene.window as Stage
        val infiniteModeView = InfiniteModeView()
        GameController(infiniteModeView, null, MenuOptions.INFINTE)
        stage.scene = infiniteModeView.scene

    }

    private fun initTimeMode(e: ActionEvent) {
        val node = e.source as Node
        val stage = node.scene.window as Stage
        val timeModeView = TimeModeView()
        GameController(null, timeModeView, MenuOptions.TIME)
        stage.scene = timeModeView.scene

    }

    private fun initScore(e: ActionEvent) {
        val node = e.source as Node
        val stage = node.scene.window as Stage
        val scoreView = ScoreView()
        ScoreController(scoreView)
        stage.scene = scoreView.scene

    }
}