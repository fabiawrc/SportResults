package com.example.sportresults.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.sportresults.core.data.local.AppLocalDatabase
import com.example.sportresults.feature_sport_activity.data.local.SportActivityData_Fake
import com.example.sportresults.feature_sport_activity.data.remote.SportActivitiApi_Fake
import com.example.sportresults.feature_sport_activity.data.remote.SportActivityApi
import com.example.sportresults.feature_sport_activity.data.repository.SportActivityRepositoryImpl
import com.example.sportresults.feature_sport_activity.data.repository.SportTypeRepositoryImpl
import com.example.sportresults.feature_sport_activity.data.repository.StorageTypeRepositoryImpl
import com.example.sportresults.feature_sport_activity.domain.repository.SportActivityRepository
import com.example.sportresults.feature_sport_activity.domain.repository.SportTypeRepository
import com.example.sportresults.feature_sport_activity.domain.repository.StorageTypeRepository
import com.example.sportresults.feature_sport_activity.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideSportActivityMemoryData(): SportActivityData_Fake{
        return SportActivityData_Fake()
    }

    @Provides
    @Singleton
    fun provideAppLocalDatabase(app: Application): AppLocalDatabase {
        return Room.databaseBuilder(
            app, AppLocalDatabase::class.java, "sportresults_db"
        ).build()
    }

//    @Provides
//    @Singleton
//    fun provideSportActivityApi(
//    ): SportActivityApi {
//        val baseURL = "https://localhost:44349/"
//
//        return Retrofit.Builder()
//            .baseUrl(baseURL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(SportActivityApi::class.java)
//    }

    @Provides
    @Singleton
    fun provideSportActivityApi(sportActivityDataFake: SportActivityData_Fake): SportActivityApi {
        return SportActivitiApi_Fake(sportActivityDataFake)
    }

    @Provides
    @Singleton
    fun provideSportActivityRepository(sportActivityApi: SportActivityApi, db: AppLocalDatabase): SportActivityRepository {
        return SportActivityRepositoryImpl(db.sportActivityDao, sportActivityApi)
    }

    @Provides
    @Singleton
    fun provideSportTypeRepository(): SportTypeRepository {
        return SportTypeRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideStorageTypeRepository(): StorageTypeRepository {
        return StorageTypeRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideSportActivityUseCases(
        repositorySport: SportActivityRepository,
        repositorySportType: SportTypeRepository,
        repositoryStorageType: StorageTypeRepository
    ): SportActivityUseCases {
        return SportActivityUseCases(
            getActivitiesUseCase = GetActivitiesUseCase(repositorySport),
            getActivityUseCase = GetActivityUseCase(repositorySport),
            insertActivityUseCase = InsertActivityUseCase(repositorySport),
            getSportTypesUseCase = GetSportTypesUseCase(repositorySportType),
            getSportTypeUseCase = GetSportTypeUseCase(repositorySportType),
            getStorageTypeUseCase = GetStorageTypeUseCase(repositoryStorageType),
            getStorageTypesUseCase = GetStorageTypesUseCase(repositoryStorageType)
        )
    }

}