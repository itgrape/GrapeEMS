import axios from 'axios'


const instance = axios.create({
    baseURL: 'http://localhost:8088',
    timeout: 2500
})

instance.interceptors.request.use(
    config => {
        //在发送请求之前触发
        config.headers.permission = localStorage.getItem("token")
        return config
    }, error => {
        //处理错误请求
        
        return Promise.reject(error)
    }
)

instance.interceptors.response.use(
    response => {
        //处理响应数据
        
        return response
    }, error => {
        //处理响应错误
        
        return Promise.reject(error)
    }
)

export default instance

