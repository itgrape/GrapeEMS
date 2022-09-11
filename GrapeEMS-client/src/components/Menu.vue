<template>
    <div id="my_scrollbar" style="background-color: #304156;">
        <el-scrollbar>
            <el-menu
                :default-active="menuActive"
                active-text-color="#ffd04b"
                background-color="#304156"
                text-color="#fff"
                :unique-opened="true"
                class="el-menu-vertical-demo"
                :router="true"
                @select="menuSelect"
            >
                <el-menu-item index="index">
                    <template #title>
                        <span><el-icon><House /></el-icon>首页</span>
                    </template>
                </el-menu-item>
                <el-sub-menu index="2">
                    <template #title>
                        <!--<el-icon><location /></el-icon>-->
                        <span><el-icon><Money /></el-icon>财务数据</span>
                    </template>
                    <el-menu-item index="salary"><el-icon><Lock /></el-icon>工资详情</el-menu-item>
                </el-sub-menu>
                <el-sub-menu index="3">
                    <template #title>
                        <!--<el-icon><location /></el-icon>-->
                        <span><el-icon><Coordinate /></el-icon>审批管理</span>
                    </template>
                    <el-menu-item index="leave"><el-icon><Smoking /></el-icon>请假申请</el-menu-item>
                    <el-menu-item index="back"><el-icon><NoSmoking /></el-icon>销假申请</el-menu-item>
                    <el-menu-item index="morework"><el-icon><Cpu /></el-icon>加班申请</el-menu-item>
                    <el-menu-item index="log"><el-icon><Notebook /></el-icon>审批日志</el-menu-item>
                </el-sub-menu>
                <el-sub-menu index="4">
                    <template #title>
                        <!--<el-icon><location /></el-icon>-->
                        <span><el-icon><Menu /></el-icon>用户管理</span>
                    </template>
                    <el-menu-item index="user"><el-icon><Avatar /></el-icon>用户中心</el-menu-item>
                </el-sub-menu>
                <el-sub-menu index="5">
                    <template #title>
                        <!--<el-icon><location /></el-icon>-->
                        <span><el-icon><Histogram /></el-icon>部门管理</span>
                    </template>
                    <el-menu-item index="dept"><el-icon><OfficeBuilding /></el-icon>部门中心</el-menu-item>
                </el-sub-menu>
                <el-sub-menu index="6">
                    <template #title>
                        <!--<el-icon><location /></el-icon>-->
                        <span><el-icon><BellFilled /></el-icon>公告管理</span>
                    </template>
                    <el-menu-item index="announce"><el-icon><Promotion /></el-icon>发布公告</el-menu-item>
                    <el-menu-item index="check"><el-icon><CircleCheck /></el-icon>管理公告</el-menu-item>
                </el-sub-menu>
            </el-menu>
        </el-scrollbar>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { Promotion, BellFilled, CircleCheck, OfficeBuilding, Histogram, Avatar, House, Money, Lock, Coordinate, Cpu, Smoking, NoSmoking, Notebook } from "@element-plus/icons-vue"

const router = useRouter()

const menuActive = ref()
onMounted(() => {
    // 获取当前路由地址，从而给 menu 设置 default-active
    menuActive.value = router.currentRoute._value.fullPath.split("/")[1]
})

</script>

<style scoped>
#my_scrollbar {
    height: calc(100vh - 80px);
}

</style>