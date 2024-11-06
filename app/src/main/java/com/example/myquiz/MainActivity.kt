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
import android.util.Log
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import androidx.activity.viewModels

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyQuizApp()
        }
    }
}

@Composable
fun MyQuizApp(quizViewModel: QuizViewModel = viewModel()) {
    var number1 by remember { mutableStateOf<Int?>(null) }
    var number2 by remember { mutableStateOf<Int?>(null) }
    var totalCount by remember { mutableIntStateOf(0) }
    var showSolution by remember { mutableStateOf(false) }
    var hasPlayed by remember { mutableStateOf(false) }

    fun generateNumbers() {
        number1 = Random.nextInt(0, 101)
        number2 = Random.nextInt(0, 101)
        totalCount += 1
        showSolution = false
        hasPlayed = true
    }

    fun areNumbersNull(): Boolean {
        Log.d("MyQuizApp", "number1: $number1, number2: $number2")
        return number1 == null || number2 == null
    }

    fun solveButtonClicked() {
        if (!areNumbersNull()){
            showSolution = true
            hasPlayed = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (hasPlayed) "What's ${number1!!} + ${number2!!}?" else "Expression"
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (showSolution && !areNumbersNull()) {
            Text(text = "Solution: ${number1!! + number2!!}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = { solveButtonClicked() }) {
                Text(text = "Solve")
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Total Count: $totalCount")
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { generateNumbers() }) {
                Text(
                    text = if (hasPlayed) "Play Again" else "Play"
                )
            }
        }
    }
}
