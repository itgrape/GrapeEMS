<template>

    <div>
        <h3>公告标题</h3>
        <el-input v-model="announcement.title" requried />
    </div>
    <br>
    <div>
        <h3>公告内容</h3>
        <el-input v-model="announcement.content" type="textarea"/>
    </div>
    <br><br>
    <div>
        <h3>发布</h3>
        <el-form :inline="true">
            <el-form-item label="发布对象部门">
                <el-select v-model="announcement.deptName" placeholder="请选择发布部门">
                    <el-option v-for="dept in depts" :value="dept">{{dept}}</el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="确认发布">
                <el-button type="success" @click="pubAnnounce()">发布</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script setup>

import {reactive, onMounted, ref} from "vue"
import instance from "../../api/DataAxios"
import {ElMessage} from "element-plus"
import {useAdminStore} from "../../store/adminStore";

const adminStore = useAdminStore()

let depts = ref([])
let announcement = reactive({
    title: null,
    content: null,
    deptName: null
})

onMounted(() => {
    depts.value = adminStore.depts
})

function pubAnnounce() {
    instance.post("/announce/addOneAnnounce", announcement).then(
        response => {
            ElMessage.success("发布成功")
            //发布成功后清空数据
            announcement = {
                title: null,
                content: null,
                deptName: null
            }
        }, error => {
            ElMessage.error("系统繁忙，请稍后再试")
        }
    )
}

</script>

<style scoped></style>