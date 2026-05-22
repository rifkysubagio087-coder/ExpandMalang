package com.example.expandmalang.data.model

data class Destination(
    val id: String,
    val name: String,
    val location: String,
    val rating: Double,
    val reviewsCount: String,
    val price: String,
    val imageUrl: String,
    val description: String = "",
    val category: String = ""
)

val sampleDestinations = listOf(
    Destination(
        id = "1",
        name = "Museum Angkut",
        location = "Batu, Malang Region",
        rating = 4.8,
        reviewsCount = "1.2k",
        price = "Rp 120.000",
        imageUrl = "https://labirutour.com/wp-content/uploads/2017/10/Review-Museum-Angkut-Malang-Tiket.jpg",
        description = "Museum Angkut merupakan museum transportasi dan tempat wisata modern yang terletak di Kota Batu, Jawa Timur. Museum ini memiliki koleksi lebih dari 300 jenis angkutan tradisional hingga modern yang tertata apik dalam berbagai zona tematik dunia."
    ),
    Destination(
        id = "2",
        name = "Jatim Park",
        location = "Batu, Malang Region",
        rating = 4.7,
        reviewsCount = "2.5k",
        price = "Rp 115.000",
        imageUrl = "https://ik.imagekit.io/tvlk/blog/2025/05/jatim-park-mob.png",
        description = "Jawa Timur Park atau Jatim Park adalah ikon wisata keluarga di Jawa Timur. Menawarkan perpaduan konsep edukasi dan hiburan, tempat ini memiliki puluhan wahana seru, museum satwa, hingga taman belajar yang interaktif bagi semua usia."
    ),
    Destination(
        id = "3",
        name = "Mount Bromo",
        location = "Probolinggo, Malang Region",
        rating = 4.9,
        reviewsCount = "5k",
        price = "Rp 54.000",
        imageUrl = "https://images.pexels.com/photos/13714573/pexels-photo-13714573.jpeg",
        description = "Gunung Bromo adalah salah satu gunung berapi paling ikonik di Indonesia. Terkenal dengan lautan pasirnya yang luas dan pemandangan matahari terbit yang magis dari Puncak Penanjakan, Bromo menawarkan petualangan alam yang tak terlupakan."
    )
)
