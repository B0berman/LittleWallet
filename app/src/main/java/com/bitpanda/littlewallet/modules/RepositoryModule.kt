package com.bitpanda.littlewallet.modules

import com.bitpanda.littlewallet.remote.DummyWebService
import com.bitpanda.littlewallet.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository() : Repository {
        return Repository(DummyWebService())
    }
}