package com.bignerdranch.android.geoquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.geoquiz.databinding.ActivityCheatBinding
// added imports
import androidx.lifecycle.ViewModelProvider
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE =
    "com.bignerdranch.android.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {
    /* no longer using binding
    private lateinit var binding: ActivityCheatBinding*/

    private var answerIsTrue = false

    /* create textview and button for answer */
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button

    /* create viewmodel for cheatactivity */
    private val cheatViewModel: CheatViewModel by lazy {
        ViewModelProvider(this)[CheatViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        /* removed
        binding = ActivityCheatBinding.inflate(layoutInflater) */

        /* changed from binding to activity_cheat layout
        setContentView(binding.root) */
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        // initialize text view and answer button from their ids
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)

        /*
        binding.showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }
        */
        // replacement binding lines above
        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShownResult()
            cheatViewModel.answerWasClicked = true
        }

        // checks if the answer was previously clicked. if so, answer text and result are auto-set
        if (cheatViewModel.answerWasClicked) {
            answerTextView.setText(R.string.true_button)
            setAnswerShownResult()
        }
    }

    // added
    private fun setAnswerShownResult() {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, true)
        }
        setResult(RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}