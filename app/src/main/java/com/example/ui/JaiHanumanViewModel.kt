package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.Inquiry
import com.example.data.JaiHanumanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ShowroomProduct(
    val id: String,
    val name: String,
    val category: String,
    val brand: String,
    val specs: String,
    val description: String,
    val accentColorHex: String,
    val isGlossy: Boolean = true
)

class JaiHanumanViewModel(private val repository: JaiHanumanRepository) : ViewModel() {

    // --- NAVIGATION STATE ---
    enum class AppScreen { HOME, PRODUCTS, SHOWROOMS, ENQUIRE }
    private val _currentScreen = MutableStateFlow(AppScreen.HOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    // --- ROOM DATA FLOWS ---
    val inquiriesFlow: StateFlow<List<Inquiry>> = repository.allInquiries
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val favoritesFlow: StateFlow<List<String>> = repository.allFavorites
        .map { favList -> favList.map { it.productId } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // --- PRODUCT SEARCH & FILTER ---
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategoryFilter = MutableStateFlow<String?>(null)
    val selectedCategoryFilter: StateFlow<String?> = _selectedCategoryFilter.asStateFlow()

    private val _showOnlyFavorites = MutableStateFlow(false)
    val showOnlyFavorites: StateFlow<Boolean> = _showOnlyFavorites.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectCategoryFilter(category: String?) {
        _selectedCategoryFilter.value = category
    }

    fun toggleFavoritesFilter() {
        _showOnlyFavorites.value = !_showOnlyFavorites.value
    }

    // --- CONTACT FORM STATE & VALIDATION ---
    private val _contactName = MutableStateFlow("")
    val contactName = _contactName.asStateFlow()

    private val _contactPhone = MutableStateFlow("")
    val contactPhone = _contactPhone.asStateFlow()

    private val _contactEmail = MutableStateFlow("")
    val contactEmail = _contactEmail.asStateFlow()

    private val _contactMessage = MutableStateFlow("")
    val contactMessage = _contactMessage.asStateFlow()

    private val _selectedProductInterest = MutableStateFlow("General Consultation")
    val selectedProductInterest = _selectedProductInterest.asStateFlow()

    private val _isSubmitting = MutableStateFlow(false)
    val isSubmitting = _isSubmitting.asStateFlow()

    private val _formSubmissionSuccess = MutableStateFlow(false)
    val formSubmissionSuccess = _formSubmissionSuccess.asStateFlow()

    private val _formError = MutableStateFlow<String?>(null)
    val formError = _formError.asStateFlow()

    fun onNameChange(value: String) { _contactName.value = value }
    fun onPhoneChange(value: String) { _contactPhone.value = value }
    fun onEmailChange(value: String) { _contactEmail.value = value }
    fun onMessageChange(value: String) { _contactMessage.value = value }
    fun onInterestChange(value: String) { _selectedProductInterest.value = value }

    fun resetSubmissionStatus() {
        _formSubmissionSuccess.value = false
        _formError.value = null
    }

    fun submitInquiry() {
        val name = _contactName.value.trim()
        val phone = _contactPhone.value.trim()
        val email = _contactEmail.value.trim()
        val message = _contactMessage.value.trim()
        val interest = _selectedProductInterest.value

        if (name.isEmpty()) {
            _formError.value = "Name is required"
            return
        }
        if (phone.isEmpty()) {
            _formError.value = "Phone is required"
            return
        }
        if (phone.length < 10) {
            _formError.value = "Please enter a valid 10-digit phone number"
            return
        }

        viewModelScope.launch {
            _isSubmitting.value = true
            _formError.value = null
            try {
                // Instantly save to local database
                repository.insertInquiry(
                    Inquiry(
                        name = name,
                        phone = phone,
                        email = email.ifEmpty { "N/A" },
                        message = message.ifEmpty { "Interested in: $interest" },
                        productInterest = interest
                    )
                )
                _formSubmissionSuccess.value = true
                // Reset form fields
                _contactName.value = ""
                _contactPhone.value = ""
                _contactEmail.value = ""
                _contactMessage.value = ""
            } catch (e: Exception) {
                _formError.value = "Failed to save inquiry. Please try again."
            } finally {
                _isSubmitting.value = false
            }
        }
    }

    fun deleteInquiry(id: Int) {
        viewModelScope.launch {
            repository.deleteInquiry(id)
        }
    }

    // --- FAVORITES MARK ACTION ---
    fun toggleProductFavorite(productId: String) {
        val currentFavorites = favoritesFlow.value
        viewModelScope.launch {
            if (currentFavorites.contains(productId)) {
                repository.removeFavorite(productId)
            } else {
                repository.addFavorite(productId)
            }
        }
    }

    // --- DETAILED MODAL STATE ---
    private val _selectedProductDetails = MutableStateFlow<ShowroomProduct?>(null)
    val selectedProductDetails = _selectedProductDetails.asStateFlow()

    fun showProductDetails(product: ShowroomProduct?) {
        _selectedProductDetails.value = product
    }

    // --- TESTIMONIALS ---
    val testimonials = listOf(
        Testimonial("Ritesh Deshmukh", "Architect, Nagole", "Jai Hanuman was recommended to us for a high-end luxury villa project in Nagole. Bujala Praveen's ceramic expertise is phenomenal. Their selection of huge slab vitrified tiles completely transformed the architectural look of the project. Highly recommended!", 5),
        Testimonial("Ananya Reddy", "Homeowner, Hayathnagar", "The Hayathnagar showroom is massive and beautiful. We bought all our master bathroom fittings and kitchen vitrified tiles from there. The premium quality sanitary collections by Johnson and RAK are pristine. Unmatched service!", 5),
        Testimonial("Surendra Babu", "Builder, Landmark Infra", "Consistently buying tiles and sanitary solutions for our apartment builds since 2015. Extremely competitive pricing, reliable fast delivery, and expert consultations. Bujala's technical knowledge prevents lots of installation errors.", 5)
    )

    private val _activeTestimonialIndex = MutableStateFlow(0)
    val activeTestimonialIndex = _activeTestimonialIndex.asStateFlow()

    fun nextTestimonial() {
        _activeTestimonialIndex.value = (_activeTestimonialIndex.value + 1) % testimonials.size
    }

    fun prevTestimonial() {
        val current = _activeTestimonialIndex.value
        _activeTestimonialIndex.value = if (current == 0) testimonials.size - 1 else current - 1
    }

    // --- REAL RICH SHOWROOM PRODUCTS DATABASE ---
    val products = listOf(
        // Floor Tiles
        ShowroomProduct(
            id = "floor_01",
            name = "Kajaria Royal Marble Slab",
            category = "Floor Tiles",
            brand = "Kajaria",
            specs = "800x1600mm | GVT (Glazed Vitrified) | 9.0mm",
            description = "High-reflective mirror finish premium vitrified marble slab with classic gold and charcoal grey veins. Looks like luxury Italian marble.",
            accentColorHex = "FFFDF0"
        ),
        ShowroomProduct(
            id = "floor_02",
            name = "Johnson Endura Heavy Duty Tile",
            category = "Floor Tiles",
            brand = "Johnson",
            specs = "600x600mm | Industrial Matt | 12.0mm",
            description = "Slip-resistant, scratchproof engineered tile for heavy-footfall areas such as driveways, hallways, and modern boutique offices.",
            accentColorHex = "42454A"
        ),
        // Wall Tiles
        ShowroomProduct(
            id = "wall_01",
            name = "Somany Textured Gold Wall Slabs",
            category = "Wall Tiles",
            brand = "Somany",
            specs = "300x600mm | Ceramic Textured Glossy",
            description = "Subtle golden 3D wave embossing with dual ceramic coating to create an exceptional luxury feature wall in living and reception rooms.",
            accentColorHex = "C5A059"
        ),
        ShowroomProduct(
            id = "wall_02",
            name = "Nitco Statuario Elite",
            category = "Wall Tiles",
            brand = "Nitco",
            specs = "600x1200mm | Polished Glazed Glaze",
            description = "Pure white Statuario finish with delicate light-grey mineral lines designed for modern kitchen backsplashes and master bedrooms.",
            accentColorHex = "F5F5FA"
        ),
        // Vitrified Tiles
        ShowroomProduct(
            id = "vit_01",
            name = "Kajaria Eternity Imperial Wood",
            category = "Vitrified Tiles",
            brand = "Kajaria",
            specs = "200x1200mm | Matte Wooden Plank",
            description = "Vitrified planks with authentic mahogany wood grain texture. Offers the absolute warmth of hardwood with water resistance.",
            accentColorHex = "5C3F24"
        ),
        ShowroomProduct(
            id = "vit_02",
            name = "Somany Maxima Super Glossy Slabs",
            category = "Vitrified Tiles",
            brand = "Somany",
            specs = "1200x2400mm | Double Charged Polyester Gloss",
            description = "Large-format seamless vitrified slab. Gives ultra-luxurious expansive layout to grand living spaces, minimizing joint lines.",
            accentColorHex = "EBEBF5"
        ),
        // Bathroom Tiles
        ShowroomProduct(
            id = "bath_01",
            name = "Kajaria Aqua Gloss Mosaic Series",
            category = "Bathroom Tiles",
            brand = "Kajaria",
            specs = "300x450mm | Aqua Gloss Ceramic",
            description = "Shimmering deep turquoise and teal mosaics. Waterproof, lime-scale resistant, ideal for custom steam chambers and showers.",
            accentColorHex = "1A5F7A"
        ),
        ShowroomProduct(
            id = "bath_02",
            name = "Somany Slip-Shield Matt Floor",
            category = "Bathroom Tiles",
            brand = "Somany",
            specs = "300x300mm | Anti-Slip Shield Surface",
            description = "Certified Slip-Shield technology providing dynamic friction even under soapy wet surfaces. Absolute safety for elderly & kids.",
            accentColorHex = "787A7D"
        ),
        // Kitchen Tiles
        ShowroomProduct(
            id = "kit_01",
            name = "Nitco Bistro Moroccan Mosaic",
            category = "Kitchen Tiles",
            brand = "Nitco",
            specs = "300x300mm | Matte Finish Designer Grout",
            description = "Intricately detailed printed Mediterranean designs. Heat-resistant, grease-proof enamel coating for clean premium cooking walls.",
            accentColorHex = "E8A068"
        ),
        // Sanitary Ware / Commodes
        ShowroomProduct(
            id = "san_01",
            name = "RAK Feeling Rimless Commode",
            category = "Water Closets",
            brand = "RAK Ceramics",
            specs = "Wall-Hung | Matte Slate Grey Finish | Dual-Flush",
            description = "Premium wall-hung rimless water closet with silent soft-close lid, antibacterial protective glaze, and high efficiency water saving.",
            accentColorHex = "2B2D30",
            isGlossy = false
        ),
        ShowroomProduct(
            id = "san_02",
            name = "Johnson Luxe Smart Closet",
            category = "Water Closets",
            brand = "Johnson",
            specs = "Wall-Hung | Pure White Gloss | Rimless",
            description = "Intelligent design bowl featuring modern tornado-jet flush, odor-trap siphonic engineering, and elegant sleek conceal fitting.",
            accentColorHex = "FFFFFF"
        ),
        // Wash Basins
        ShowroomProduct(
            id = "basin_01",
            name = "RAK Luxury Ceramic Console Basin",
            category = "Wash Basins",
            brand = "RAK Ceramics",
            specs = "650x450mm | Counter-Top | Gold Plated Rim",
            description = "Masterpiece statement counter-top basin with handcrafted gold luxury metal borders and scratch-proof ceramic glaze inside.",
            accentColorHex = "D1C7BD"
        ),
        ShowroomProduct(
            id = "basin_02",
            name = "Somany Italian Pedestal Wash Basin",
            category = "Wash Basins",
            brand = "Somany",
            specs = "One-Piece Floor Standing | Alabaster White",
            description = "Majestic floor-standing integrated column basin representing minimalist European hotel styles.",
            accentColorHex = "F9F9F9"
        ),
        // Bathroom Accessories
        ShowroomProduct(
            id = "acc_01",
            name = "RAK Line Dual-Thermostat Shower System",
            category = "Bathroom Accessories",
            brand = "RAK Ceramics",
            specs = "Brushed Gold | Anti-Scald Control",
            description = "Heavy solid brass luxury overhead rainfall shower and handheld stream wand in warm brushed gold. Real-time temperature memory.",
            accentColorHex = "D6B265",
            isGlossy = false
        ),
        ShowroomProduct(
            id = "acc_02",
            name = "Johnson Premium Waterfall Mixer Faucet",
            category = "Bathroom Accessories",
            brand = "Johnson",
            specs = "Counter Deck Mounted | Solid Chrome Brass",
            description = "Super wide waterfall faucet with multi-layered ceramic core disk cartridge preventing any drip leakages.",
            accentColorHex = "E1E5EB"
        )
    )
}

data class Testimonial(
    val name: String,
    val role: String,
    val message: String,
    val rating: Int
)

class JaiHanumanViewModelFactory(private val repository: JaiHanumanRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JaiHanumanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JaiHanumanViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
