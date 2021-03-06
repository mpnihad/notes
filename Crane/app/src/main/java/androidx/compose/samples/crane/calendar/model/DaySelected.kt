/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.samples.crane.calendar.model

import androidx.compose.samples.crane.calendar.data.january2020
import androidx.compose.samples.crane.calendar.data.year2020

data class DaySelected(val day: Int, val month: CalendarMonth) {
    val calendarDay = lazy {
        month.getDay(day)
    }

    override fun toString(): String {
        return "${month.name.substring(0, 3).capitalize()} $day"
    }

    operator fun compareTo(other: DaySelected): Int {
        if (day == other.day && month == other.month) return 0
        if (month == other.month) return day.compareTo(other.day)
        return (year2020.indexOf(month)).compareTo(
            year2020.indexOf(other.month)
        )
    }
}

/**
 * Represents an empty value for [DaySelected]
 */
val DaySelectedEmpty = DaySelected(-1, january2020)
