package com.example.data

import kotlinx.coroutines.flow.Flow

class JaiHanumanRepository(private val dao: JaiHanumanDao) {
    
    val allInquiries: Flow<List<Inquiry>> = dao.getAllInquiries()
    
    val allFavorites: Flow<List<FavoriteProduct>> = dao.getAllFavorites()

    suspend fun insertInquiry(inquiry: Inquiry) {
        dao.insertInquiry(inquiry)
    }

    suspend fun deleteInquiry(id: Int) {
        dao.deleteInquiryById(id)
    }

    suspend fun addFavorite(productId: String) {
        dao.addFavorite(FavoriteProduct(productId))
    }

    suspend fun removeFavorite(productId: String) {
        dao.removeFavorite(productId)
    }

    fun isFavorite(productId: String): Flow<Boolean> {
        return dao.isFavorite(productId)
    }
}
