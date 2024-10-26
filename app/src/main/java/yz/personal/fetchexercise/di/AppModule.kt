package yz.personal.fetchexercise.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yz.personal.fetchexercise.data.remote.ItemsApi
import yz.personal.fetchexercise.data.remote.ItemsRepositoryImpl
import yz.personal.fetchexercise.domain.repository.ItemsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemsApi(): ItemsApi {
        return Retrofit.Builder()
            .baseUrl(" https://fetch-hiring.s3.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ItemsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesItemsRepository(itemsApi: ItemsApi): ItemsRepository {
        return ItemsRepositoryImpl(itemsApi)
    }
}