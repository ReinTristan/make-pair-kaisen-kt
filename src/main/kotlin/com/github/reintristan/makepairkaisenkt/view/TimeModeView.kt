package com.github.reintristan.makepairkaisenkt.view

import com.github.reintristan.makepairkaisenkt.model.BaseView
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import com.github.reintristan.makepairkaisenkt.Main
import com.github.reintristan.makepairkaisenkt.model.Constants
import javafx.scene.text.Font
import javafx.scene.text.Text

class TimeModeView(): GameView() {
    lateinit var timeTitle: Text
    lateinit var timeText: Text
    init {
        initUIChild()
        initPaneChild()
        initAnchorsChild()
        initSceneChild()
    }
    fun initUIChild() {
        super.initUI()
        this.title = Text(Constants.TIME_MODE).apply {
            styleClass.addAll("text-kaisen","text-kaisen-title")
            font = Font.font(150.0)
        }
        this.timeTitle = Text("Time").apply {
            styleClass.addAll("text-kaisen","text-kaisen-title")
            font = Font.font(100.0)
        }
        this.timeText = Text("3:00").apply {
            styleClass.addAll("text-kaisen","text-kaisen-title")
            font = Font.font(100.0)
        }
    }
    fun initPaneChild() {
        super.initPane()
        this.root.children.add(this.title)
        this.root.children.add(this.timeTitle)
        this.root.children.add(this.timeText)


    }
    fun initAnchorsChild() {
        super.initAnchors()
        AnchorPane.setTopAnchor(this.title, 50.0)
        AnchorPane.setLeftAnchor(this.title, Constants.WIDTH / 2 - this.title.layoutBounds.width / 4)
        AnchorPane.setTopAnchor(this.timeTitle, 25.0)
        AnchorPane.setRightAnchor(this.timeTitle, 90.0)
        AnchorPane.setTopAnchor(this.timeText, 100.0)
        AnchorPane.setRightAnchor(this.timeText, 75.0)
    }
    fun initSceneChild() {
        super.initScene()
    }
}