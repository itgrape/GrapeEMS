import axios from 'axios'
import {ElMessage} from "element-plus";

const instance = axios.create({
    baseURL: 'http://localhost:12280',
    timeout: 2500
})

instance.interceptors.response.use(
    response => {
        return response
    }, error => {
        //处理响应错误
        ElMessage.error("系统繁忙，请稍后再试")
        return Promise.reject(error)
    }
)

export default instance

