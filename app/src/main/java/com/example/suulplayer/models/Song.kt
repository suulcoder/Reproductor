package com.example.suulplayer.models

data class Song(var thisId:Long, var thisTitle:String, var thisArtist:String)    {

    fun getTitle(): String {
        return thisTitle
    }
}