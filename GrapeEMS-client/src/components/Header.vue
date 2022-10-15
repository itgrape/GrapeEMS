<template>
    <div class="logo">
        <img src="../assets/logo.png" alt="logo" class="logo-img">
        <span class="logo-text1">学参天地</span>
        <span class="logo-text2">德和自然</span>
    </div>

    <div class="header">
        <div class="title-text">
            <span>Grape EMS</span>
        </div>

        <div class="user">
            <el-dropdown><!--@command="commandAction"-->
            <span class="el-dropdown-link">
                <el-avatar :icon="UserFilled" :size="30" style="margin-right: 6px; vertical-align: middle;"/>
                <span class="username">{{ username }}<el-icon class="el-icon--right"><arrow-down/></el-icon></span>
            </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item :command="1" @click="logout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>

<script setup>
import {UserFilled, ArrowDown} from "@element-plus/icons-vue"
import router from '../router'
import instance from "../api/DataAxios";
import {ElMessage} from "element-plus";

const username = "admin"

const logout = () => {
    instance.post("/user/logout").then(
        response => {
            ElMessage.success("退出登录成功")
            sessionStorage.clear()
            router.push("/login")
        }
    )
}
</script>

<style scoped>
.logo {
    height: 100%;
    width: 209px;
    float: left;
    background-color: #2b2f3a;
    font-size: 20px;
    color: #ffffff;
    font-family: 宋体, serif;
}

.logo-img {
    height: 80px;
    position: absolute;
    top: 5px;
    left: -18px;
}

.logo-text1 {
    position: absolute;
    top: 15px;
    left: 90px;
}

.logo-text2 {
    position: absolute;
    top: 45px;
    left: 110px;
}

.title-text {
    position: absolute;
    top: 26px;
    left: 300px;
    font-size: 17px;
}

.user {
    position: absolute;
    top: 26px;
    right: 20px;
}

.username {
    font-size: 16px;
    color: #51a7ff;
    cursor: pointer;
}

.el-icon--right {
    top: 3px;
}
</style>