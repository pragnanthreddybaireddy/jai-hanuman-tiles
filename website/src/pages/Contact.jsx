import React, { useState } from 'react';
import './Contact.css';
import { Check, Phone } from 'lucide-react';

export default function Contact() {
  const [clientName, setClientName] = useState('');
  const [clientPhone, setClientPhone] = useState('');
  const [clientEmail, setClientEmail] = useState('');
  const [productInterest, setProductInterest] = useState('Vitrified Tiles');
  const [clientMessage, setClientMessage] = useState('');
  
  const [submissionSuccess, setSubmissionSuccess] = useState(false);
  const [submissionError, setSubmissionError] = useState(null);
  
  const interests = [
    "Vitrified Tiles", "Premium Slabs", "Floor Tiles", "Wall Tiles", 
    "Water Closets", "Wash Basins", "Designer Fittings", "Other Consultation"
  ];

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!clientName.trim()) { setSubmissionError("Please enter your name."); return; }
    if (!clientPhone.trim() || clientPhone.length < 10) { setSubmissionError("Enter a valid 10-digit mobile number."); return; }
    
    setSubmissionError(null);
    setSubmissionSuccess(true);
  };

  const handleWhatsAppSend = () => {
    const msg = `Hello Jai Hanuman Tiles & Sanitary, \nRegistered Inquiry:\nName: ${clientName}\nPhone: ${clientPhone}\nEmail: ${clientEmail || "N/A"}\nCategory Selected: ${productInterest}\nMessage: ${clientMessage}\n\nPlease send me catalogues. These brands and materials are required for my project. Thanks!`;
    window.open(`https://api.whatsapp.com/send?phone=919246351432&text=${encodeURIComponent(msg)}`, '_blank');
  };

  return (
    <div className="contact-page">
      {/* 1. BRAND HERO FOR INQUIRY */}
      <section className="inquiry-hero">
        <div className="elegant-header">
          <span className="elegant-category">Virtual Showroom</span>
          <h2 className="elegant-title">
            <span className="elegant-title-normal">Connect </span>
            <span className="elegant-title-italic">Inquire Now</span>
          </h2>
          <p className="elegant-desc">Have custom construction requirements? Fill the luxury register model below. Our showroom managers will reach out.</p>
        </div>
      </section>

      {/* 2. FORM BODY */}
      <section className="form-section container">
        {submissionSuccess ? (
          <div className="glass-card success-card">
            <div className="success-icon-container"><Check size={32} color="#000" /></div>
            <h3 className="success-title">INQUIRY LOGGED SECURELY</h3>
            <p className="success-desc">Thank you for registering at Jai Hanuman Tiles. We have saved your preference for '{productInterest}'. Direct draft copy created. You can also send the manifest via WhatsApp instantly below.</p>
            <div className="divider"></div>
            <div className="success-buttons">
              <button className="luxury-btn secondary" onClick={() => setSubmissionSuccess(false)}>WIPE FORM</button>
              <button className="whatsapp-btn luxury-btn primary" onClick={handleWhatsAppSend}>
                <span>WHATSAPP SEND</span>
              </button>
            </div>
          </div>
        ) : (
          <form className="inquiry-form" onSubmit={handleSubmit}>
            <span className="form-label">REGISTER INTEREST</span>
            
            <input type="text" placeholder="Your full name *" value={clientName} onChange={e => setClientName(e.target.value)} className="form-input" />
            <input type="tel" placeholder="Your mobile number *" value={clientPhone} onChange={e => setClientPhone(e.target.value)} className="form-input" />
            <input type="email" placeholder="Your Email Address (Optional)" value={clientEmail} onChange={e => setClientEmail(e.target.value)} className="form-input" />
            
            <select value={productInterest} onChange={e => setProductInterest(e.target.value)} className="form-input form-select">
              {interests.map(i => <option key={i} value={i}>{i}</option>)}
            </select>
            
            <textarea placeholder="Message / Specific dimension requirements" value={clientMessage} onChange={e => setClientMessage(e.target.value)} className="form-input form-textarea"></textarea>
            
            {submissionError && <p className="error-text">{submissionError}</p>}
            
            <button type="submit" className="luxury-btn primary submit-btn">REGISTER ONLINE REQUEST</button>
          </form>
        )}
      </section>

      {/* 3. PHYSICAL SHOWROOM HOURS CARD */}
      <section className="hours-section container">
        <div className="elegant-header">
          <span className="elegant-category">Visit Details</span>
          <h2 className="elegant-title">
            <span className="elegant-title-normal">Showroom </span>
            <span className="elegant-title-italic">Operating Hours</span>
          </h2>
        </div>

        <div className="glass-card hours-card">
          <div className="timing-row">
            <span>Monday - Saturday</span>
            <span className="time">09:30 AM – 08:30 PM</span>
          </div>
          <div className="timing-row">
            <span>Sunday</span>
            <span className="time">10:30 AM – 05:30 PM (Nagole Flagship)</span>
          </div>
          <div className="divider"></div>
          <a href="tel:+919246351432" className="direct-support">
            <span className="support-icon"><Phone size={20} /></span>
            <span>Direct Support: +91 92463 51432</span>
          </a>
        </div>
      </section>
    </div>
  );
}
