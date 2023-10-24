package ioc.fernandez.roomwordssample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private var mRepository: WordRepository
    private var mAllWords: LiveData<List<Word>>

    init {
        mRepository = WordRepository(application.applicationContext)
        mAllWords = mRepository.getAllWords()
    }


    fun getAllWords(): LiveData<List<Word>> = mAllWords

    suspend fun insert(word: Word) = mRepository.insert(word)

    suspend fun deleteAll() = mRepository.deleteAll()
}