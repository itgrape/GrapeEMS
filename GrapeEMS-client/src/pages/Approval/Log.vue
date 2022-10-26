<template>

    <div class="custom-total">
        <div class="custom-form">
            <el-form :inline="true">
                <div class="custom-not-button-group">
                    <el-form-item label="对象姓名">
                        <el-input v-model="queryForm.name" class="name-input" placeholder="支持模糊匹配"></el-input>
                    </el-form-item>

                    <el-form-item label="对象部门">
                        <el-select v-model="queryForm.dept" class="select-input" placeholder="请选择">
                            <el-option v-for="dept in depts" :value="dept">{{ dept }}</el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="审批内容" v-show="isOpen">
                        <el-select v-model="queryForm.kind" class="select-input" placeholder="请选择">
                            <el-option value="1">请假申请</el-option>
                            <el-option value="2">销假申请</el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="审批时间" v-show="isOpen">
                        <el-date-picker type="datetimerange"
                                        unlink-panels range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        v-model="approveTime"
                        />
                    </el-form-item>
                </div>

                <el-form-item class="custom-button-group">
                    <el-button type="primary" @click="doQuery()">查询</el-button>
                    <el-button type="danger" @click="resetForm">重置</el-button>
                    <el-button type="danger" @click="deleteSelectLogs">删除选中</el-button>
                    <span class="custom-span-switch" @click="isOpen = !isOpen"><span v-show="isOpen">合并<el-icon><ArrowUp /></el-icon></span><span v-show="!isOpen">展开<el-icon><ArrowDown /></el-icon></span></span>
                </el-form-item>
            </el-form>
        </div>

        <hr>

        <div class="custom-table">
            <el-table :data="logInfoTable" class="log-table" stripe @selection-change="handleSelectChange">
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
        </div>

        <div class="custom-pagination">
            <el-pagination
                v-model:currentPage="currentPage"
                v-model:page-size="pageSize"
                :page-sizes="[5, 10, 15, 20]"
                :background="true"
                layout="total, sizes, prev, pager, next, jumper"
                :total="totalNum"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
            />
        </div>
    </div>

</template>

<script setup>
import { Delete } from '@element-plus/icons-vue'
import {onMounted, reactive, ref, watch} from "vue";
import instance from "../../api/DataAxios";
import axios from 'axios'
import {ElMessage} from "element-plus";

let logInfoTable = ref([])
let logInfo = ref([])
let depts = ref([])

let currentPage = ref(1)
let pageSize = ref(10)
let totalNum = ref(0)

onMounted(() => {
    axios.all([getAllApproveLogs(), getAllDeptName()])
})

const handleSizeChange = (ps) => {
    pageSize.value = ps
    refresh_log_info_table()
}

const handleCurrentChange = (cp) => {
    currentPage.value = cp
    refresh_log_info_table()
}

const refresh_log_info_table = () => {
    logInfoTable.value = logInfo.value.slice(pageSize.value * (currentPage.value - 1), pageSize.value * currentPage.value)
}

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

function refresh_data() {
    logInfo.value.splice(0,logInfo.value.length)
}

function fillData(data) {
    refresh_data()
    totalNum.value = data.length
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
    refresh_log_info_table()
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
        queryForm.approveStartTime = new Date(approveTime.value[0]).getTime()
        queryForm.approveEndTime = new Date(approveTime.value[1]).getTime()
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



//以下为美化界面JS代码
let isOpen = ref(false)
import eleResizeDetector from "element-resize-detector"
onMounted(() => {
    let erd = eleResizeDetector()
    erd.listenTo(document.querySelector(".custom-not-button-group"), element => {
        document.querySelector(".log-table").style.height = "calc(100vh - " + (200 + element.offsetHeight) + "px)"
    })
})
</script>

<style scoped>
.custom-total {
    width: 100%;
}

.custom-form {
    padding-right: 300px;
    height: auto;
}

.custom-button-group {
    position: absolute;
    right: 10px;
    top: 115px;
}

.log-table {
    width: 100%;
    height: calc(100vh - 260px);
}

.name-input {
    width: 120px;
}

.select-input {
    width: 120px;
}

.custom-span-switch {
    margin-left: 10px;
    cursor: pointer;
    color: #51afff;
}

.custom-pagination {
    margin-top: 10px;
    margin-left: 50%;
    transform: translateX(-50%);
}
</style>