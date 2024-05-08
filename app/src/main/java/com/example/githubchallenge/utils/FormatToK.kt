package com.example.githubchallenge.utils

class FormatToK {
   companion object {
        fun Long.formatToK(): String {
            val num = this.toDouble()
            return when {
                num >= 1_000_000 -> "${String.format("%.1f", num / 1_000_000)}M"
                num >= 1_000 -> "${String.format("%.1f", num / 1_000)}k"
                else -> "$this"
            }
        }
    }
}