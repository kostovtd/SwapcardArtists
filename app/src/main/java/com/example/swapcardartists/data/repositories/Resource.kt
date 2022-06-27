package com.example.swapcardartists.data.repositories

import androidx.annotation.StringRes
import com.example.swapcardartists.R

data class Resource<out T>(val status: ResourceStatus, val data: T?, val messageType: MessageType = MessageType.NONE)

enum class ResourceStatus {
    SUCCESS,
    ERROR
}

enum class MessageType(@StringRes val resourceId: Int) {
    NONE(R.string.not_available)
}