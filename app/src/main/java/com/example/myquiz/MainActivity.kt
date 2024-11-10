package com.example.myquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val quizViewModel: QuizViewModel by viewModels()
            MyQuizApp(quizViewModel)
        }
    }
}

@Composable
fun MyQuizApp(quizViewModel: QuizViewModel) {
    val number1 by quizViewModel.number1.observeAsState()
    val number2 by quizViewModel.number2.observeAsState()
    val totalCount by quizViewModel.totalCount.observeAsState()
    val showSolution by quizViewModel.showSolution.observeAsState()
    val hasPlayed by quizViewModel.hasPlayed.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (hasPlayed == true) "What's ${number1!!} + ${number2!!}?" else "Expression"
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (showSolution == true && !quizViewModel.areNumbersNull()) {
            Text(text = "Solution: ${number1!! + number2!!}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = { quizViewModel.solveButtonClicked() }) {
                Text(text = "Solve")
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Total Count: $totalCount")
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { quizViewModel.generateNumbers() }) {
                Text(
                    text = if (hasPlayed == true) "Play Again" else "Play"
                )
            }
        }
    }
}
