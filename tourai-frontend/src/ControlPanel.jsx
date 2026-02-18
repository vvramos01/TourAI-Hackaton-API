import React, { useState } from 'react'

export default function ControlPanel({ onGenerate }) {
  const [prefs, setPrefs] = useState(['cultura'])
  const [count, setCount] = useState(4)

  function toggle(cat) {
    setPrefs(prev => prev.includes(cat) ? prev.filter(p => p!==cat) : [...prev, cat])
  }

  return (
    <div className="panel">
      <h3>Preferências</h3>
      <div>
        <label><input type="checkbox" checked={prefs.includes('cultura')} onChange={() => toggle('cultura')} /> Cultura</label>
        <label><input type="checkbox" checked={prefs.includes('gastronomia')} onChange={() => toggle('gastronomia')} /> Gastronomia</label>
        <label><input type="checkbox" checked={prefs.includes('natureza')} onChange={() => toggle('natureza')} /> Natureza</label>
      </div>

      <div style={{marginTop:10}}>
        <label>Quantidade de pontos:
          <input type="number" value={count} min="1" max="8" onChange={e=>setCount(Number(e.target.value))} />
        </label>
      </div>

      <button style={{marginTop:10}} onClick={()=>onGenerate(prefs, -23.304, -51.162, count)}>Gerar Roteiro</button>
    </div>
  )
}
