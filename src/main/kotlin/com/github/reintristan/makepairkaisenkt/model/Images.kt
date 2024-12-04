package com.github.reintristan.makepairkaisenkt.model

import com.github.reintristan.makepairkaisenkt.Main
import javafx.scene.image.Image

class Images {
    companion object {
        val yujiImage = Image(Main::class.java.getResourceAsStream("/images/yuji.jpg"))
        val nobaraImage = Image(Main::class.java.getResourceAsStream("/images/nobara.jpg"))
        val megumiImage = Image(Main::class.java.getResourceAsStream("/images/megumi.jpg"))
        val gojoImage = Image(Main::class.java.getResourceAsStream("/images/gojo.jpg"))
        val nanamiImage = Image(Main::class.java.getResourceAsStream("/images/nanami.jpg"))
        val pandaImage = Image(Main::class.java.getResourceAsStream("/images/panda.jpg"))
        val togeImage = Image(Main::class.java.getResourceAsStream("/images/toge.jpg"))
        val makiImage = Image(Main::class.java.getResourceAsStream("/images/maki.jpg"))
        val suguruImage = Image(Main::class.java.getResourceAsStream("/images/suguru.jpg"))
        val livesImage = Image(Main::class.java.getResourceAsStream("/images/lives.png"))
        val logoImage = Image(Main::class.java.getResourceAsStream("/images/logo.jpg"))
        val imageList = arrayListOf<IiMage>(object: IiMage {
            override val name = "yuji"
            override val image= yujiImage
        }, object: IiMage {
            override val name = "nobara"
            override val image= nobaraImage
        }, object: IiMage {
            override val name = "megumi"
            override val image= megumiImage
        }, object: IiMage {
            override val name = "gojo"
            override val image= gojoImage
        }, object: IiMage {
            override val name = "nanami"
            override val image= nanamiImage
        }, object: IiMage {
            override val name = "panda"
            override val image= pandaImage
        }, object: IiMage {
            override val name = "toge"
            override val image= togeImage
        }, object: IiMage {
            override val name = "maki"
            override val image= makiImage
        }, object: IiMage {
            override val name = "suguru"
            override val image= suguruImage
        }
        )
    }
}

interface IiMage {
    val name: String
    val image: Image
}
