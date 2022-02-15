package com.angelorobson.opsmoonkmm.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val id: Int,
    val title: String,
    val body: String
)