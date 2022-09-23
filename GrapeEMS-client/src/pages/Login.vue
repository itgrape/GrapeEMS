<template>

 
    <el-form :model="userinfo">
        <div class="login-div">
            <div class="login-text">Grape 员工管理系统</div>
            <el-input v-model="userinfo.userEmail" placeholder="请输入邮箱" class="username">
                <template #prepend>
                    <el-icon><Avatar /></el-icon>
                </template>
            </el-input>
            <el-input v-model="userinfo.userPassword" placeholder="请输入密码" class="password" type="password">
                <template #prepend>
                    <el-icon><Coin /></el-icon>
                </template>
            </el-input>
            <el-input v-model="userinfo.activeCode" placeholder="请输入图片验证码" class="activeCode">
                <template #prepend>
                    <el-icon><HelpFilled /></el-icon>
                </template>
                <template #append>
                    <img id="kaptcha" alt="activeCode" @click="flushActiveCode" />
                </template>
            </el-input>
            <el-button type="primary" class="login-button" @click="login">登录</el-button>
        </div>
    </el-form>


</template>

<script setup>
import '../api/md5'
import {onMounted, reactive, ref} from 'vue'
import axios from 'axios'
import {useUserStore} from "../store/userStore"
import {useRouter} from "vue-router";

const router = useRouter()
const userStore = useUserStore()
let activeCodeText = ref()
const userinfo = reactive({
    userEmail: '',
    userPassword: '',
    activeCode: '',
})

onMounted (() => {
    flushActiveCode()
})

function login() {
    let md5ActiveCode = md5(userinfo.activeCode)
    if (md5ActiveCode === activeCodeText.value) {
        //验证码正确
        let data = {
            userEmail: userinfo.userEmail,
            userPassword: userinfo.userPassword
        }
        axios.post("http://localhost:8088/user/login", data).then(
            response => {
                if (response.data.code === 200) {
                    //登录成功
                    localStorage.setItem("token", response.data.data.token)
                    if (response.data.data.role === '1') {
                        userStore.isPlainUser = true
                        localStorage.setItem("isPlainUser", true)
                    } else if (response.data.data.role === '0') {
                        userStore.isAdmin = true
                        localStorage.setItem("isAdmin", true)
                        router.push("/")
                    }
                } else {
                    alert("登录失败，请检查用户名密码是否正确")
                }
            }, error => {
                alert("系统繁忙，请稍后再试")
            }
        )
    } else {
        alert("验证码有误，请重新输入")
        flushActiveCode()
    }
}

function flushActiveCode() {
    axios.get("http://localhost:8088/img/getActiveCode").then(
        response => {
            let imgByte = response.data.data.imgByte
            activeCodeText.value = response.data.data.imgText
            let outputImg = document.getElementById("kaptcha")
            outputImg.src = 'data:image/png;base64,' + imgByte
        }, error => {
            console.log("验证码请求错误")
        }
    )
}

</script>

<style scoped>
.login-div {
    position: relative;
    height: calc(100vh - 60px);
    width: calc(100vw);
    top: 60px;
}

.login-text {
    position: absolute;
    top: 17%;
    left: 50%;
    transform: translate(-50%);
    font-size: 26px;
    font-family: 宋体, 'Courier New', Courier, monospace;
}

.username,.password,.activeCode,.login-button {
    width: 400px;
    height: 40px;
    font-size: 16px;
    position: absolute;
    left: 50%;
    transform: translate(-50%);
}

.username {
    top: 25%;
}

.password {
    top: 32%;
}

.activeCode {
    top: 39%;
}

.login-button {
    top: 48%;
}

</style>