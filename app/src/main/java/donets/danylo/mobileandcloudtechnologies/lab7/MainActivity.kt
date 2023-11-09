package donets.danylo.mobileandcloudtechnologies.lab7

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import donets.danylo.mobileandcloudtechnologies.lab7.GameLogick.Episode
import donets.danylo.mobileandcloudtechnologies.lab7.GameLogick.GameDatabase

class MainActivity : AppCompatActivity() {

    // Використання лінивого ініціалізатора та applicationContext
    private lateinit var gameDatabase: GameDatabase
    private lateinit var titleTextView: TextView
    private lateinit var conditionTextView: TextView
    private lateinit var option1TextView: TextView
    private lateinit var option2TextView: TextView
    private lateinit var chooseOption1Button: Button
    private lateinit var chooseOption2Button: Button

    private var currentEpisodeIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameDatabase = GameDatabase.getInstance(applicationContext)


        val episodes = gameDatabase.getAllEpisodes()

        // Ініціалізація елементів інтерфейсу
        titleTextView = findViewById(R.id.titleTextView)
        conditionTextView = findViewById(R.id.conditionTextView)
        option1TextView = findViewById(R.id.option1TextView)
        option2TextView = findViewById(R.id.option2TextView)
        chooseOption1Button = findViewById(R.id.chooseOption1Button)
        chooseOption2Button = findViewById(R.id.chooseOption2Button)

        // Встановлення тексту для першого епізоду
        setEpisodeText(episodes[currentEpisodeIndex])

        // Налаштування обробників подій для кнопок
        chooseOption1Button.setOnClickListener {
            handleOptionClick(episodes[currentEpisodeIndex].option1)
        }

        chooseOption2Button.setOnClickListener {
            handleOptionClick(episodes[currentEpisodeIndex].option2)
        }
    }

    private fun setEpisodeText(episode: Episode) {
        // Встановлення тексту для поточного епізоду
        titleTextView.text = "${episode.id+1}. "+episode.title
        conditionTextView.text = "<b>Опис:</b> "+episode.description
        option1TextView.text = "<b>Варіант 1:</b> "+episode.option1
        option2TextView.text = "<b>Варіант 2:</b> "+episode.option2
    }

    private fun handleOptionClick(option: String) {
        // Обробка вибору гравця
        // Можна додати логіку для зміни поточного епізоду на основі вибору гравця

        // Збільшення індексу для переходу до наступного епізоду
        currentEpisodeIndex++

        // Перевірка, чи ще є доступні епізоди
        if (currentEpisodeIndex < gameDatabase.getAllEpisodes().size) {
            // Оновлення тексту та варіантів відповіді
            setEpisodeText(gameDatabase.getAllEpisodes()[currentEpisodeIndex])
        } else {
            val message = "Вітаємо з проходженням гри"
            AlertDialog.Builder(this)
                .setTitle("Ви виграли")
                .setMessage(message)
                .setPositiveButton("Win")
                { _,_ ->

                }
                .setCancelable(false)
                .show()
        }
    }
}
