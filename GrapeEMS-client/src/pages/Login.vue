<template>

 
    <form action="#">
        <div class="login-div">
            <div class="login-text">欢迎使用 Grape 员工管理系统</div>
            <el-input v-model="userinfo.username" placeholder="请输入用户名" class="username">
                <template #prepend>
                    <el-icon><Avatar /></el-icon>
                </template>
            </el-input>
            <el-input v-model="userinfo.password" placeholder="请输入密码" class="password" type="password">
                <template #prepend>
                    <el-icon><Coin /></el-icon>
                </template>
            </el-input>
            <el-input v-model="userinfo.activeCode" placeholder="请输入图片验证码" class="activeCode">
                <template #prepend>
                    <el-icon><HelpFilled /></el-icon>
                </template>
                <template #append>
                    <img alt="activeCode" src="../assets/kaptcha.png" />
                </template>
            </el-input>
            <el-button type="primary" class="login-button" @click="login">登录</el-button>
        </div>
    </form>


</template>

<script setup>
import { reactive } from 'vue'

const userinfo = reactive({
    username: '',
    password: '',
    activeCode: '',
})

function login() {
    alert("模拟登录\n用户名:" + userinfo.username + "\n密码:" + userinfo.password + "\n验证码:" + userinfo.activeCode)
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