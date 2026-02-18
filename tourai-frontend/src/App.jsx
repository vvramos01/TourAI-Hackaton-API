import React, { useState } from 'react'
import MapView from './MapView'
import ControlPanel from './ControlPanel'
import { generateRoute } from './Api'

export default function App() {
  const [pois, setPois] = useState([])

  async function onGenerate(prefs, lat, lon, count) {
    try {
      const data = await generateRoute(prefs, lat, lon, count)
      setPois(data)
    } catch (err) {
      alert('Erro ao gerar rota: ' + (err.message || err))
    }
  }

  return (
    <div className="app">
      <h1>TourAI — MVP</h1>
      <div className="layout">
        <ControlPanel onGenerate={onGenerate} />
        <MapView pois={pois} />
      </div>
    </div>
  )
}
