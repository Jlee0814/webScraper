import axios from 'axios'
import { Message } from 'element-ui'

// create axios instance
const service = axios.create({
  baseURL: "http://127.0.0.1:8887", //  base_url
  timeout: 5000 // request time out
})


// response interceptor
service.interceptors.response.use(
  response => {
    /**
     * code 20000 == success 
     */
    const res = response.data
    if (res.code !== 20000) {
      Message({
        message: res.message,
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
