package fernandez.ioc.cat.songdetail_start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import fernandez.ioc.cat.songdetail_start.content.SongUtils

class SongDetailFragment : Fragment() {

    private var mSong: SongUtils.Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mSong = SongUtils.SONG_ITEMS[requireArguments().getInt(SongUtils.SONG_ID_KEY)]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.song_detail, container, false)
        mSong?.let { rootView.findViewById<TextView>(R.id.song_detail).text = it.details }
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(selectedSong: Int) =
            SongDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(SongUtils.SONG_ID_KEY, selectedSong)
                }
            }
    }
}