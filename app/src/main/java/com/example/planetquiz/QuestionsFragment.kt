package com.example.planetquiz

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class QuestionsFragment : Fragment(R.layout.fragment_questions) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonIds = listOf(
            R.id.question_1_button,
            R.id.question_2_button,
            R.id.question_3_button
        )

        buttonIds.forEachIndexed { index, id ->
            view.findViewById<Button>(id).setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AnswersFragment.newInstance(index))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}