<template>

    <el-form :inline="true" class="query-form">
        <el-form-item label="姓名">
            <el-input v-model="queryForm.name" class="name-input" placeholder="支持模糊匹配"></el-input>
        </el-form-item>

        <el-form-item label="部门">
            <el-select v-model="queryForm.dept" class="select-input" placeholder="请选择">
                <el-option v-for="dept in depts" :value="dept">{{ dept }}</el-option>
            </el-select>
        </el-form-item>

        <el-form-item label="审批内容">
            <el-select v-model="queryForm.kind" class="select-input" placeholder="请选择">
                <el-option value="1">请假申请</el-option>
                <el-option value="2">销假申请</el-option>
            </el-select>
        </el-form-item>

        <el-form-item label="审批时间">
            <el-date-picker type="daterange" unlink-panels range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            v-model="approveTime"
            />
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="doQuery()">查询</el-button>
            <el-button type="danger" @click="resetForm">重置</el-button>
            <el-button type="danger" @click="deleteSelectLogs">删除选中</el-button>
        </el-form-item>
    </el-form>

    <el-table :data="logInfo" class="user-table" stripe @selection-change="handleSelectChange">
        <el-table-column type="selection" width="55" />

        <el-table-column prop="approvalTime" label="审批时间" width="200"/>
        <el-table-column prop="approvalContent" label="审批内容"/>
        <el-table-column prop="approvalResult" label="审批结果"/>
        <el-table-column prop="name" label="姓名" width="120" />
        <el-table-column prop="dept" label="部门" />
        <el-table-column prop="role" label="角色" />
        <el-table-column prop="email" label="联系方式" width="140" />


        <el-table-column label="操作" width="120" fixed="right">
            <template #default="scope">
                <el-button type="danger" @click="deleteOneLog(scope.row)" :icon="Delete" circle />
            </template>
        </el-table-column>
    </el-table>

</template>

<script setup>
import { Delete } from '@element-plus/icons-vue'
import {onMounted, reactive, ref} from "vue";
import instance from "../../api/DataAxios";
import axios from 'axios'
import {ElMessage} from "element-plus";
import {useAdminStore} from "../../store/adminStore";

const adminStore = useAdminStore()

let logInfo = ref([])
let depts = ref([])

onMounted(() => {
    axios.all([getAllApproveLogs()])
    depts.value = adminStore.depts
})

function refresh_data() {
    logInfo.value.splice(0,logInfo.value.length)
}

function fillData(data) {
    refresh_data()
    for (let a of data) {
        if (a.approveLogKind === 1) {
            const approval = {
                id: a.approveLogId,
                approvalTime: new Date(a.content.leaveApplicationApproveTime).toLocaleString(),
                approvalContent: '请假审批',
                approvalResult: a.content.leaveApplicationApproveResult,
                name: a.content.user.userName,
                dept: a.content.user.deptName,
                role: a.content.user.roleName,
                email: a.content.user.userEmail,
            }
            logInfo.value.push(approval)
        } else if (a.approveLogKind === 2) {
            const approval = {
                id: a.approveLogId,
                approvalTime: new Date(a.content.backApplicationApproveTime).toLocaleString(),
                approvalContent: '销假审批',
                approvalResult: a.content.backApplicationApproveResult,
                name: a.content.user.userName,
                dept: a.content.user.deptName,
                role: a.content.user.roleName,
                email: a.content.user.userEmail,
            }
            logInfo.value.push(approval)
        }
    }
}

function getAllApproveLogs() {
    return instance.get("/approve/getAllApproveLogs").then(
        response => {
            const data = response.data
            fillData(data)
        }
    )
}

let approveTime = ref(null)
let queryForm = reactive({
    name: null,
    dept: null,
    kind: null,
    approveStartTime: null,
    approveEndTime: null
})
function doQuery() {
    if (approveTime.value !== null && approveTime.value !== '') {
        queryForm.approveStartTime = approveTime.value[0]
        queryForm.approveEndTime = approveTime.value[1]
    }
    instance.post("/approve/queryApproveLog", queryForm).then(
        response => {
            const data = response.data
            fillData(data)
        }
    )
}

const resetForm = () => {
    approveTime.value = null
    queryForm.name = null;
    queryForm.dept = null;
    queryForm.kind = null;
    queryForm.approveStartTime = null;
    queryForm.approveEndTime = null;
}

const deleteOneLog = (row) => {
    instance.get("/approve/deleteOneApproveLog/" + row.id).then(
        response => {
            refresh_data()
            getAllApproveLogs()
            ElMessage.success("删除成功")
        }
    )
}
let deleteLogList = ref([])
const handleSelectChange = (logs) => {
    deleteLogList.value = []
    for (let log of logs) {
        deleteLogList.value.push(log.id)
    }
}
const deleteSelectLogs = () => {
    for (let id of deleteLogList.value) {
        instance.get("/approve/deleteOneApproveLog/" + id)
    }
    refresh_data()
    getAllApproveLogs()
    ElMessage.success("已全部删除")
}
</script>

<style scoped>
.user-table {
    width: 100%;
    height: calc(100vh - 285px);
}

.name-input {
    width: 120px;
}

.select-input {
    width: 120px;
}

.age-input {
    width: 50px;
}

</style>