package com.example.sportresults.core.util

import com.example.sportresults.core.domain.model.UserDurationTime
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TimeCalcTest {

    @Test
    fun `Get UserDurationTime from duration (seconds), correct UserDurationTime`() {
        val userDurationTime = TimeCalc.getUserDurationTime(6548)

        assertThat(
            userDurationTime.hours == 1 &&
                    userDurationTime.minutes == 49 &&
                    userDurationTime.seconds == 8).isTrue()
    }

    @Test
    fun `Get duration from UserDurationTime, correct duration`() {
        val durationTime = TimeCalc.getDuration(UserDurationTime(hours = 1, minutes = 49, seconds = 8))

        assertThat(
            durationTime == 6548).isTrue()
    }
}