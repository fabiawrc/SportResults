package com.example.sportresults.core.util

import com.example.sportresults.core.domain.model.UserDurationTime

class TimeCalc {
    companion object {
        fun getUserDurationTime(duration: Int): UserDurationTime {
            val durationTime = UserDurationTime()
            durationTime.hours = duration / 3600
            var remaindSec = duration.mod(3600)
            durationTime.minutes = remaindSec / 60
            durationTime.seconds = duration.mod(60)

            return durationTime
        }

        fun getDuration(userDurationTime: UserDurationTime): Int {
            var duration = 0
            duration += userDurationTime.hours * 3600
            duration += userDurationTime.minutes * 60
            duration += userDurationTime.seconds
            return duration
        }
    }
}