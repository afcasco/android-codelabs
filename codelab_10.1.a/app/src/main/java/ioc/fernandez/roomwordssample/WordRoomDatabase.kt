package ioc.fernandez.roomwordssample


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        private var instance: WordRoomDatabase? = null
        private const val database: String = "word_database"

        fun getDatabase(context: Context): WordRoomDatabase {
            return instance ?: synchronized(WordRoomDatabase::class.java) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext, WordRoomDatabase::class.java, database
                ).fallbackToDestructiveMigration().addCallback(WordDatabaseCallback(context))
                    .build().also { instance = it }
            }
        }
    }

    private class WordDatabaseCallback(private val context: Context) : Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            CoroutineScope(Dispatchers.IO).launch {
                initializeDbSampleData(context)
            }
        }
    }
}


private fun initializeDbSampleData(context: Context) {
    val mDao = WordRoomDatabase.getDatabase(context).wordDao()
    mDao.deleteAll()

    mDao.insert(Word("dolphin"))
    mDao.insert(Word("crocodile"))
    mDao.insert(Word("cobra"))
}