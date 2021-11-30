package com.example.numbers_game_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var btnGuesses: Button? = null
    var edEntertext: EditText? = null
    var items = arrayListOf<String>()
    var theRecyclerView: RecyclerView? = null

    val randomNumber = Random.nextInt(0, 11)
    val guessedTrite: Int = 0
    var guessesOfNumber = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.i("Main", randomNumber.toString())

        viewConnected()
    }

    private fun viewConnected() {

        btnGuesses = findViewById<Button>(R.id.btnAdd)
        edEntertext = findViewById(R.id.edInput)
        btnGuesses?.setOnClickListener {


            if (guessesOfNumber > 0) {
                if (edInput.text.isEmpty() || edInput.text.toString() == "") {

                    Snackbar.make(clMain, "Empty Field, Please Enter Text", Snackbar.LENGTH_SHORT)
                        .show()

                } else if (edInput.text.toString() == randomNumber.toString()) {
                    items?.add(edInput.text.toString())
                    items?.add("You guessed  ${edInput.text.toString()}")

                    theRecyclerView = findViewById<RecyclerView>(R.id.rvMain)

                    theRecyclerView?.adapter = RecyclerViewAdapter(items)

                    theRecyclerView?.layoutManager = LinearLayoutManager(this)

                    btnGuesses!!.isEnabled = false

                    edInput.text.clear()

                } else {

                    items.add("You guessed ${edInput.text.toString()}")
                    items.add("You have ${guessesOfNumber - 1} guesses left")
                    theRecyclerView = findViewById<RecyclerView>(R.id.rvMain)
                    theRecyclerView?.adapter = RecyclerViewAdapter(items)
                    theRecyclerView?.layoutManager = LinearLayoutManager(this)

                    edInput.text.clear()

                }

                guessesOfNumber--
            }
            if (guessesOfNumber == 0) {
                btnGuesses?.isEnabled = false

                edInput.text.clear()
            }
        }

    }
}