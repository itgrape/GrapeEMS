<template>

    <el-form :inline="true" class="query-form" id="my-form">
        <el-form-item label="部门">
            <el-select class="select-input" placeholder="请选择" v-model="deptName">
                <el-option v-for="dept in depts" :value="dept">{{dept}}</el-option>
            </el-select>
        </el-form-item>

        <el-form-item label="发布时间">
            <el-date-picker type="daterange" unlink-panels range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            v-model="pubTime"
            />
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="queryAnnounce()">查询</el-button>
            <el-button type="danger" @click="resetForm()">重置</el-button>
            <el-button type="danger" @click="deleteSelectAnnounce()">删除选中</el-button>
        </el-form-item>
    </el-form>

    <el-table :data="announceList" border class="user-table" @selection-change="handleSelectChange">
        <el-table-column type="selection" width="55" />

        <el-table-column prop="title" label="公告标题"/>
        <el-table-column prop="content" label="公告内容"/>
        <el-table-column prop="createTime" label="发布时间"/>

        <el-table-column label="相关操作" width="120">
            <template #default="scope">
                <el-button type="primary" @click="editDialogFormVisible = true; editAnnounce(scope.row)" :icon="Edit" circle />
                <el-button type="danger" @click="deleteOneAnnounce(scope.row)" :icon="Delete" circle />
            </template>
        </el-table-column>
    </el-table>


    <el-drawer
        ref="drawerRef"
        v-model="editDialogFormVisible"
        title="修改公告信息"
        direction="rtl"
        custom-class="demo-drawer"
    >
        <div class="demo-drawer__content">
            <el-form :model="editAnnounceForm">
                <el-form-item label="标题" :label-width="formLabelWidth">
                    <el-input v-model="editAnnounceForm.title" placeholder="新标题"/>
                </el-form-item>
                <el-form-item label="内容" :label-width="formLabelWidth">
                    <el-input v-model="editAnnounceForm.content" type="textarea" rows="20" placeholder="新内容"></el-input>
                </el-form-item>
            </el-form>
            <div class="demo-drawer__footer">
                <el-button @click="editDialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="editDialogFormVisible = false; editAnnouncePost()">确认修改</el-button>
            </div>
        </div>
    </el-drawer>
</template>

<script setup>
import { Edit, Delete } from '@element-plus/icons-vue'
import instance from "../../api/DataAxios";
import {ElMessage, ElMessageBox} from "element-plus";
import axios from 'axios'
import {ref, onMounted, reactive} from "vue";

let announceList = ref([])
let depts = ref([])

onMounted(() => {
    axios.all([getAllDeptName(), getAllAnnounce()])
})

function getAllDeptName() {
    return instance.get("/userCenter/getAllDeptName").then(
        response => {
            for (let dept of response.data) {
                depts.value.push(dept)
            }
        }
    )
}

function getAllAnnounce() {
    return instance.get("/announce/getAllAnnounce").then(
        response => {
            for (let a of response.data) {
                let announce = {
                    id: a.announceId,
                    title: a.announceTitle,
                    content: a.announceContent,
                    createTime: new Date(a.announceCreateTime).toLocaleString(),
                    deptId: a.deptId
                }
                announceList.value.push(announce)
            }
        }, error => {
            ElMessage.error("系统繁忙，请稍后再试")
        }
    )
}

function refreshAnnounceList() {
    announceList.value.splice(0,announceList.value.length)
    getAllAnnounce()
}


let pubTime = ref([])
let deptName = ref()
function queryAnnounce() {
    const startTime = pubTime.value[0]
    const endTime = pubTime.value[1]
    const queryDeptName = deptName.value
    instance.post("/announce/queryAnnounce", {
        deptName: queryDeptName,
        startTime: startTime,
        endTime: endTime,
    }).then(
        response => {
            announceList.value.splice(0,announceList.value.length)
            for (let a of response.data) {
                let announce = {
                    id: a.announceId,
                    title: a.announceTitle,
                    content: a.announceContent,
                    createTime: new Date(a.announceCreateTime).toLocaleString(),
                    deptId: a.deptId
                }
                announceList.value.push(announce)
            }
        }, error => {
            ElMessage.error("系统繁忙，请稍后再试")
        }
    )
}

function resetForm() {
    document.querySelector("#my-form").reset()
    pubTime = []
    deptName = ""
}

const formLabelWidth = '50px'
let editDialogFormVisible = ref(false)
let editAnnounceForm = reactive({
    id: null,
    title: null,
    content: null
})
function editAnnounce(row) {
    for (let announce of announceList.value) {
        if (row.id === announce.id) {
            editAnnounceForm.id = announce.id
            editAnnounceForm.title = announce.title
            editAnnounceForm.content = announce.content
        }
    }
}
function editAnnouncePost() {
    instance.post("/announce/editOneAnnounce", editAnnounceForm).then(
        response => {
            ElMessage.success("修改成功")
            refreshAnnounceList()
        }, error => {
            ElMessage.error("系统繁忙，请稍后再试")
        }
    )
}

function deleteOneAnnounce(row) {
    ElMessageBox.confirm(
        '',
        '删除提示', {
            confirmButtonText: '确定删除',
            cancelButtonText: '我再想想',
            type: 'warning',
        }
    ).then(() => {
        instance.get("/announce/deleteOneAnnounce/" + row.id).then(
            respone => {
                ElMessage({
                    type: 'success',
                    message: '公告已删除',
                })
                refreshAnnounceList()
            }, error => {
                ElMessage.error("系统繁忙，请稍后再试")
            }
        )
    }).catch(() => {
        ElMessage({
            type: 'info',
            message: '取消删除',
        })
    })
}

let deleteAnnounceList = ref([])
const handleSelectChange = (announces) => {
    deleteAnnounceList.value = []
    for (let announce of announces) {
        deleteAnnounceList.value.push(announce.id)
    }
}
function deleteSelectAnnounce() {
    for (let id of deleteAnnounceList.value) {
        instance.get("/announce/deleteOneAnnounce/" + id).then(
            respone => {
                ElMessage({
                    type: 'success',
                    message: '公告已删除',
                })
                refreshAnnounceList()
            }, error => {
                ElMessage.error("系统繁忙，请稍后再试")
            }
        )
    }
}

</script>

<style scoped>
.user-table {
    width: 100%;
}

.select-input {
    width: 120px;
}

</style>