package com.example.data

data class ProductCategory(
    val id: String,
    val name: String,
    val description: String,
    val iconName: String
)

data class ProductItem(
    val id: String,
    val name: String,
    val categoryId: String,
    val brand: String,
    val size: String,
    val finish: String,
    val highlights: List<String>,
    val detailDescription: String,
    val previewStyle: Int // Identifies dynamic luxury procedural ceramic pattern to draw
)

data class BranchInfo(
    val id: String,
    val name: String,
    val establishedYear: String,
    val description: String,
    val features: List<String>,
    val address: String,
    val mapQuery: String, // Google Maps search/coords query
    val isFlagship: Boolean
)

data class TestimonialItem(
    val id: String,
    val author: String,
    val role: String,
    val rating: Int,
    val review: String,
    val location: String
)

data class TimelineEvent(
    val year: String,
    val title: String,
    val description: String
)

data class BrandInfo(
    val name: String,
    val officialUrl: String,
    val description: String
)
