package com.pmaita.mov.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pmaita.mov.core.utils.Configuration
import com.pmaita.mov.data.source.local.dao.MovieDao
import com.pmaita.mov.data.source.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = Configuration.DATABASE_VERSION
)
abstract class MovieDatabase:RoomDatabase() {
    abstract val movieDao:MovieDao
}