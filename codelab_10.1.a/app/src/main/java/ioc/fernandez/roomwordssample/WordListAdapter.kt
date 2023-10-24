package ioc.fernandez.roomwordssample

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter(
    private val context: Context,
    private var mWordList: List<Word>?,
) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    override fun getItemCount(): Int = mWordList?.let { mWordList!!.size } ?: 0

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.wordItemView.text = mWordList?.let {
            mWordList!![position].word
        } ?: "No Word"
    }

    fun setWords(words: List<Word>) {
        mWordList = words
        notifyDataSetChanged()
    }


    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val wordItemView: TextView = itemView.findViewById(R.id.textView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.i("WordListAdapter","not implemented")
        }
    }
}