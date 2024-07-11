package com.jetpack.sharedelement.data

import com.jetpack.sharedelement.model.Album
import com.jetpack.sharedelement.model.Coffee
import com.jetpack.sharedelement.R
import com.jetpack.sharedelement.model.Snack

object FakeDataProvider {
    fun getAlbums(): List<Album> =
        listOf(
            Album(
                id = 0,
                title = "It happened Quiet",
                author = "Aurora",
                year = 2018,
                cover = R.drawable.img_album_01
            ),
            Album(
                id = 1,
                title = "All My Daemons",
                author = "Aurora",
                year = 2016,
                cover = R.drawable.img_album_02
            ),
            Album(
                id = 2,
                title = "Running",
                author = "Aurora",
                year = 2015,
                cover = R.drawable.img_album_03
            ),
            Album(
                id = 3,
                title = "Paradise",
                author = "Aurora",
                year = 2015,
                cover = R.drawable.img_album_04
            ),
            Album(
                id = 4,
                title = "Heroz Falling",
                author = "Aurora",
                year = 2015,
                cover = R.drawable.img_album_05
            ),
            Album(
                id = 5,
                title = "Better World",
                author = "Aurora",
                year = 2015,
                cover = R.drawable.img_album_06
            ),
            Album(
                id = 6,
                title = "Master Memories",
                author = "Aurora",
                year = 2015,
                cover = R.drawable.img_album_07
            ),
            Album(
                id = 7,
                title = "Chamber",
                author = "Aurora",
                year = 2015,
                cover = R.drawable.img_album_08
            )
        )

    fun getCoffees(): List<Coffee> =
        listOf(
            Coffee(
                id = 0,
                name = "Espresso",
                description = "Espresso as a standalone coffee is served everywhere. It contains literally the basic essence. Coffee and water.Ideal serving: 30ml Espresso in a 90ml cup.",
                image = R.drawable.ic_espresso
            ),
            Coffee(
                id = 1,
                name = "Doppio",
                description = "Doppio in Italian literally means ‘double.’ It is a double shot of Espresso coffee.Ideal serving: 60ml Espresso in a 90ml cup.",
                image = R.drawable.ic_doppio
            ),
            Coffee(
                id = 2,
                name = "Macchiato",
                description = "Macchiato in Italian means ‘stained.’ This is because a serving of Macchiato is a normal Espresso shot with a little-foamed milk on the top.Ideal serving: 30ml Espresso + Foamed milk on top in a 90ml cup.",
                image = R.drawable.ic_espresso_macchiato
            ),
            Coffee(
                id = 3,
                name = "Cappuccino",
                description = "Everyone’s favourite and the most well-known and standard coffee drink, cappuccino contains more milk-to-coffee ratio.Ideal serving: 60ml Espresso + 60ml steamed milk + 60ml foamed milk (in that order) in a 200ml cup.",
                image = R.drawable.ic_cappuccino
            ),
            Coffee(
                id = 4,
                name = "Café au Lait",
                description = "Café au Lait literally means ‘coffee with milk.’ It is a French press coffee preparation with equal amounts coffee brew and scalded milk. Scalded milk is milk that is heated to 82° C to kill off bacteria and remove many proteins.\n" +
                        "Ideal serving: 90ml French press coffee + 90ml scalded milk in a 200ml cup.",
                image = R.drawable.ic_cafe_au_lait
            ),
            Coffee(
                id = 5,
                name = "Turkish",
                description = "The Turkish like their coffee light and sweet. Hence, a majority of this coffee is sugar water.\n" +
                        "Ideal serving: 10g (or 2 tsp.) ground coffee + 180ml sugar water + köpük (foam) (in that order) in a 200ml cup.\n" +
                        "\n",
                image = R.drawable.ic_turkish
            ),
            Coffee(
                id = 6,
                name = "Americano",
                description = "Those who say they like their coffee black talk about Café Americano. If you might have seen in Western media, Americans like to drink their coffee straight out of the pot.\n" +
                        "Ideal serving: 60ml Espresso + 120ml hot water in a 200ml cup.",
                image = R.drawable.ic_americano
            ),
            Coffee(
                id = 7,
                name = "Vienna Mocha",
                description = "Or simply known as Vienna Coffee, this coffee is a fun-loving twist to your regular Espresso shot. What’s the twist, you ask? WHIPPED CREAM!\n" +
                        "Ideal serving: 60ml Espresso + Whipped cream on the top in a 150ml cup.",
                image = R.drawable.ic_vienna_mocha
            ),
            Coffee(
                id = 8,
                name = "Latte",
                description = "Caffé Latte can be seen as the more mainstream brother of Café au Lait. The name literally means, yes, you guessed it right, ‘milk coffee.’\n" +
                        "Ideal serving: 60ml Espresso + 180-300ml steamed milk (depending on container)",
                image = R.drawable.ic_caffe_latte
            )
        )

    fun getSnacks(): List<Snack> =
        listOf(
            Snack(name = "Cupcake", image = R.drawable.cupcake),
            Snack(name = "Donut", image = R.drawable.donut),
            Snack(name = "Eclair", image = R.drawable.eclair),
            Snack(name = "Froyo", image = R.drawable.froyo),
            Snack(name = "Gingerbread", image = R.drawable.gingerbread),
            Snack(name = "Honeycomb", image = R.drawable.honeycomb)
        )
}