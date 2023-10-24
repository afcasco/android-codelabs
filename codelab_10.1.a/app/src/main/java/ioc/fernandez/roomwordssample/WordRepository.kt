package ioc.fernandez.roomwordssample

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WordRepository(context: Context) {

    private val wordDao: WordDao
    private val mAllWords: LiveData<List<Word>>
    private val db: WordRoomDatabase

    init {
        db =  WordRoomDatabase.getDatabase(context)
        wordDao = db.wordDao()
        mAllWords = wordDao.getAllWords()

    }

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    // intent de sustituir asynctask amb corutines
    suspend fun insert(word: Word) = withContext(Dispatchers.IO) {
        wordDao.insert(word)
    }

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        wordDao.deleteAll()
    }


}