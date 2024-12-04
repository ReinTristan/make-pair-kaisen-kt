package com.github.reintristan.makepairkaisenkt.view

import com.github.reintristan.makepairkaisenkt.Main
import com.github.reintristan.makepairkaisenkt.model.BaseView
import com.github.reintristan.makepairkaisenkt.model.Constants
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.scene.text.Font
import javafx.scene.text.Text

class MenuView: BaseView {
    override lateinit var scene : Scene
    override lateinit var root : AnchorPane
    lateinit var title: Text
    lateinit var infiniteModeButton: Button
    lateinit var timeModeButton: Button
    lateinit var scoreButton: Button

    constructor() {
        initUI()
        initPane()
        initAnchors()
        initScene()
    }
    override fun initUI() {
        this.title = Text("Make the pair \n Kaisen").apply {
            styleClass.addAll("text-kaisen","text-kaisen-title")
            font = Font.font(150.0)
        }
        this.infiniteModeButton = Button(Constants.INFINITE_MODE).apply {
            styleClass.add("button-kaisen")
            prefWidth = 300.0
            prefHeight = 50.0
        }
        this.timeModeButton = Button(Constants.TIME_MODE).apply {
            styleClass.add("button-kaisen")
            prefWidth = 300.0
            prefHeight = 50.0
        }
        this.scoreButton = Button(Constants.SCORE).apply {
            styleClass.add("button-kaisen")
            prefWidth = 300.0
            prefHeight = 50.0

        }
    }

    override fun initPane() {
        this.root = AnchorPane()
        this.root.styleClass.add("bg-kaisen")
        this.root.children.add(title)
        this.root.children.add(infiniteModeButton)
        this.root.children.add(timeModeButton)
        this.root.children.add(scoreButton)

    }

    override fun initAnchors() {
        AnchorPane.setTopAnchor(title, 50.0)
        AnchorPane.setLeftAnchor(title, Constants.WIDTH / 2 - title.layoutBounds.width / 4)
        AnchorPane.setTopAnchor(infiniteModeButton, 400.0)
        AnchorPane.setLeftAnchor(infiniteModeButton, Constants.WIDTH / 2 - infiniteModeButton.prefWidth / 2)
        AnchorPane.setTopAnchor(timeModeButton, 500.0)
        AnchorPane.setLeftAnchor(timeModeButton, Constants.WIDTH / 2 - timeModeButton.prefWidth / 2)
        AnchorPane.setTopAnchor(scoreButton, 600.0)
        AnchorPane.setLeftAnchor(scoreButton, Constants.WIDTH / 2 - scoreButton.prefWidth / 2)
    }
    override fun initScene() {
        this.scene = Scene(root, Constants.WIDTH, Constants.HEIGHT).apply {
            try {
                stylesheets.add(Main::class.java.getResource("/main.css")!!.toExternalForm())
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

}
