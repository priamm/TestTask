package com.example.test.di

import android.app.Application
import androidx.room.Room
import com.example.test.BuildConfig
import com.example.test.data.db.CrossRefDao
import com.example.test.data.db.Database
import com.example.test.data.db.EmployeeDao
import com.example.test.data.db.SpecialtyDao
import com.example.test.data.network.GitlabApi
import com.example.test.util.TLSSocketFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideApi(): GitlabApi {
        val socketFactory = TLSSocketFactory()
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .client(
                OkHttpClient.Builder().sslSocketFactory(socketFactory, socketFactory.trustManager)
                    .build()
            )
            .addConverterFactory(Json.asConverterFactory(contentType))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(GitlabApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): Database {
        return Room
            .databaseBuilder(app, Database::class.java, "specialties.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideEmployeeDao(db: Database): EmployeeDao {
        return db.employeeDao()
    }

    @Singleton
    @Provides
    fun provideSpecialtyDao(db: Database): SpecialtyDao {
        return db.specialtyDao()
    }

    @Singleton
    @Provides
    fun provideCrossRefDao(db: Database): CrossRefDao {
        return db.crossRefDao()
    }
}
