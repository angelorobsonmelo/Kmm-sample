package com.angelorobson.opsmoonkmm.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val id: Int,
    val title: String,
    val body: String
)