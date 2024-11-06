package com.example.myquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class QuizViewModel: ViewModel() {
    val number1 = MutableLiveData<Int>(null)
    val number2 = MutableLiveData<Int>(null)
    val totalCount = MutableLiveData<Int>(0)
    val showSolution = MutableLiveData<Boolean>(false)
    val hasPlayed = MutableLiveData<Boolean>(false)

    fun generateNumbers() {
        number1.value = Random.nextInt(0, 101)
        number2.value = Random.nextInt(0, 101)
        totalCount.value = totalCount.value?.plus(1)
        showSolution.value = false
        hasPlayed.value = true
    }

    fun areNumbersNull(): Boolean {
        return number1.value == null || number2.value == null
    }

    fun solveButtonClicked() {
        if (!areNumbersNull()){
            showSolution.value = true
            hasPlayed.value = true
        }
    }
}