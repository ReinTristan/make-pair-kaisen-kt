package com.github.reintristan.makepairkaisenkt.view

import com.github.reintristan.makepairkaisenkt.Main
import com.github.reintristan.makepairkaisenkt.model.BaseView
import com.github.reintristan.makepairkaisenkt.model.Constants
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.scene.text.Font
import javafx.scene.text.Text

class EndgameView: BaseView  {
    override lateinit var scene : Scene
    override lateinit var root : AnchorPane
    lateinit var title: Text
    lateinit var submit: Button
    lateinit var scoreText: Text
    lateinit var nameInput: TextField

    init {
        initUI()
        initPane()
        initAnchors()
        initScene()
    }
    override fun initUI() {

        this.submit = Button("Submit").apply {
            styleClass.add("button-kaisen")
            prefWidth = 300.0
            prefHeight = 50.0
        }
        this.title = Text("Game Over").apply {
            styleClass.addAll("text-kaisen","text-kaisen-title")
            font = Font.font(150.0)
        }
        this.scoreText = Text("Score:\n 0").apply {
            styleClass.addAll("text-kaisen","text-kaisen-title")
            font = Font.font(100.0)
        }
        this.nameInput = TextField().apply {
            styleClass.add("input-kaisen")
            promptText = "Enter your name"
            styleClass.add("text-kaisen")
            prefWidth = 300.0
            prefHeight = 50.0
        }
    }

    override fun initPane() {
        this.root = AnchorPane()
        this.root.styleClass.add("bg-kaisen")
        this.root.children.add(submit)
        this.root.children.add(scoreText)
        this.root.children.add(title)
        this.root.children.add(nameInput)
    }

    override fun initAnchors() {
        AnchorPane.setBottomAnchor(submit, 25.0)
        AnchorPane.setLeftAnchor(submit, Constants.WIDTH / 2 - submit.prefWidth / 2)
        AnchorPane.setTopAnchor(title, 50.0)
        AnchorPane.setLeftAnchor(title, Constants.WIDTH / 2 - title.layoutBounds.width / 4)
        AnchorPane.setTopAnchor(scoreText, 200.0)
        AnchorPane.setLeftAnchor(scoreText,Constants.WIDTH / 2 - scoreText.layoutBounds.width / 2 )
        AnchorPane.setTopAnchor(nameInput, 400.0)
        AnchorPane.setLeftAnchor(nameInput, Constants.WIDTH / 2 - nameInput.prefWidth / 2)
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