package dev.paulshields.chronomancy.api

import io.ktor.application.Application

interface ApiRoutes {
    fun bind(ktorApplication: Application)
}
