import React from 'react';
import './Founder.css';
import { Star, CheckCircle2 } from 'lucide-react';

export default function Founder() {
  const timeline = [
    { year: "Pre-2002", title: "Ceramics Graduation", desc: "Graduated in Ceramics from the prestigious Govt Institute of Ceramic Technology in Goodur, mastering the scientific fundamentals of the trade." },
    { year: "2002-2012", title: "Industrial Ceramic Experience", desc: "Accumulated a decade of hands-on industrial experience in the ceramics sector, acquiring deep technical knowledge before venturing into retail." },
    { year: "2012", title: "Flagship Opening at Nagole", desc: "Mr. Bujala Praveen Kumar Reddy ventured into premium ceramics retailing with the core flagship branch at Nagole Crossroads, building associations with leading brands like Kajaria and Johnson." },
    { year: "2015", title: "Expansion of Builder Network", desc: "Established deep ties with Hyderabad's elite architects and builders. Sourced huge double-charged vitrified floor tiles & custom water closets for large-scale premium developments." },
    { year: "2018", title: "Launch of Hayathnagar Store", desc: "To serve the burgeoning urban demands in Eastern Hyderabad, the second showroom was launched in Hayathnagar, creating a massive flagship space showcasing world-class ceramics." },
    { year: "2021", title: "Digitalization & Premium Integration", desc: "Pioneered digitized custom consultation mockups, allowing house owners to pre-conceptualize their bathroom suites before final procurement." },
    { year: "2026", title: "A Legacy of Trust", desc: "Continuing to lead the market with 20+ years of combined industrial and retail experience, offering exceptional technical advice and premium manufacturer warranties." }
  ];

  return (
    <div className="founder-page">
      {/* 1. BRAND HERO FOR FOUNDER */}
      <section className="founder-hero">
        <div className="founder-hero-content">
          <div className="founder-icon-container">
            <span className="founder-icon"><Star size={24} color="#C5A059" fill="#C5A059" /></span>
          </div>
          <div className="founder-titles">
            <h1 className="founder-name">BUJALA PRAVEEN KUMAR REDDY</h1>
            <h2 className="founder-role">Founder & Ceramic Specialist (20+ Years Experience)</h2>
          </div>
          <p className="founder-quote">
            "Exquisite spaces start with premium foundations. At Jai Hanuman, we don't just supply tiles—we consult on the science, thickness, and slip-resistance that keep your design looking flawless for generations."
          </p>
        </div>
      </section>

      {/* 2. TIMELINE SECTION */}
      <section className="timeline-section container">
        <div className="elegant-header">
          <span className="elegant-category">Our Story</span>
          <h2 className="elegant-title">
            <span className="elegant-title-normal">Historical </span>
            <span className="elegant-title-italic">Milestones</span>
          </h2>
          <p className="elegant-desc">Follow our continuous trajectory of excellence, trust, and premium sanitary craftsmanship since 2012.</p>
        </div>

        <div className="timeline-container">
          {timeline.map((event, index) => (
            <div className="timeline-item" key={index}>
              <div className="timeline-visual">
                <div className="timeline-dot"><div className="inner-dot"></div></div>
                {index < timeline.length - 1 && <div className="timeline-line"></div>}
              </div>
              <div className="timeline-content">
                <h3 className="timeline-year">{event.year}</h3>
                <h4 className="timeline-event-title">{event.title}</h4>
                <p className="timeline-event-desc">{event.desc}</p>
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* 3. SECTOR EXPERTISE CARD */}
      <section className="expertise-section container">
        <div className="elegant-header">
          <span className="elegant-category">Leadership Pillars</span>
          <h2 className="elegant-title">
            <span className="elegant-title-normal">Strategic </span>
            <span className="elegant-title-italic">Expertise</span>
          </h2>
        </div>

        <div className="expertise-list">
          <div className="glass-card expertise-card">
            <div className="expertise-icon"><CheckCircle2 size={24} color="#C5A059" /></div>
            <div className="expertise-text">
              <h4>True Technical Consulting</h4>
              <p>Our founder has technical expertise regarding clay density ratios, glaze water-absorption scores and thermal stresses.</p>
            </div>
          </div>
          <div className="glass-card expertise-card">
            <div className="expertise-icon"><CheckCircle2 size={24} color="#C5A059" /></div>
            <div className="expertise-text">
              <h4>Direct Brand Partnership</h4>
              <p>Direct procurement channels with industry leaders like Kajaria, Johnson, and RAK resulting in absolute transparent premium pricing.</p>
            </div>
          </div>
          <div className="glass-card expertise-card">
            <div className="expertise-icon"><CheckCircle2 size={24} color="#C5A059" /></div>
            <div className="expertise-text">
              <h4>Custom Suite Consultation</h4>
              <p>Curated designs for bungalows, high-rises, and corporate towers matching visual trends, space and budgets.</p>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}
