import axios from 'axios'
import {ElMessage} from "element-plus";
import router from "../router";

const instance = axios.create({
    baseURL: 'http://localhost:8088',
    timeout: 2500
})

instance.interceptors.request.use(
    config => {
        //在发送请求之前触发
        config.headers.token = sessionStorage.getItem("token")
        return config
    }, error => {
        //处理错误请求
        return Promise.reject(error)
    }
)

instance.interceptors.response.use(
    response => {
        //处理响应数据
        if (response.data.code === 401) {
            ElMessage.error("认证失败，请重新登录")
            sessionStorage.clear()
            router.push("/login")
        } else if (response.data.code === 403) {
            ElMessage.warning("权限不足")
        }
        return response
    }, error => {
        //处理响应错误
        ElMessage.error("系统繁忙，请稍后再试")
        return Promise.reject(error)
    }
)

export default instance

