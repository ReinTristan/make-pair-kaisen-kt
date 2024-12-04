package com.github.reintristan.makepairkaisenkt

import com.github.reintristan.makepairkaisenkt.controller.MenuController
import com.github.reintristan.makepairkaisenkt.view.MenuView
import javafx.application.Application
import javafx.scene.image.Image
import javafx.stage.Stage

class Main : Application() {
    override fun start(stage: Stage) {
        stage.title = "Make the pair Kaisen"
        stage.isResizable = false
        val icon = Image(Main::class.java.getResourceAsStream("/images/lives.png"))
        stage.icons.add(icon)
        val menuView = MenuView()
        MenuController(menuView)
        stage.scene = menuView.scene
        stage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}