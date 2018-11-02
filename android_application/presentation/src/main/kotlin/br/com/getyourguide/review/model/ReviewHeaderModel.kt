package br.com.getyourguide.review.model

import java.io.Serializable

//TODO HARDCODED
class ReviewHeaderModel(val title: String? = "Berlin Tempelhof Airport: The Legend of Tempelhof", val imageLink: String? = "https://cdn.getyourguide.com/img/tour_img-309970-145.jpg") : Serializable {
    var totalReviewCount: String = ""
}
