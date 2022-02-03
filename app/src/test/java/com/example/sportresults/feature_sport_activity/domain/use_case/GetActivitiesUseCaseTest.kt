package com.example.sportresults.feature_sport_activity.domain.use_case

import com.example.sportresults.core.util.TimeCalc
import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.data.repository.FakeSportActivityRepository
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class GetActivitiesUseCaseTest {

    private lateinit var getActivities: GetActivitiesUseCase
    private lateinit var fakeRepository: FakeSportActivityRepository

    @Before
    fun setUp() {
        fakeRepository = FakeSportActivityRepository()
        getActivities = GetActivitiesUseCase(fakeRepository)

        val cities = listOf<String>("Praha", "Brno", "Ostrava", "Olomouc", "Písek")
        val durations = listOf<Int>(852, 60, 15478, 145, 36, 88, 79, 129)
        val distances = listOf<Float>(10f, 3.2f, 10f, 0.1f, 2.65f, 8.99f)

        val sportActivitiesToInsert = mutableListOf<SportActivity>()
        (1..10).forEach {
            val userDurationTime = TimeCalc.getUserDurationTime(durations.random())

            sportActivitiesToInsert.add(
                SportActivity(
                    sportType = SportType.getList().random(),
                    storageType = StorageType.Local,
                    place = cities.random(),
                    distance = distances.random(),
                    hours = userDurationTime.hours,
                    minutes = userDurationTime.minutes,
                    seconds = userDurationTime.seconds
                )
            )
        }

        (1..10).forEach {
            val userDurationTime = TimeCalc.getUserDurationTime(durations.random())

            sportActivitiesToInsert.add(
                SportActivity(
                    sportType = SportType.getList().random(),
                    storageType = StorageType.Remote,
                    place = cities.random(),
                    distance = distances.random(),
                    hours = userDurationTime.hours,
                    minutes = userDurationTime.minutes,
                    seconds = userDurationTime.seconds
                )
            )
        }

        sportActivitiesToInsert.shuffle()
        runBlocking {
            sportActivitiesToInsert.forEach { fakeRepository.insertActivity(it) }
        }
    }

    @Test
    fun `Get SportActivities by StorageType Local, correct get`() {
        runBlocking {
            getActivities(StorageType.Local).data?.let { sportActivities ->
                for (i in 0..sportActivities.size - 1) {
                    assertThat(sportActivities[i].storageType!!.equals(StorageType.Local)).isTrue()
                }
            }
        }
    }

    @Test
    fun `Get SportActivities by StorageType Remote, correct get`() {
        runBlocking {
            assertThat(getActivities(StorageType.Remote).data?.filter {
                it.storageType!!.equals(
                    StorageType.Local
                )
            }?.count() == 0).isTrue()
        }
    }

    @Test
    fun `Get SportActivities by StorageType All, correct get`() {
        runBlocking {
            assertThat(getActivities(StorageType.All).data?.filter {
                it.storageType!!.equals(
                    StorageType.All
                )
            }?.count() == 0).isTrue()

            getActivities(StorageType.Local).data?.let { sportActivities ->
                for (i in 0..sportActivities.size - 1) {
                    assertThat(
                        sportActivities[i].storageType!!.equals(StorageType.Local) ||
                                sportActivities[i].storageType!!.equals(StorageType.Remote)
                    ).isTrue()
                }
            }
        }
    }
}