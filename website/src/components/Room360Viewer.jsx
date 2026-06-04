import React from 'react';
import { ReactPhotoSphereViewer } from 'react-photo-sphere-viewer';
import { X } from 'lucide-react';
import './Room360Viewer.css';

export default function Room360Viewer({ isOpen, onClose, imageSrc, title }) {
  if (!isOpen) return null;

  return (
    <div className="viewer-overlay">
      <div className="viewer-container">
        <div className="viewer-header">
          <h3>360° Room View: {title}</h3>
          <button className="close-btn" onClick={onClose}><X size={24} /></button>
        </div>
        <div className="viewer-content">
          <ReactPhotoSphereViewer
            src={imageSrc}
            height={"100%"}
            width={"100%"}
            littlePlanet={false}
            navbar={['zoom', 'caption', 'fullscreen']}
          />
        </div>
      </div>
    </div>
  );
}
