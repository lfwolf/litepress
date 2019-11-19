import axios from 'axios'

// create an axios instance
const service = axios.create({
  baseURL: process.env.API_ROOT,
  timeout: 5000
})

const wpservice = axios.create({
  baseURL: process.env.API_ROOT_WP,
  timeout: 5000
})

const lizhiservice = axios.create({
  baseURL: process.env.API_ROOT_LIZHI,
  timeout: 5000
})

export default service
export { wpservice, lizhiservice }
