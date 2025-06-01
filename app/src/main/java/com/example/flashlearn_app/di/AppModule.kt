package com.example.flashlearn_app.di


import android.app.Application
import androidx.room.Room
import com.example.flashlearn_app.data.dao.CardDao
import com.example.flashlearn_app.data.dao.DeckDao
import com.example.flashlearn_app.data.dao.UserDao
import com.example.flashlearn_app.data.database.AppDatabase
import com.example.flashlearn_app.data.repository.CardRepository
import com.example.flashlearn_app.data.repository.CardRepositoryImpl
import com.example.flashlearn_app.data.repository.DeckRepository
import com.example.flashlearn_app.data.repository.DeckRepositoryImpl
import com.example.flashlearn_app.data.repository.UserRepository
import com.example.flashlearn_app.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "flashlearn_db"
        ).build()
    }

    @Provides
    fun provideCardDao(db: AppDatabase): CardDao = db.cardDao()

    @Provides
    fun provideDeckDao(db: AppDatabase): DeckDao = db.deckDao()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideCardRepository(cardDao: CardDao): CardRepository =
        CardRepositoryImpl(cardDao)

    @Provides
    @Singleton
    fun provideDeckRepository(deckDao: DeckDao): DeckRepository =
        DeckRepositoryImpl(deckDao)

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository =
        UserRepositoryImpl(userDao)
}
