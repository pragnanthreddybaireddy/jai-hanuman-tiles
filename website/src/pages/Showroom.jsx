import React, { useState, useMemo } from 'react';
import './Showroom.css';
import { products } from '../data';
import { Search, X, Clock, ChevronDown, ChevronUp, Phone, Eye } from 'lucide-react';
import Room360Viewer from '../components/Room360Viewer';

export default function Showroom() {
  const [searchField, setSearchField] = useState('');
  const [selectedCategoryId, setSelectedCategoryId] = useState('all');
  const [viewerState, setViewerState] = useState({ isOpen: false, product: null });

  const categories = [
    { id: "all", name: "All Items" },
    { id: "Floor Tiles", name: "Floor Tiles" },
    { id: "Wall Tiles", name: "Wall Tiles" },
    { id: "Vitrified Tiles", name: "Vitrified Tiles" },
    { id: "Bathroom Tiles", name: "Bathroom Tiles" },
    { id: "Kitchen Tiles", name: "Kitchen Tiles" },
    { id: "Sanitary Ware", name: "Sanitary Ware" },
    { id: "Wash Basins", name: "Wash Basins" },
    { id: "Water Closets", name: "Water Closets" },
    { id: "Bathroom Accessories", name: "Bathroom Accessories" },
    { id: "Natural Stones", name: "Natural Stones" }
  ];

  const filteredProducts = useMemo(() => {
    return products.filter(prod => {
      const matchCategory = selectedCategoryId === 'all' || prod.category === selectedCategoryId;
      const term = searchField.toLowerCase();
      const matchSearch = prod.name.toLowerCase().includes(term) ||
                          prod.id.toLowerCase().includes(term) ||
                          prod.brand.toLowerCase().includes(term) ||
                          prod.description.toLowerCase().includes(term);
      return matchCategory && matchSearch;
    });
  }, [selectedCategoryId, searchField]);

  const [expandedDetails, setExpandedDetails] = useState({});

  const toggleDetail = (id) => {
    setExpandedDetails(prev => ({ ...prev, [id]: !prev[id] }));
  };

  const handleWhatsAppInquiry = (product) => {
    const msg = `Hello Jai Hanuman Tiles & Sanitary, \nI saw this premium product on your showroom catalog. These brands and materials are required for my project:\n\nProduct: ${product.name}\nCode: ${product.id}\nBrand: ${product.brand}\nSpecs: ${product.specs}\n\nPlease guide me with the quote and availability. Thanks!`;
    window.open(`https://api.whatsapp.com/send?phone=919246351432&text=${encodeURIComponent(msg)}`, '_blank');
  };

  return (
    <div className="showroom-page">
      <div className="catalog-header">
        <div className="container">
          <div className="elegant-header">
            <span className="elegant-category">Luxury Catalogs</span>
            <h2 className="elegant-title">
              <span className="elegant-title-normal">Our Premium </span>
              <span className="elegant-title-italic">Collections</span>
            </h2>
            <p className="elegant-desc">Filter and find direct specifications. Request physical samples or pricing models directly on WhatsApp.</p>
          </div>

          <div className="search-container">
            <span className="search-icon"><Search size={18} /></span>
            <input 
              type="text" 
              placeholder="Search Tiles, Closets, Brands, Finishes..." 
              value={searchField}
              onChange={(e) => setSearchField(e.target.value)}
              className="search-input"
            />
            {searchField && (
              <button className="clear-btn" onClick={() => setSearchField('')}><X size={16} /></button>
            )}
          </div>

          <div className="categories-scroll">
            {categories.map(category => (
              <button 
                key={category.id}
                className={`category-pill ${selectedCategoryId === category.id ? 'active' : ''}`}
                onClick={() => setSelectedCategoryId(category.id)}
              >
                {category.name.toUpperCase()}
              </button>
            ))}
          </div>
        </div>
      </div>

      <div className="container catalog-body">
        {filteredProducts.length === 0 ? (
          <div className="no-results">
            <span className="history-icon"><Clock size={48} /></span>
            <h3>No premium products found</h3>
            <p>Try adjusting your category filter, clearing your search query or contact us for special order materials.</p>
          </div>
        ) : (
          <div className="product-grid">
            {filteredProducts.map(product => {
              const isExpanded = expandedDetails[product.id];
              return (
                <div className="glass-card product-card" key={product.id}>
                  <div className="product-image-container">
                    {product.image ? (
                      <img src={product.image} alt={product.name} className="product-image" />
                    ) : (
                      <div className="procedural-tile" style={{
                        backgroundColor: product.id.includes('VT') ? '#C5A059' : 
                                       product.id.includes('FL') ? '#D4AF37' :
                                       product.id.includes('WL') ? '#8c7648' : '#222'
                      }}>
                        {product.id}
                      </div>
                    )}
                    <div className="brand-badge">{product.brand.toUpperCase()}</div>
                  </div>
                  
                  <div className="product-info">
                    <div className="product-title-row">
                      <h4>{product.name}</h4>
                      <span className="product-code">CODE: {product.id.split('-').pop()}</span>
                    </div>

                    <div className="product-tags">
                      <span className="tag">{product.specs.split('|')[0] || "Standard"}</span>
                      <span className="tag">{product.specs.split('|')[1] || product.category}</span>
                    </div>

                    <p className={`product-desc ${isExpanded ? 'expanded' : ''}`} onClick={() => toggleDetail(product.id)}>
                      {product.description}
                    </p>

                    {isExpanded && (
                      <div className="product-highlights">
                        <span className="highlight-title">FULL SPECIFICATIONS:</span>
                        <div className="highlight-item">
                          <div className="dot"></div>
                          <span>{product.specs}</span>
                        </div>
                      </div>
                    )}

                    <div className="toggle-btn" onClick={() => toggleDetail(product.id)}>
                      <span style={{ display: 'flex', alignItems: 'center', gap: '4px', justifyContent: 'center' }}>
                        {isExpanded ? 'SHOW LESS' : 'SHOW DETAILS'}
                        {isExpanded ? <ChevronUp size={14} /> : <ChevronDown size={14} />}
                      </span>
                    </div>

                    <div className="divider"></div>

                    <div className="product-actions">
                      {product.panoramaImage && (
                        <button 
                          className="luxury-btn secondary" 
                          onClick={() => setViewerState({ isOpen: true, product })}
                          style={{ marginRight: '8px', padding: '0 15px' }}
                          title="View 360 Room"
                        >
                          <Eye size={18} />
                        </button>
                      )}
                      <a href="tel:+919246351432" className="phone-btn"><Phone size={18} /></a>
                      <button className="whatsapp-btn luxury-btn primary" onClick={() => handleWhatsAppInquiry(product)}>
                        <span>SEND INQUIRY</span>
                      </button>
                    </div>
                  </div>
                </div>
              );
            })}
          </div>
        )}
      </div>

      <Room360Viewer 
        isOpen={viewerState.isOpen} 
        onClose={() => setViewerState({ isOpen: false, product: null })} 
        imageSrc={viewerState.product?.panoramaImage} 
        title={viewerState.product?.name}
      />
    </div>
  );
}
