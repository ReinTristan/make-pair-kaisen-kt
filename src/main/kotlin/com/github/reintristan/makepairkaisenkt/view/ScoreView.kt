package com.github.reintristan.makepairkaisenkt.view

import com.github.reintristan.makepairkaisenkt.Main
import com.github.reintristan.makepairkaisenkt.model.BaseView
import com.github.reintristan.makepairkaisenkt.model.Constants
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.Text

class ScoreView : BaseView {
    override lateinit var scene: Scene
    override lateinit var root: AnchorPane
    lateinit var title: Text
    lateinit var backButton: Button
    lateinit var leftColumn: VBox
    lateinit var rightColumn: VBox
    lateinit var container: HBox
    lateinit var scrollPane: ScrollPane

    init {
        initUI()
        initPane()
        initAnchors()
        initScene()
    }

    override fun initUI() {
        title = Text("Score").apply {
            styleClass.addAll("text-kaisen", "text-kaisen-title")
            font = Font.font(150.0)
        }
        backButton = Button("Back").apply {
            styleClass.add("button-kaisen")
            prefWidth = 300.0
            prefHeight = 50.0
        }

    }

    override fun initPane() {
        root = AnchorPane()
        container = HBox(100.0).apply {
            styleClass.add("table-kaisen")

        }
        leftColumn = VBox(10.0).apply {
            styleClass.add("table-kaisen")

        }
        rightColumn = VBox(10.0).apply {
            styleClass.add("table-kaisen")

        }
        container.children.addAll(leftColumn, rightColumn)
        leftColumn.children.add(Label("Player "))
        rightColumn.children.add(Label("Score "))

        scrollPane = ScrollPane().apply {
            styleClass.add("table-kaisen")

            content = container
            prefWidth = 500.0
            prefHeight = 300.0
        }
        root.children.add(scrollPane)
        root.styleClass.add("bg-kaisen")
        root.children.add(title)
        root.children.add(backButton)


    }

    override fun initAnchors() {
        AnchorPane.setTopAnchor(title, 50.0)
        AnchorPane.setLeftAnchor(title, Constants.WIDTH / 2 - title.layoutBounds.width / 4)
        AnchorPane.setBottomAnchor(backButton, 50.0)
        AnchorPane.setLeftAnchor(backButton, Constants.WIDTH / 2 - backButton.prefWidth / 2)
        AnchorPane.setTopAnchor(scrollPane, 200.0)
        AnchorPane.setLeftAnchor(scrollPane, Constants.WIDTH / 2 - scrollPane.prefWidth / 2)
    }

    override fun initScene() {
        scene = Scene(root, Constants.WIDTH, Constants.HEIGHT).apply {
            try {
                stylesheets.add(Main::class.java.getResource("/main.css")!!.toExternalForm())
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}