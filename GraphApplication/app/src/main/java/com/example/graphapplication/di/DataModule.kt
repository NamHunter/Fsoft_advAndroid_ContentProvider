package com.example.graphapplication.di

import android.content.Context
import com.example.graphapplication.data.remotesource.MyChartDataSource
import com.example.graphapplication.data.repository.ChartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDataChart(@ApplicationContext context: Context): MyChartDataSource =
        MyChartDataSource(context)

    @Provides
    @Singleton
    fun provideChartRepository(myChartDataSource: MyChartDataSource): ChartRepository =
        ChartRepository(myChartDataSource)
}