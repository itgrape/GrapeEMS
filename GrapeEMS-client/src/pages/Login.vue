<template>


    <el-form :model="userinfo">
        <div class="login-div">
            <div class="login-text">Grape 员工管理系统</div>
            <el-input @keydown.enter="pressEmailInput" v-model="userinfo.userEmail" placeholder="请输入邮箱" class="username">
                <template #prepend>
                    <el-icon><Avatar /></el-icon>
                </template>
            </el-input>
            <el-input @keydown.enter="pressPasswordInput" id="password-input" v-model="userinfo.userPassword" placeholder="请输入密码" class="password" type="password">
                <template #prepend>
                    <el-icon><Coin /></el-icon>
                </template>
            </el-input>
            <el-input @keydown.enter="login" id="active-code-input" v-model="userinfo.activeCode" placeholder="点击图片可以刷新验证码" class="activeCode">
                <template #prepend>
                    <el-icon><HelpFilled /></el-icon>
                </template>
                <template #append>
                    <img src="../assets/kaptcha.png" id="kaptcha" alt="activeCode" @click="flushActiveCode" />
                </template>
            </el-input>
            <el-button type="primary" class="login-button" @click="login">登录</el-button>
        </div>
    </el-form>


</template>

<script setup>
import md5 from 'js-md5'
import {onMounted, reactive, ref} from 'vue'
import instance from "../api/LoginAxios";
import {useRouter} from "vue-router";
import {HelpFilled, Coin, Avatar} from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";

const router = useRouter()
let activeCodeText = ref('cf91deba88780e042a46f69e274de3a8')
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
        instance.post("/user/login", data).then(
            response => {
                if (response.data.code === 200) {
                    sessionStorage.clear()
                    sessionStorage.setItem("token", response.data.data.token)
                    if (response.data.data.role === '1') {
                        sessionStorage.setItem("userId", response.data.data.id)
                        sessionStorage.setItem("isPlainUser", true)
                        router.push("/plainUser/")
                    } else if (response.data.data.role === '0') {
                        sessionStorage.setItem("isAdmin", true)
                        router.push("/admin/")
                    }
                    ElMessage.success("登录成功")
                } else {
                    ElMessage.error("登录失败，请检查用户名密码是否正确")
                }
            }
        )
    } else {
        ElMessage.error("验证码有误，请重新输入")
        document.getElementById("active-code-input").focus()
        userinfo.activeCode = ""
        flushActiveCode()
    }
}

function flushActiveCode() {
    instance.get("/img/getActiveCode").then(
        response => {
            let imgByte = response.data.data.imgByte
            activeCodeText.value = response.data.data.imgText
            let outputImg = document.getElementById("kaptcha")
            outputImg.src = 'data:image/png;base64,' + imgByte
        }
    )
}

const pressEmailInput = () => {
    document.getElementById('password-input').focus()
}

const pressPasswordInput = () => {
    document.getElementById('active-code-input').focus()
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