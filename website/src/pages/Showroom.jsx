import React, { useState, useMemo } from 'react';
import './Showroom.css';
import { products } from '../data';
import { Search, X, Clock, ChevronDown, ChevronUp, Phone, Eye } from 'lucide-react';
import Room360Viewer from '../components/Room360Viewer';

export default function Showroom() {
  const [searchField, setSearchField] = useState('');
  const [selectedCategoryId, setSelectedCategoryId] = useState('all');
  const [viewerState, setViewerState] = useState({ isOpen: false, product: null });

  const sections = [
    {
      id: "Stones",
      title: "Natural Stones & Marble",
      subtitle: "Italian Statuario Marble, Natural Granite Slabs & Calacatta Quartz with 360° VR Previews",
      icon: "🏛️",
      tag: "LUXURY STONES"
    },
    {
      id: "Tiles",
      title: "Floor & Vitrified Tiles",
      subtitle: "Large-Format Glazed Vitrified Slabs, Double-Charged Tiles & Heavy-Duty Anti-Skid Planks",
      icon: "🏢",
      tag: "PREMIUM TILES"
    },
    {
      id: "Walls",
      title: "Wall Tiles & Elevation Slabs",
      subtitle: "3D Textured Wave Embossing, Statuario Backsplashes & Protective Aqua Mosaics",
      icon: "🧱",
      tag: "WALL SLABS"
    },
    {
      id: "Sanitary",
      title: "Sanitary Ware & Bath Solutions",
      subtitle: "Rimless Wall-Hung Closets, Handcrafted Console Basins & Brushed Gold Showers",
      icon: "🚿",
      tag: "SANITARY WARE"
    }
  ];

  const categories = [
    { id: "all", name: "All Collections", icon: "✨" },
    { id: "Stones", name: "Stones & Marble", icon: "🏛️" },
    { id: "Tiles", name: "Floor Tiles", icon: "🏢" },
    { id: "Walls", name: "Wall Slabs", icon: "🧱" },
    { id: "Sanitary", name: "Sanitary Ware", icon: "🚿" }
  ];

  const filteredProducts = useMemo(() => {
    return products.filter(prod => {
      const matchCategory = selectedCategoryId === 'all' || prod.category === selectedCategoryId;
      const term = searchField.toLowerCase();
      const matchSearch = prod.name.toLowerCase().includes(term) ||
                          prod.id.toLowerCase().includes(term) ||
                          prod.brand.toLowerCase().includes(term) ||
                          prod.specs.toLowerCase().includes(term) ||
                          prod.description.toLowerCase().includes(term);
      return matchCategory && matchSearch;
    });
  }, [selectedCategoryId, searchField]);

  const [expandedDetails, setExpandedDetails] = useState({});

  const toggleDetail = (id) => {
    setExpandedDetails(prev => ({ ...prev, [id]: !prev[id] }));
  };

  const handleWhatsAppInquiry = (product) => {
    const msg = `Hello Jai Hanuman Tiles & Sanitary, \nI saw this premium product on your showroom catalog. These brands and materials are required for my project:\n\nProduct: ${product.name}\nCategory: ${product.category} (${product.subCategory || ''})\nCode: ${product.id}\nBrand: ${product.brand}\nSpecs: ${product.specs}\n\nPlease guide me with the quote and availability. Thanks!`;
    window.open(`https://api.whatsapp.com/send?phone=919246351432&text=${encodeURIComponent(msg)}`, '_blank');
  };

  const activeSections = selectedCategoryId === 'all' 
    ? sections 
    : sections.filter(s => s.id === selectedCategoryId);

  return (
    <div className="showroom-page">
      <div className="catalog-header">
        <div className="container">
          <div className="catalog-header-top">
            <div className="elegant-header catalog-header-text">
              <span className="elegant-category">Direct Manufacturer Dealership</span>
              <h2 className="elegant-title">
                <span className="elegant-title-normal">Our Premium </span>
                <span className="elegant-title-italic">Collections</span>
              </h2>
              <p className="elegant-desc">Explore luxury natural stones, Italian marble finishes, large-format glazed vitrified tiles, and designer sanitary ware. Filter specifications or request physical samples on WhatsApp.</p>
              <div className="section-header-features">
                <span className="feature-pill">✓ Kajaria • Somany • Johnson • Nitco</span>
                <span className="feature-pill">✓ Nagole & Hayathnagar Showrooms</span>
                <span className="feature-pill">✓ 100% Genuine Certified Quality</span>
              </div>
            </div>

            <div className="section-prominent-logo-box catalog-right-logo">
              <img 
                src="images/logo.png" 
                alt="Jai Hanuman Tiles & Sanitary" 
                className="section-prominent-logo-img" 
              />
              <div className="section-logo-meta">
                <span className="meta-brand-title">JAI HANUMAN</span>
                <span className="meta-sub">TILES & SANITARY</span>
              </div>
            </div>
          </div>

          <div className="search-container">
            <span className="search-icon"><Search size={18} /></span>
            <input 
              type="text" 
              placeholder="Search Stones, Vitrified Tiles, Wall Slabs, Sanitary..." 
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
                <span className="pill-icon">{category.icon}</span>
                <span>{category.name.toUpperCase()}</span>
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
          <div className="catalog-sections-wrapper">
            {activeSections.map(section => {
              const sectionItems = filteredProducts.filter(p => p.category === section.id);
              if (sectionItems.length === 0) return null;

              return (
                <div className="catalog-section-group" key={section.id}>
                  <div className="section-divider-header">
                    <div className="section-title-wrap">
                      <span className="section-tag-pill">{section.icon} {section.tag}</span>
                      <h3 className="section-title">{section.title}</h3>
                      <p className="section-subtitle">{section.subtitle}</p>
                    </div>
                    <div className="section-count-badge">
                      <span className="count-num">{sectionItems.length}</span>
                      <span className="count-text">Items with 360° VR</span>
                    </div>
                  </div>

                  <div className="product-grid">
                    {sectionItems.map(product => {
                      const isExpanded = expandedDetails[product.id];
                      return (
                        <div className="glass-card product-card" key={product.id}>
                          <div className="product-image-container">
                            <img src={product.image} alt={product.name} className="product-image" />
                            <div className="brand-badge">{product.brand.toUpperCase()}</div>
                            
                            {/* Prominent 360 VR Badge on the image */}
                            {product.panoramaImage && (
                              <button 
                                className="product-vr-floating-badge"
                                onClick={() => setViewerState({ isOpen: true, product })}
                                title="Launch 360° Virtual Reality Room View"
                              >
                                <span className="vr-pulse-dot"></span>
                                <Eye size={13} />
                                <span>360° VR VIEW</span>
                              </button>
                            )}
                          </div>
                          
                          <div className="product-info">
                            <div className="product-title-row">
                              <h4>{product.name}</h4>
                              <span className="product-code">CODE: {product.id.split('-').pop()}</span>
                            </div>

                            <div className="product-tags">
                              <span className="tag highlight">{product.subCategory || product.category}</span>
                              <span className="tag">{product.specs.split('|')[0] || "Standard"}</span>
                              <span className="tag">{product.specs.split('|')[1] || "Polished"}</span>
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
                                <div className="highlight-item">
                                  <div className="dot"></div>
                                  <span>360° VR Simulation Available for Instant Room Experience</span>
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
                                  className="luxury-btn vr-action-btn" 
                                  onClick={() => setViewerState({ isOpen: true, product })}
                                  title="Open 360° Virtual Reality Room View"
                                >
                                  <Eye size={16} />
                                  <span>360° ROOM</span>
                                </button>
                              )}
                              <a href="tel:+919246351432" className="phone-btn" title="Call Showroom"><Phone size={16} /></a>
                              <button className="whatsapp-btn luxury-btn primary" onClick={() => handleWhatsAppInquiry(product)}>
                                <span>INQUIRE</span>
                              </button>
                            </div>
                          </div>
                        </div>
                      );
                    })}
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
