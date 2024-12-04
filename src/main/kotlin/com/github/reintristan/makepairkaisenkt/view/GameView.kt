package com.github.reintristan.makepairkaisenkt.view

import com.github.reintristan.makepairkaisenkt.Main
import com.github.reintristan.makepairkaisenkt.controller.Utils
import com.github.reintristan.makepairkaisenkt.model.BaseView
import com.github.reintristan.makepairkaisenkt.model.Constants
import com.github.reintristan.makepairkaisenkt.model.GameCards
import com.github.reintristan.makepairkaisenkt.model.Images
import javafx.scene.Scene
import javafx.scene.SnapshotParameters
import javafx.scene.control.Button
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.Text

open class GameView: BaseView {
    override lateinit var scene : Scene
    override lateinit var root : AnchorPane
    lateinit var cardsList: ArrayList<GameCards>
    var cardImagesViewList: ArrayList<ImageView> = ArrayList()
    lateinit var title: Text
    lateinit var scoreText: Text
    lateinit var earlyEndButton: Button
    override fun initUI() {

        this.earlyEndButton = Button("Exit").apply {
            styleClass.add("button-kaisen")
            prefWidth = 300.0
            prefHeight = 50.0
        }
        this.scoreText = Text("Score:\n 0").apply {
            styleClass.addAll("text-kaisen","text-kaisen-title")
            font = Font.font(100.0)
        }

    }

    override fun initPane() {
        this.root = AnchorPane()
        this.root.styleClass.add("bg-kaisen")
        this.root.children.add(earlyEndButton)
        this.root.children.add(scoreText)
    }

    override fun initAnchors() {
        AnchorPane.setBottomAnchor(earlyEndButton, 25.0)
        AnchorPane.setLeftAnchor(earlyEndButton, Constants.WIDTH / 2 - earlyEndButton.prefWidth / 2)
        AnchorPane.setTopAnchor(scoreText, 25.0)
        AnchorPane.setLeftAnchor(scoreText, 75.0)
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
    fun renderBoard() {
        if(cardImagesViewList.isNotEmpty()) {
            cardImagesViewList.clear()
        }
        var initialX = 200.0
        var initialY = 200.0

        for (i in 0..2) {
            for (j in 0..5) {
                val correctIndex = i * 6 + j
                val card = cardsList[correctIndex]
                var sprite = card.sprite
                if(!card.isFlipped) {
                    sprite = Images.logoImage
                }
                val cardImgView = Utils.decorateCardImage(sprite)
                cardImagesViewList.add(cardImgView)
                this.root.children.add(cardImgView)
                AnchorPane.setTopAnchor(cardImgView, initialY)
                AnchorPane.setLeftAnchor(cardImgView, initialX)
                initialX += 150.0
            }
            initialX = 200.0
            initialY += 150.0
        }
    }
}