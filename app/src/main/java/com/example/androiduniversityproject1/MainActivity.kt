package com.example.androiduniversityproject1

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.guessWordButton)
        val wordToGuess = findViewById<TextView>(R.id.actualWord)
        val correctWord = FourLetterWordList.getRandomFourLetterWord()
        val guess = findViewById<EditText>(R.id.guessText)

        //First Guess Variables
        val firstGuess = findViewById<TextView>(R.id.guessedWord1)
        val firstGuessResult = findViewById<TextView>(R.id.guess1result)

        //Second Guess Variables
        val secondGuess = findViewById<TextView>(R.id.secondGuess)
        val secondGuessResult = findViewById<TextView>(R.id.guess2result)

        //Third Guess Variables
        val thirdGuess = findViewById<TextView>(R.id.thirdGuess)
        val thirdGuessResult = findViewById<TextView>(R.id.guess3result)

        var numGuesses = 0 //keep track of number of guesses

        button.setOnClickListener{
            numGuesses++ //increment number of guesses

            var guessValue = guess.getText().toString().uppercase() //convert guess to uppercase
            var word = checkGuess(guessValue.uppercase(), correctWord.uppercase())

            guess.setText("") //clear input text
            hideKeyboard() //call function that hides the android keyboard

            if (numGuesses == 1) {
                firstGuess.setText(guessValue)
                firstGuessResult.setText(word)
                if (guessValue.equals(correctWord)){
                    Toast.makeText(it.context, "Congratulations, your guess was correct!", Toast.LENGTH_SHORT).show()
                    wordToGuess.setText(correctWord)
                    button.isEnabled = false
                    button.isClickable = false
                }
            }
            else if (numGuesses == 2){
                secondGuess.setText(guessValue)
                secondGuessResult.setText(word)
                if (guessValue.equals(correctWord)){
                    Toast.makeText(it.context, "Congratulations, your guess was correct!", Toast.LENGTH_SHORT).show()
                    wordToGuess.setText(correctWord)
                    button.isEnabled = false
                    button.isClickable = false
                }
            }
            else{
                thirdGuess.setText(guessValue)
                thirdGuessResult.setText(word)
                if (guessValue.equals(correctWord)){
                    Toast.makeText(it.context, "Congratulations, your guess was correct!", Toast.LENGTH_SHORT).show()
                    wordToGuess.setText(correctWord)
                    button.isEnabled = false
                    button.isClickable = false
                }
                else {
                    Toast.makeText(it.context, "Sorry, number of guesses exceeded", Toast.LENGTH_SHORT).show()
                    wordToGuess.setText(correctWord)
                    button.isEnabled = false
                    button.isClickable = false
                }
            }
        }
    }

    fun hideKeyboard(){
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var currentFocus = this.currentFocus
        if (currentFocus == null) {
            currentFocus = View(this)
        }
        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */

    private fun checkGuess(guess: String, wordGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }


}