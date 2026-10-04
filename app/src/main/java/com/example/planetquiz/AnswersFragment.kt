package com.example.planetquiz

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class AnswersFragment : Fragment(R.layout.fragment_answers) {

    private val correctAnswers = listOf("JUPITER", "SATURN", "URANUS")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionIndex = requireArguments().getInt(ARG_QUESTION_INDEX)
        val questions = resources.getStringArray(R.array.questions)
        val details = resources.getStringArray(R.array.details)
        val planets = resources.getStringArray(R.array.planets)

        val questionText = view.findViewById<TextView>(R.id.question_text)
        val resultText = view.findViewById<TextView>(R.id.result_text)
        val answerContainer = view.findViewById<LinearLayout>(R.id.answer_container)

        questionText.text = questions[questionIndex]

        val margin = (16 * resources.displayMetrics.density).toInt()

        planets.forEach { planet ->
            val button = MaterialButton(requireContext()).apply {
                text = planet
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply { topMargin = margin }

                setOnClickListener {
                    resultText.text = if (planet == correctAnswers[questionIndex]) {
                        getString(R.string.correct_message, details[questionIndex])
                    } else {
                        getString(R.string.wrong_message)
                    }
                }
            }
            answerContainer.addView(button)
        }
    }

    companion object {
        private const val ARG_QUESTION_INDEX = "question_index"

        fun newInstance(questionIndex: Int): AnswersFragment {
            return AnswersFragment().apply {
                arguments = Bundle().apply { putInt(ARG_QUESTION_INDEX, questionIndex) }
            }
        }
    }
}
