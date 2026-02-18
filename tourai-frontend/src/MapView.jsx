import React from 'react'
import { MapContainer, TileLayer, Marker, Polyline, Popup } from 'react-leaflet'
import 'leaflet/dist/leaflet.css'
import L from 'leaflet'

// fix default marker icon path (Leaflet + Vite)
delete L.Icon.Default.prototype._getIconUrl
L.Icon.Default.mergeOptions({
  iconRetinaUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon-2x.png',
  iconUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png',
  shadowUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png',
})

export default function MapView({ pois = [] }) {
  const center = pois.length ? [pois[0].lat || pois[0].latitude, pois[0].lon || pois[0].longitude] : [-23.304, -51.162]

  return (
    <div className="map">
      <MapContainer center={center} zoom={14} style={{ height: '70vh', width: '100%' }}>
        <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"/>
        {pois.map((p, i) => (
          <Marker key={i} position={[p.lat || p.latitude, p.lon || p.longitude]}>
            <Popup>
              <b>{p.name}</b><br/>{p.description || ''}
            </Popup>
          </Marker>
        ))}
        {pois.length > 1 && <Polyline positions={pois.map(p => [p.lat || p.latitude, p.lon || p.longitude])} />}
      </MapContainer>
    </div>
  )
}
