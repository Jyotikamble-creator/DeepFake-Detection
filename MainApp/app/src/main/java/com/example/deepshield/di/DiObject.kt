package com.example.deepshield.di

import android.content.Context
import com.example.deepshield.data.UseCases.GetAllSongUseCase
import com.example.deepshield.data.UseCases.GetFrameFromServerUseCase
import com.example.deepshield.data.UseCases.GetGradCamUseCase
import com.example.deepshield.data.UseCases.GetHeatMapUseCase
import com.example.deepshield.data.UseCases.ImagePredictionUseCase
import com.example.deepshield.data.UseCases.NewsPredictionUseCase
import com.example.deepshield.data.UseCases.UploadAudioToServerUseCase
import com.example.deepshield.data.UseCases.UploadVideoToServerUseCase
import com.example.deepshield.data.UseCases.UseCaseHelper.UseCaseHelperClass
import com.example.deepshield.data.repoIMPL.RepositoryImpl
import com.example.deepshield.data.repoIMPL.SongRepoImpl
import com.example.deepshield.domain.Repository.Repository
import com.example.deepshield.domain.Repository.SongRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiObject {
    @Provides
    @Singleton
    fun provideHttpClient():HttpClient{

        val client = HttpClient(CIO){
            install(HttpTimeout) {
                requestTimeoutMillis = 300_000 // 120 seconds (2 minutes)
                connectTimeoutMillis = 300_000  // 60 seconds (1 minute)
                socketTimeoutMillis = 300_000  // 120 seconds (2 minutes)
            }
            install(ContentNegotiation){
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient=true
                        prettyPrint=true
                    }
                )
            }

        }
        return client
    }


    @Provides
    fun provideRepoIMPL( httpClient: HttpClient):RepositoryImpl{
        return RepositoryImpl(httpClient = httpClient)
    }
    @Provides
    fun provideRepository(httpClient: HttpClient):Repository{
        return RepositoryImpl(httpClient = httpClient)
    }
    @Provides
    fun getAllSongUseCaseObj(songRepository: SongRepository): GetAllSongUseCase{
        return GetAllSongUseCase(repository = songRepository)
    }
    @Provides
    fun uploadAudioUseCaseObj(httpClient: HttpClient): UploadAudioToServerUseCase{
        return UploadAudioToServerUseCase(repository = provideRepository(httpClient = httpClient))
    }
    @Provides
    fun useCaseHelperClass(@ApplicationContext context: Context,httpClient: HttpClient):UseCaseHelperClass{
        return UseCaseHelperClass(
            getGradCamFromServerUseCase = GetGradCamUseCase(repository = provideRepository(httpClient = httpClient)),
            getFrameFromServerUseCase = GetFrameFromServerUseCase(repository = provideRepository(httpClient = httpClient)),
            getHeatMapFromServerUseCase = GetHeatMapUseCase(repository = provideRepository(httpClient = httpClient)),
            uploadVideoToDeepFakeServerUseCase = UploadVideoToServerUseCase(repository = provideRepository(httpClient = httpClient)),
            getAllSongUseCase=getAllSongUseCaseObj(songRepository = providesongRepoObj(context = context)),
            uploadAudioToServerUseCase = UploadAudioToServerUseCase(repository = provideRepository(httpClient = httpClient)),
            newsPredictionUseCase = NewsPredictionUseCase(repository = provideRepository(httpClient = httpClient)),
            imagePredictionUseCase = ImagePredictionUseCase(repository = provideRepository(httpClient = httpClient))
        )
    }
    @Provides
    fun providesongRepoObj(@ApplicationContext context: Context): SongRepository{
        return SongRepoImpl(context =context )
    }

}