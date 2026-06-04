import React, { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import './Navbar.css';
import { Phone, MessageSquare, Menu, X, CheckCircle2 } from 'lucide-react';

export default function Navbar() {
  const [isScrolled, setIsScrolled] = useState(false);
  const [isMobileOpen, setIsMobileOpen] = useState(false);
  const [showThemeAlert, setShowThemeAlert] = useState(false);
  const [showInfoAlert, setShowInfoAlert] = useState(false);
  const location = useLocation();

  useEffect(() => {
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 10);
    };
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  // Close mobile menu when route changes
  useEffect(() => {
    setIsMobileOpen(false);
  }, [location.pathname]);

  return (
    <>
      <header className={`navbar ${isScrolled ? 'scrolled' : ''}`}>
        <div className="nav-container">
          {/* Brand Section */}
          <Link to="/" className="brand-section">
            <img src="/images/logo.png" alt="Jai Hanuman Logo" className="brand-logo" />
            <div className="brand-name">
              <h1>JAI HANUMAN</h1>
              <span>TILES</span>
            </div>
          </Link>
          
          {/* Main Links */}
          <nav className={`nav-links ${isMobileOpen ? 'open' : ''}`}>
            <div className="nav-item">
              <Link to="/" className={location.pathname === '/' ? 'active' : ''}>HOME GALLERY</Link>
              {location.pathname === '/' && <div className="active-indicator"></div>}
            </div>
            <div className="nav-item">
              <Link to="/showroom" className={location.pathname === '/showroom' ? 'active' : ''}>STONE COLLECTION</Link>
              {location.pathname === '/showroom' && <div className="active-indicator"></div>}
            </div>
            <div className="nav-item">
              <Link to="/founder" className={location.pathname === '/founder' ? 'active' : ''}>THE LEADERSHIP</Link>
              {location.pathname === '/founder' && <div className="active-indicator"></div>}
            </div>
            <div className="nav-item">
              <Link to="/contact" className={location.pathname === '/contact' ? 'active' : ''}>DIGITAL INQUIRY</Link>
              {location.pathname === '/contact' && <div className="active-indicator"></div>}
            </div>
          </nav>

          {/* Right side actions */}
          <div className="nav-actions">
            <a href="tel:+919246351432" className="call-nav-btn">
              <span className="icon"><Phone size={14} /></span>
              <span className="text">CALL US</span>
            </a>
            <a href="https://api.whatsapp.com/send?phone=919246351432" target="_blank" rel="noreferrer" className="whatsapp-nav-btn">
              <span className="icon"><MessageSquare size={14} /></span>
              <span className="text">WHATSAPP</span>
            </a>

            <button className="mobile-toggle" onClick={() => setIsMobileOpen(!isMobileOpen)}>
              {isMobileOpen ? <X size={24} /> : <Menu size={24} />}
            </button>
          </div>
        </div>
      </header>

      {/* Info Dialog */}
      {showInfoAlert && (
        <div className="modal-overlay" onClick={() => setShowInfoAlert(false)}>
          <div className="modal-content glass-card" onClick={e => e.stopPropagation()}>
            <h2 className="modal-title">JAI HANUMAN</h2>
            <div className="modal-body">
              <p className="modal-subtitle">Premium Tiles, Sanitary Ware & Ceramic Custom Solutions.</p>
              <ul className="modal-list">
                <li>• Flagship Nagole branch opened in 2012.</li>
                <li>• Hayathnagar branch launched in 2018.</li>
                <li>• Core technical leadership by Bujala Praveen (Ceramics Specialist).</li>
              </ul>
            </div>
            <div className="modal-footer">
              <button className="text-btn" onClick={() => setShowInfoAlert(false)}>CLOSE</button>
            </div>
          </div>
        </div>
      )}

      {/* Theme Dialog (Mocked for parity) */}
      {showThemeAlert && (
        <div className="modal-overlay" onClick={() => setShowThemeAlert(false)}>
          <div className="modal-content glass-card" onClick={e => e.stopPropagation()}>
            <h2 className="modal-title" style={{ letterSpacing: '1px' }}>SELECT PALETTE</h2>
            <div className="modal-body">
              <p className="modal-desc" style={{ marginBottom: '16px', fontSize: '12px', color: 'rgba(255,255,255,0.8)' }}>
                Choose an exquisite visual theme to view Jai Hanuman Ceramic styles in your preferred ambiance:
              </p>
              
              <div className="theme-option active">
                <div className="theme-circle" style={{ backgroundColor: '#0F0F0F' }}>
                  <div className="theme-inner-circle" style={{ backgroundColor: '#C5A059' }}></div>
                </div>
                <div className="theme-info">
                  <h4>ONYX (ACTIVE)</h4>
                  <p>Deep charcoal with classic gold.</p>
                </div>
                <div className="theme-check"><CheckCircle2 size={16} color="#C5A059" /></div>
              </div>
              
            </div>
            <div className="modal-footer">
              <button className="text-btn" onClick={() => setShowThemeAlert(false)}>DONE</button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
