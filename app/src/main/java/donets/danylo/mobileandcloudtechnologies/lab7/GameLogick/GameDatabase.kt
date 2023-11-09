package donets.danylo.mobileandcloudtechnologies.lab7.GameLogick

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GameDatabase private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "game.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_EPISODES = "episodes"
        private const val KEY_ID = "id"
        private const val KEY_TITLE = "title"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_OPTION1 = "option1"
        private const val KEY_OPTION2 = "option2"

        private var instance: GameDatabase? = null

        fun getInstance(context: Context): GameDatabase {
            if (instance == null) {
                instance = GameDatabase(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        createTable(db)
        addAllEpisodes(db)
    }

    private fun createTable(db: SQLiteDatabase) {
        val createTableQuery =
            """
            CREATE TABLE IF NOT EXISTS $TABLE_EPISODES (
                $KEY_ID INTEGER PRIMARY KEY,
                $KEY_TITLE TEXT,
                $KEY_DESCRIPTION TEXT,
                $KEY_OPTION1 TEXT,
                $KEY_OPTION2 TEXT
            );
            """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EPISODES")
        onCreate(db)
    }

    private fun addEpisode(db: SQLiteDatabase, episode: Episode) {
        val values = ContentValues().apply {
            put(KEY_ID, episode.id)
            put(KEY_TITLE, episode.title)
            put(KEY_DESCRIPTION, episode.description)
            put(KEY_OPTION1, episode.option1)
            put(KEY_OPTION2, episode.option2)
        }
        db.insert(TABLE_EPISODES, null, values)
    }

    private fun addAllEpisodes(db: SQLiteDatabase) {
        val episode1 = Episode(0, "Середньовічний міст",
            "Ви прокидаєтеся в лісі поруч із старовинним містом, який виглядає, наче час в ньому зупинився. Заходячи на міст, ви помічаєте таємничого стражника.",
            "Підходити до стражника і спитати про найближчий вихід.",
            "Обійти стороною і намагатися вийти з міста на власну руку.")

        val episode2 = Episode(1, "Магічний алтар",
            "Поза містом ви натрапляєте на старовинний алтар. Навколо алтаря кружляють дивні світлові форми.",
            "Взяти один з артефактів, щоб з'ясувати його призначення.",
            "Пропустити алтар і вирушити глибше в ліс.")

        val episode3 = Episode(2, "Дерев'яний міст",
            "В лісі ви знаходите річковий переїзд, але міст виглядає дуже хрупко.",
            "Перейти міст, ігноруючи його стан.",
            "Спробувати знайти інший шлях через річку.")

        val episode4 = Episode(3, "Таємничі світлові стежки",
            "По той бік річки ви виявляєте світлові стежки, які призводять вглиб лісу.",
            "Слідувати світловим стежкам, сподіваючись на нові відкриття.",
            "Обійти стежки і шукати інший вихід з лісу.")

        val episode5 = Episode(4, "Зустріч з лісовим чарівником",
            "Спустившись глибше в ліс, ви натрапляєте на чарівника, який пропонує вам вибір:",
            "Питати чарівника про таємниці лісу.",
            "Пропонувати чарівнику свою допомогу в обмін на вихід з лісу.")

        val episode6 = Episode(5, "Схована долина",
            "Ви розкриваєте таємничу долину, де росте рідкісна рослина.",
            "Досліджувати долину та з'ясовувати призначення рослини.",
            "Проігнорувати долину та продовжити подорож.")

        val episode7 = Episode(6, "Таємниця стародавнього мавпеня",
            "Ви натрапляєте на стародавнє дерево, яке на першому погляді схоже на мавпеня.",
            "Спробувати взаємодіяти з мавпенем та дізнатися про його таємниці.",
            "Пройти повз і ігнорувати дерево.")

        val episode8 = Episode(7, "Темний лісовий храм",
            "В глибині лісу ви виявляєте стародавній храм, огорнутий магічною аурою.",
            "Ввійти в храм і вивчити його таємниці.",
            "Пройти повз храм та уникати можливих небезпек.")

        val episode9 = Episode(8, "Туманна поляна",
            "Ви виходите на туманну поляну, де плавають світлові кулі.",
            "Намагатися взаємодіяти з кулями та дізнатися їхню природу.",
            "Пропустити поляну і шукати інший шлях.")

        val episode10 = Episode(9, "Гори загадкових вогнів",
            "На горизонті ви бачите світлові вогні, які танцюють в повітрі.",
            "Подолати важкодоступний терен і дізнатися про природу вогнів.",
            "Ігнорувати вогні та шукати інший шлях.")

        val episode11 = Episode(10, "Літаючі острови",
            "Вас веде світловий шлях до літаючих островів, що висять у повітрі.",
            "Вивчити острови та їхню історію.",
            "Відмовитися від дослідження і шукати інший шлях.")

        val episode12 = Episode(11, "Лісова стежина часу",
            "Ви знаходите таємничу стежину, яка, здається, може переносити вас в часі.",
            "Переступити на стежину та дослідити її вплив.",
            "Залишити стежину та шукати інші шляхи.")

        val episode13 = Episode(12, "Забуте місто",
            "Стежина приводить вас до руїн старовинного міста, яке зникло з карт.",
            "Досліджувати місто та з'ясовувати, чому воно стало забутим.",
            "Проходити повз місто та продовжити свою подорож.")

        val episode14 = Episode(13, "Застава таємничих стражів",
            "Ви натрапляєте на таємничу заставу, за якою стоять стражі з загадковими символами.",
            "Спробувати взаємодіяти зі стражами та пройти заставу.",
            "Шукати обхідний шлях навколо застави.")

        val episode15 = Episode(14, "Остаточний вибір",
            "Ваші рішення підводять вас до остаточного виходу з лісу, і ви розумієте, що ваші вчинки визначили вашу долю та призвели до певного кінця історії.",
            "Побачити наслідки своїх вчинків та, можливо, змінити кінцівку.",
            "Завершити гру та переглянути всі можливі варіанти завершення.")
        // Додавання тестових епізодів до бази даних
        addEpisode(db , episode1)
        addEpisode(db ,episode2)
        addEpisode(db ,episode3)
        addEpisode(db ,episode4)
        addEpisode(db ,episode5)
        addEpisode(db ,episode6)
        addEpisode(db ,episode7)
        addEpisode(db ,episode8)
        addEpisode(db ,episode9)
        addEpisode(db ,episode10)
        addEpisode(db ,episode11)
        addEpisode(db ,episode12)
        addEpisode(db ,episode13)
        addEpisode(db ,episode14)
        addEpisode(db ,episode15)

        // Додайте інші епізоди за аналогією

        // Продовжіть додавати всі епізоди, які вам потрібно
    }

    fun getAllEpisodes(): List<Episode> {
        val episodeList = mutableListOf<Episode>()
        val selectQuery = "SELECT * FROM $TABLE_EPISODES"
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        with(cursor) {
            while (moveToNext()) {
                try {
                    val id = getInt(getColumnIndexOrThrow(KEY_ID))
                    val title = getString(getColumnIndexOrThrow(KEY_TITLE))
                    val description = getString(getColumnIndexOrThrow(KEY_DESCRIPTION))
                    val option1 = getString(getColumnIndexOrThrow(KEY_OPTION1))
                    val option2 = getString(getColumnIndexOrThrow(KEY_OPTION2))

                    val episode = Episode(id, title, description, option1, option2)
                    episodeList.add(episode)
                } catch (e: Exception) {
                    // Log or print the exception for debugging
                    e.printStackTrace()
                }
            }
            close()
        }

        return episodeList
    }
}