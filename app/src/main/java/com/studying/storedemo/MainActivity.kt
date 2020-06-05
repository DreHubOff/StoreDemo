package com.studying.storedemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.studying.storedemo.model.Buyer
import com.studying.storedemo.model.Models
import com.studying.storedemo.model.Provider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    companion object {
        private const val START_VAl = 50

        @Volatile
        private var score = START_VAl
        private val generalList = mutableListOf<Models>()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val responseList = ResponseList.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.container, responseList).commit()
        balance.text = "Кол-во товара: $score"


        startTask(responseList) {
            val randomInt = Random.nextInt(5, 30)
            generalList.add(Provider(randomInt))
            score += randomInt
        }

        startTask(responseList) {
            val randomInt = Random.nextInt(10, 50)
            generalList.add(Buyer(randomInt))
            score -= randomInt
        }
    }

    @SuppressLint("SetTextI18n")
    private fun startTask(listView: ResponseList, makeResponse: () -> Unit) {
        GlobalScope.launch {
            while (score.div(4) < START_VAl && score > 0) {
                delay(Random.nextLong(3000, 5000))
                makeResponse.invoke()
                updateUI {
                    balance.text = "Кол-во товара: $score"
                    listView.updateList(generalList)
                }
            }
            updateUI { balance.text = getString(R.string.beyond_limit) }
        }
    }

    private suspend fun updateUI(update: () -> Unit) =
        withContext(Dispatchers.Main) { update.invoke() }
}
