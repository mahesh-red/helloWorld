package com.mahesh.helloworld.database

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Entity(tableName = "mTable")
data class mEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    @ColumnInfo(name = "Name")
    var personName:String,
    @ColumnInfo(name = "Marks")
    var marks:String
)

@Dao
interface mDao{
    @Query("SELECT * FROM mTable")
     fun getAllDetails(): Flow<List<mEntity>>

    @Insert
    suspend fun insertDetail(mEntity: mEntity)

    @Delete
    fun deleteDetail(mEntity: mEntity)
}

@Database(entities = [mEntity::class], version = 1, exportSchema = false)
abstract class mBase:RoomDatabase(){
    abstract fun mDao():mDao
}

@InstallIn(SingletonComponent::class)
@Module
object AppModule{
    @Singleton
    @Provides
    fun mDaoProvider(base: mBase):mDao=base.mDao()

    @Singleton
    @Provides
    fun mBaseProvider(@ApplicationContext context: Context):mBase=Room.databaseBuilder(context,
        mBase::class.java,"m_DB").build()

}


class mRepo  @Inject constructor(val mDao: mDao){

    suspend fun getDetails()=mDao.getAllDetails()

    suspend fun insertDetail(mEntity: mEntity)=mDao.insertDetail(mEntity)

    fun deleteDetail(mEntity: mEntity)=mDao.deleteDetail(mEntity)
}