<template>

    <el-container>
        <el-header height="80px" class="my-header"><Header /></el-header>
        <el-container>
            <el-aside width="210px" class="my-aside"><Menu /></el-aside>
            <el-container>
                <el-main class="main-area"><router-view /></el-main>
            </el-container>
        </el-container>
    </el-container>


</template>

<script setup>
import Header from '../components/Header.vue'
import Menu from '../components/Menu.vue'
</script>

<style scoped>
.my-header {
    width: calc(100vw);
    padding: 0;
    background-color: #ffffff;
    box-shadow: 0 0 2px 0 #c8c9cc;
}

.main-area {
    background-color: #ffffff;
    margin: 15px 16px 0 15px;
    box-shadow: 0 0 2px 0 #c8c9cc;
    height: calc(100vh - 95px);
    overflow: auto;
}
</style>