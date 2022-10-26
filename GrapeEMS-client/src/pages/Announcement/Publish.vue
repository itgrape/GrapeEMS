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
            <el-form-item label="">
                <el-select v-model="announcement.deptName" placeholder="请选择发布部门">
                    <el-option value="全体部门">全体部门</el-option>
                    <el-option v-for="dept in depts" :value="dept">{{dept}}</el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="">
                <el-button type="success" @click="pubAnnounce()">发布</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script setup>

import {reactive, onMounted, ref} from "vue"
import instance from "../../api/DataAxios"
import {ElMessage} from "element-plus"

let depts = ref([])
let announcement = reactive({
    title: null,
    content: null,
    deptName: null
})

onMounted(() => {
    getAllDeptName()
})

function getAllDeptName() {
    return instance.get("/userCenter/getAllDeptName").then(
        response => {
            depts.value = []
            for (let dept of response.data) {
                depts.value.push(dept)
            }
        }
    )
}

function pubAnnounce() {
    if (announcement.title !== null && announcement.title !== '' && announcement.content !== null && announcement.content !== '' && announcement.deptName !== null && announcement.deptName !== '') {
        instance.post("/announce/addOneAnnounce", announcement).then(
            response => {
                ElMessage.success("发布成功")
                //发布成功后清空数据
                announcement.title = null
                announcement.content = null
                announcement.deptName = null
            }
        )
    } else {
        ElMessage.warning("请检查表单信息是否有误")
    }
}

</script>

<style scoped></style>