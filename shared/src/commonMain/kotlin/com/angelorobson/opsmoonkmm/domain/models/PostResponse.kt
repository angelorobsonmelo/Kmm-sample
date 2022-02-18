package com.angelorobson.opsmoonkmm.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val id: Int,
    val title: String,
    val body: String
)