package br.com.getyourguide.review.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {

    val scheduler: Scheduler
}