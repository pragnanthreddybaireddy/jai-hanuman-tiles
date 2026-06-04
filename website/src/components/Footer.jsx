import React from 'react';
import { Link } from 'react-router-dom';
import { MapPin, Phone, Mail, Clock } from 'lucide-react';
import './Footer.css';

export default function Footer() {
  return (
    <footer className="footer glass">
      <div className="container footer-content">
        <div className="footer-col">
          <h3 className="logo">Jai Hanuman <span>Tiles</span></h3>
          <p className="footer-desc">Premium tiles, flawless sanitary ware, and architectural finish solutions in Hyderabad. Elegance defined.</p>
          <div className="social-links">
            <a href="#">IG</a>
          </div>
        </div>
        
        <div className="footer-col">
          <h4>Contact Info</h4>
          <ul className="contact-list">
            <li>
              <span className="footer-icon"><MapPin size={16} /></span>
              <span>Nagole Cross Roads, ORR, Hyderabad</span>
            </li>
            <li>
              <span className="footer-icon"><Phone size={16} /></span>
              <span>+91 92463 51432</span>
            </li>
            <li>
              <span className="footer-icon"><Mail size={16} /></span>
              <span>jaihanumantiles@gmail.com</span>
            </li>
            <li>
              <span className="footer-icon"><Clock size={16} /></span>
              <span>Mon-Sat: 09:30 AM – 08:30 PM</span>
            </li>
          </ul>
        </div>
        
        <div className="footer-col">
          <h4>Business Hours</h4>
          <ul className="hours-list">
            <li><span>Monday - Saturday:</span> 9:30 AM - 8:30 PM</li>
            <li><span>Sunday:</span> 10:00 AM - 2:00 PM</li>
          </ul>
        </div>
      </div>
      <div className="footer-bottom">
        <p>&copy; {new Date().getFullYear()} Jai Hanuman Tiles & Sanitary. All rights reserved.</p>
      </div>
    </footer>
  );
}
