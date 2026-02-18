import axios from 'axios';
const api = axios.create({ baseURL: 'http://localhost:8080/api' });

export async function generateRoute(prefs = [], lat = -23.304, lon = -51.162, count = 4) {
  const body = { prefs, lat, lon, count }
  const res = await api.post('/generate', body)
  return res.data
}