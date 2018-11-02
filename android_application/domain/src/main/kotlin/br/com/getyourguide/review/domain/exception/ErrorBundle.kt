package br.com.getyourguide.review.domain.exception

interface ErrorBundle {

    val exception: Exception?

    val errorMessage: String?
}