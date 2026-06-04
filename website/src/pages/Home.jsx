import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Home.css';
import { products } from '../data';
import { Check, MapPin, Star, ExternalLink, ArrowLeft, ArrowRight } from 'lucide-react';

export default function Home() {
  const navigate = useNavigate();
  
  const [counters, setCounters] = useState({ years: 0, branches: 0, customers: 0 });
  
  useEffect(() => {
    // Simple counter animation
    const timer = setTimeout(() => {
      setCounters({ years: 20, branches: 2, customers: 6 });
    }, 500);
    return () => clearTimeout(timer);
  }, []);

  const brands = [
    { name: 'Kajaria', desc: "India's No. 1 Tile Manufacturer" },
    { name: 'Johnson', desc: "Legacy brand delivering international quality" },
    { name: 'Somany', desc: "Avant-garde ceramic designs" },
    { name: 'Nitco', desc: "Luxurious natural looks, designer tiles" },
    { name: 'RAK Ceramics', desc: "Global powerhouse offering exquisite collections" }
  ];

  const branches = [
    {
      id: "nagole",
      name: "Nagole Branch",
      year: "2012",
      desc: "The flagship branch located at Nagole has been successfully serving customers since 2012 and remains the foundation of the Jai Hanuman Tiles & Sanitary legacy.",
      features: ["Premium Tiles", "Sanitary Ware", "Bathroom Solutions", "Expert Consultation"],
      address: "Jai Hanuman Tiles & Sanitary – Best Tiles & Sanitary in Nagole Hyderabad",
      isFlagship: true,
      map: "Jai Hanuman Tiles & Sanitary – Best Tiles & Sanitary in Nagole Hyderabad"
    },
    {
      id: "hayathnagar",
      name: "Hayathnagar Branch",
      year: "2018",
      desc: "Continuing the brand's legacy, the Hayathnagar branch was launched in 2018 to bring premium tile and sanitary solutions closer to customers across Hyderabad.",
      features: ["Complete Tile Range", "Sanitary Products", "Builder Solutions", "Modern Collections"],
      address: "Vijayawada Highway, Near Word & Deed School, Hayathnagar, Hyderabad, Telangana",
      isFlagship: false,
      map: "Jai Hanuman Tiles & Sanitary Hayathnagar Hyderabad"
    }
  ];

  const testimonials = [
    { initial: "A", name: "Anirudh Reddy", role: "Homeowner • Nagole", rating: 5, text: "Fabulous collection of premium large-format slabs. Mr. Praveen suggested Carrara glazed vitrified tiles for our hall which looks exceptionally luxurious. Outstanding service!" },
    { initial: "M", name: "Meenakshi K.", role: "Architect • Secunderabad", rating: 5, text: "As an designer, I look for precision, sizing variety and trusted brands. Jai Hanuman is our go-to showroom in East Hyderabad. The sanitary ware display is top-tier." },
    { initial: "S", name: "Satish Kumar", role: "Builder & Developer • Hayathnagar", rating: 5, text: "We have been sourcing vitrified tiles and water closets for our residential projects. Best competitive pricing, fast delivery times, and direct warranty support." },
    { initial: "V", name: "Venkatesh Goud", role: "Villa Owner • Alkapuri", rating: 5, text: "Excellent bathroom layout consultations. Visited their Hayathnagar branch, bought their whole wellness set with premium wash-basins. True guidance!" }
  ];

  const [activeTestimonial, setActiveTestimonial] = useState(0);

  return (
    <div className="home-page">
      {/* 1. HERO SECTION */}
      <section className="hero-section">
        <div className="hero-radial-bg"></div>
        <div className="hero-content container">
          <div className="hero-editorial">
            <span className="est">EST. 2012</span>
            <span className="dot"></span>
            <span className="city">HYDERABAD</span>
          </div>
          <h1 className="hero-title">
            <span className="line1">Building</span><br/>
            <span className="line2 italic">Beautiful</span><br/>
            <span className="line3">Spaces.</span>
          </h1>
          <p className="hero-subtitle">
            Hyderabad's trusted destination for premium tiles, sanitary ware, and bathroom solutions.
          </p>
          <div className="hero-buttons">
            <button className="luxury-btn primary" onClick={() => navigate('/showroom')}>Explore range</button>
            <button className="luxury-btn secondary" onClick={() => navigate('/contact')}>Contact Us</button>
          </div>
        </div>
      </section>

      {/* 2. COUNTER SECTION */}
      <section className="counter-section">
        <div className="container counter-grid">
          <div className="counter-item">
            <h2>{counters.years}+</h2>
            <p>YEARS EXPERIENCE</p>
          </div>
          <div className="counter-item">
            <h2>{counters.branches}</h2>
            <p>SHOWROOMS</p>
          </div>
          <div className="counter-item">
            <h2>{counters.customers} Lakhs+</h2>
            <p>HAPPY CLIENTS</p>
          </div>
          <div className="counter-item">
            <h2>Top</h2>
            <p>BRANDS</p>
          </div>
        </div>
      </section>

      {/* 3. ABOUT COMPANY EDITORIAL */}
      <section className="about-editorial-section container">
        <div className="elegant-header">
          <span className="elegant-category">Our Legacy</span>
          <h2 className="elegant-title">
            <span className="elegant-title-normal">About </span>
            <span className="elegant-title-italic">Jai Hanuman</span>
          </h2>
        </div>
        <div className="editorial-text">
          <p>Led by a founder with over 20+ years of deep industrial and retail experience (a Ceramic Graduate from Govt Institute of Ceramic Technology, Goodur), Jai Hanuman Tiles & Sanitary is one of Hyderabad's trusted destinations for premium tiles and sanitary solutions. Established in 2012 at Nagole, the company quickly earned a reputation for quality products, expert guidance, and customer satisfaction.</p>
          <p>With growing customer trust and increasing demand, the second branch was established in Hayathnagar in 2018, continuing the legacy and commitment to excellence.</p>
          <p>Today, Jai Hanuman Tiles & Sanitary serves homeowners, builders, architects, and interior designers with a wide range of premium products from India's leading brands.</p>
        </div>
      </section>

      {/* 4. FEATURED COLLECTIONS */}
      <section className="featured-section container">
        <div className="elegant-header">
          <span className="elegant-category">Showcase</span>
          <h2 className="elegant-title">
            <span className="elegant-title-normal">Featured </span>
            <span className="elegant-title-italic">Collections</span>
          </h2>
          <p className="elegant-desc">A glimpse of our premium handpicked tiles.</p>
        </div>
        <div className="featured-grid">
          {products.slice(0, 4).map(product => (
            <div className="glass-card featured-card" key={product.id}>
              <div className="featured-image">
                <img src={product.image} alt={product.name} />
                <div className="brand-badge">{product.brand.toUpperCase()}</div>
              </div>
              <div className="featured-info">
                <h4>{product.name}</h4>
                <p>{product.specs.split('|')[0]}</p>
                <button className="luxury-btn primary btn-small" onClick={() => navigate('/showroom')}>VIEW CATALOG</button>
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* 5. BRANCHES SHOWROOM SECTION */}
      <section className="branches-section">
        <div className="container">
          <div className="elegant-header">
            <span className="elegant-category">Our Locations</span>
            <h2 className="elegant-title">
              <span className="elegant-title-normal">Luxury </span>
              <span className="elegant-title-italic">Showrooms</span>
            </h2>
            <p className="elegant-desc">Visit our physical branches to experience tactile premium ceramic finishes, textures and full sanitary ranges first-hand.</p>
          </div>

          <div className="branches-list">
            {branches.map(branch => (
              <div className="glass-card branch-card" key={branch.id}>
                <div className="branch-header">
                  <div>
                    <h3>{branch.name}</h3>
                    <p className="est-year">Established: {branch.year}</p>
                  </div>
                  {branch.isFlagship && <span className="flagship-badge">FLAGSHIP</span>}
                </div>
                <p className="branch-desc">{branch.desc}</p>
                <div className="branch-features">
                  {branch.features.map((f, i) => (
                    <div className="feature-item" key={i}>
                      <span className="check"><Check size={14} color="#C5A059" /></span>
                      <span>{f}</span>
                    </div>
                  ))}
                </div>
                <div className="branch-divider"></div>
                <div className="branch-footer">
                  <div className="branch-address">
                    <span className="pin"><MapPin size={16} /></span>
                    <span>{branch.address}</span>
                  </div>
                  <a href={`https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(branch.map)}`} target="_blank" rel="noreferrer" className="luxury-btn primary btn-small">VIEW LOCATION</a>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* 6. TESTIMONIAL SLIDER SECTION */}
      <section className="testimonials-section container">
        <div className="elegant-header">
          <span className="elegant-category">Patron Reviews</span>
          <h2 className="elegant-title">
            <span className="elegant-title-normal">Client </span>
            <span className="elegant-title-italic">Testimonials</span>
          </h2>
          <p className="elegant-desc">Read of the satisfaction experienced by decorators, builders, and elite homeowners.</p>
        </div>

        <div className="glass-card testimonial-card">
          <div className="testimonial-header">
            <div className="reviewer-info">
              <div className="reviewer-avatar">{testimonials[activeTestimonial].initial}</div>
              <div className="reviewer-details">
                <h4>{testimonials[activeTestimonial].name}</h4>
                <p>{testimonials[activeTestimonial].role}</p>
              </div>
            </div>
            <div className="reviewer-rating">
              {[...Array(testimonials[activeTestimonial].rating)].map((_, i) => <Star key={i} size={16} fill="#C5A059" color="#C5A059" />)}
            </div>
          </div>
          <p className="testimonial-text">"{testimonials[activeTestimonial].text}"</p>
          
          <div className="testimonial-controls">
            <div className="dots">
              {testimonials.map((_, idx) => (
                <div 
                  key={idx} 
                  className={`dot ${idx === activeTestimonial ? 'active' : ''}`}
                  onClick={() => setActiveTestimonial(idx)}
                ></div>
              ))}
            </div>
            <div className="arrows">
              <button onClick={() => setActiveTestimonial(prev => prev > 0 ? prev - 1 : testimonials.length - 1)}><ArrowLeft size={20} /></button>
              <button onClick={() => setActiveTestimonial(prev => (prev + 1) % testimonials.length)}><ArrowRight size={20} /></button>
            </div>
          </div>
        </div>
      </section>

      {/* 7. BRANDS LOGO SECTION */}
      <section className="brands-section">
        <h4 className="brands-title">TRUSTED BRANDS WE DEAL WITH</h4>
        <div className="brands-scroll">
          {[
            {
              name: "Kajaria",
              desc: "India's No. 1 Tile Company.",
              logo: "images/brands/kajaria.svg",
              url: "https://www.kajariaceramics.com/"
            },
            {
              name: "Johnson",
              desc: "Heritage of Quality since 1958.",
              logo: "images/brands/johnson.svg",
              url: "https://hrjohnsonindia.com/"
            },
            {
              name: "Somany",
              desc: "Innovation & Design Leadership.",
              logo: "images/brands/somany.svg",
              url: "https://www.somanyceramics.com/"
            },
            {
              name: "Nitco",
              desc: "Premium Surface Solutions.",
              logo: "images/brands/nitco.svg",
              url: "https://www.nitco.in/"
            },
            {
              name: "RAK Ceramics",
              desc: "Global lifestyle ceramic brand.",
              logo: "images/brands/rak.svg",
              url: "https://www.rakceramics.com/india/en-in"
            }
          ].map((brand, i) => (
            <div className="brand-card glass-card" key={i}>
              <div className="brand-logo-container">
                <img src={brand.logo} alt={brand.name} />
              </div>
              <div className="brand-info">
                <h5 className="brand-name">{brand.name.toUpperCase()}</h5>
                <p className="brand-desc">{brand.desc}</p>
                <a href={brand.url} target="_blank" rel="noreferrer" className="brand-link" style={{ display: 'flex', alignItems: 'center', gap: '4px' }}>OFFICIAL SITE <ExternalLink size={12} /></a>
              </div>
            </div>))}
        </div>
      </section>
    </div>
  );
}
