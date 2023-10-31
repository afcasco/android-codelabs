package fernandez.ioc.cat.songdetail_start.content

class SongUtils {

    data class Song(val songTitle: String, val details: String)
    companion object {
        const val SONG_ID_KEY: String = "item_id"
        val SONG_ITEMS: ArrayList<Song> = ArrayList()
        private const val COUNT = 7

        init {
            for (i in 0..<COUNT) {
                addItem(createSongAtPosition(i))
            }
        }

        private fun addItem(item: Song) {
            SONG_ITEMS.add(item)
        }


        private fun createSongAtPosition(position: Int): Song {
            val newTitle: String
            val newDetail: String
            when (position) {
                0 -> {
                    newTitle = "Cry for a Shadow"
                    newDetail = """
            Cry for a Shadow
            
            Many a Beatle fanatic started down the outtake road, like I did, with a first listen to this song. Originally titled “Beatle Bop” and recorded in a single session that yielded four songs (the other three featured Tony Sheridan with the Beatles as a backing band), “Cry for a Shadow” is an instrumental written by Lennon and Harrison, which makes it unique to this day. John Lennon plays rhythm guitar, George Harrison plays lead guitar, Paul McCartney plays bass, and Pete Best plays drums. The sessions were produced by Bert Kaempfert in Hamburg, Germany, during the Beatles’ second visit from April through July of 1961 to play in the Reeperbahn-section clubs.
            """.trimIndent()
                }

                1 -> {
                    newTitle = "My Bonnie - Ain’t She Sweet"
                    newDetail =
                        "My Bonnie - Ain’t She Sweet\n\nAt the same session, the Beatles played on “My Bonnie” (the first-ever single with Beatles playing), as the backing band for English singer Tony Sheridan, originally a member of the Jets. The popularity of this single in Liverpool brought the Beatles to the attention of Brian Epstein, who worked in the NEMS record store and tried to meet demand for the disc. John Lennon then sings a fine “Ain’t She Sweet” (his first-ever released vocal)."
                }

                2 -> {
                    newTitle = "Searching"
                    newDetail =
                        "Searching\n\nA Jerry Leiber - Mike Stoller comedy song that was a hit for the Coasters in 1957, and a popular live favorite of the Beatles. The Coasters also had a hit with “Besame Mucho” and the Beatles covered that song as well. Ringo Starr had by now replaced Pete Best on drums. The high falsetto is George, who also plays a hesitant lead guitar. This is from their first audition for Decca Records in London on Jan 1., 1962, live in the studio. The Grateful Dead would later cover “Searchin” with a similar arrangement, Pigpen doing the Paul vocals. A live version is available on bootlegs featuring the Dead joined by the Beach Boys!"
                }

                3 -> {
                    newTitle = "Love Me Do"
                    newDetail =
                        "Love Me Do\n\nAn early version of the song, played a bit slower and with more of a blues feeling, and a cool bossa-nova beat in middle. Paul had to sing while John played harmonica — a first for the group. Pete Best played drums on this version."
                }

                4 -> {
                    newTitle = "She Loves You – Till There Was You – Twist and Shout"
                    newDetail =
                        "She Loves You – Till There Was You – Twist and Shout\n\nLive at the Princess Wales Theatre by Leicester Square in London, attended by the Queen. “Till There Was You” (by Meredith Wilson) is from the musical The Music Man and a hit for Peggy Lee in 1961. Before playing it, Paul said it was recorded by his favorite American group, “Sophie Tucker” (which got some laughs). At the end, John tells the people in the cheaper seats to clap their hands, and the rest to “rattle your jewelry” and then announces “Twist and Shout” (a song by Bert Russell and Phil Medley that was first recorded in 1962 by the Isley Brothers). A film of the performance shows the Queen smiling at John’s remark."
                }

                5 -> {
                    newTitle = "Leave My Kitten Alone"
                    newDetail =
                        "Leave My Kitten Alone\n\nOne of the lost Beatle songs recorded during the “Beatles For Sale” sessions but never released. This song, written by Little Willie John, Titus Turner, and James McDougal, was a 1959 R&B hit for Little Willie John and covered by Johnny Preston before the Beatles tried it and shelved it. A reference to a “big fat bulldog” may have influenced John’s “Hey Bulldog” (Yellow Submarine album), which is a similar rocker."
                }

                else -> {
                    newTitle = "One After 909"
                    newDetail =
                        "One After 909\n\nA song recorded for the Let It Be album was actually worked on way back in the beginning, six years earlier. This take shows how they did it much more slowly, with an R&B feel to it."
                }
            }
            return Song(newTitle, newDetail)
        }
    }
}