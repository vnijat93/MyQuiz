package com.example.myquiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class QuizViewModel: ViewModel() {
    private val _number1 = MutableLiveData<Int>(null)
    val number1: LiveData<Int> get() = _number1

    private val _number2 = MutableLiveData<Int>(null)
    val number2: LiveData<Int> get() = _number2

    private val _totalCount = MutableLiveData<Int>(0)
    val totalCount: LiveData<Int> get() = _totalCount

    private val _showSolution = MutableLiveData<Boolean>(false)
    val showSolution: LiveData<Boolean> get() = _showSolution

    private val _hasPlayed = MutableLiveData<Boolean>(false)
    val hasPlayed: LiveData<Boolean> get() = _hasPlayed

    fun generateNumbers() {
        _number1.value = Random.nextInt(0, 101)
        _number2.value = Random.nextInt(0, 101)
        _totalCount.value = totalCount.value?.plus(1)
        _showSolution.value = false
        _hasPlayed.value = true
    }

    fun areNumbersNull(): Boolean {
        return number1.value == null || number2.value == null
    }

    fun solveButtonClicked() {
        if (!areNumbersNull()){
            _showSolution.value = true
            _hasPlayed.value = true
        }
    }
}
