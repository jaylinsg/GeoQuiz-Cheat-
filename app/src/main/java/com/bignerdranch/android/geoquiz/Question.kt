package com.bignerdranch.android.geoquiz

import androidx.annotation.StringRes

data class Question (@StringRes
                     val textResId: Int,
                     val answer: Boolean,
                     // keep track of whether a question was answered
                     var wasAnswered: Boolean = false)