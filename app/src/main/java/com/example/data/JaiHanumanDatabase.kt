package com.example.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

// 1. Inquiries from the Contact/Request Form
@Entity(tableName = "inquiries")
data class Inquiry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String,
    val email: String,
    val message: String,
    val productInterest: String = "General Consultation",
    val timestamp: Long = System.currentTimeMillis()
)

// 2. Favorite Products Bookmarked by users in the Luxury Catalog
@Entity(tableName = "favorite_products")
data class FavoriteProduct(
    @PrimaryKey val productId: String,
    val timestamp: Long = System.currentTimeMillis()
)

// 3. DAO to handle all showroom operations
@Dao
interface JaiHanumanDao {
    // Inquiries
    @Query("SELECT * FROM inquiries ORDER BY timestamp DESC")
    fun getAllInquiries(): Flow<List<Inquiry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInquiry(inquiry: Inquiry)

    @Query("DELETE FROM inquiries WHERE id = :id")
    suspend fun deleteInquiryById(id: Int)

    // Favorites
    @Query("SELECT * FROM favorite_products ORDER BY timestamp DESC")
    fun getAllFavorites(): Flow<List<FavoriteProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteProduct)

    @Query("DELETE FROM favorite_products WHERE productId = :productId")
    suspend fun removeFavorite(productId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_products WHERE productId = :productId LIMIT 1)")
    fun isFavorite(productId: String): Flow<Boolean>
}

// 4. Database Setup
@Database(entities = [Inquiry::class, FavoriteProduct::class], version = 1, exportSchema = false)
abstract class JaiHanumanDatabase : RoomDatabase() {
    abstract fun dao(): JaiHanumanDao
}
