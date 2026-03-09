package com.example.favoritesearchapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    val dictionary = mutableMapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadDictionary()

        val searchInput = findViewById<TextInputEditText>(R.id.searchInput)
        val resultText = findViewById<TextView>(R.id.resultText)
        val searchButton = findViewById<Button>(R.id.searchButton)

        val queryInput = findViewById<TextInputEditText>(R.id.queryInput)
        val saveButton = findViewById<Button>(R.id.saveButton)

        // SEARCH BUTTON
        searchButton.setOnClickListener {

            val word = searchInput.text.toString().trim().lowercase()

            if (dictionary.containsKey(word)) {
                resultText.text = dictionary[word]
            } else {
                resultText.text = "Word not found"
            }
        }

        // SAVE BUTTON (simple functionality)
        saveButton.setOnClickListener {

            val query = queryInput.text.toString()

            if (query.isNotEmpty()) {
                searchInput.setText(query)
                resultText.text = "Query saved: $query"
            }
        }
    }

    fun loadDictionary() {

        val inputStream = assets.open("en_mk_recnik.txt")
        val reader = inputStream.bufferedReader()

        reader.forEachLine {

            val parts = it.split(",")

            if (parts.size == 2) {

                val en = parts[0].trim().lowercase()
                val mk = parts[1].trim().lowercase()

                dictionary[en] = mk
                dictionary[mk] = en
            }
        }
    }
}