/*Informacion tomada de:
https://code.tutsplus.com/tutorials/create-a-music-player-on-android-project-setup--mobile-22764
 */
//Este sera un adaptador para mostra cada cancion.
package com.example.suulplayer.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import java.util.*
import android.support.design.widget.CoordinatorLayout.Behavior.setTag
import android.R
import android.content.Context
import android.widget.TextView
import android.widget.LinearLayout
import com.example.suulplayer.models.Song


class SongAdapter : BaseAdapter() {

    private var songs: ArrayList<Song>? = null
    private var songInf: LayoutInflater? = null


    fun SongAdapter(c: Context, theSongs: ArrayList<Song>): ??? {
        songs = theSongs
        songInf = LayoutInflater.from(c)
    }

    override fun getCount(): Int {
        return songs.size();
    }

    override fun getItem(arg0: Int): Any? {
        return null
    }

    override fun getItemId(arg0: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        //map to song layout
        val songLay = songInf.inflate(R.layout.song, parent, false) as LinearLayout
        //get title and artist views
        val songView = songLay.findViewById(R.id.song_title) as TextView
        val artistView = songLay.findViewById(R.id.song_artist) as TextView
        //get song using position
        val currSong = songs.get(position)
        //get title and artist strings
        songView.setText(currSong.getTitle())
        artistView.setText(currSong.getArtist())
        //set position as tag
        songLay.tag = position
        return songLay
    }

}