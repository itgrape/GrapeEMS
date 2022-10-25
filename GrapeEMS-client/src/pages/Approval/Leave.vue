<template>

    <div class="custom-total">
        <table>
            <tbody>
                <tr>
                    <th>创建时间</th>
                    <th>姓名</th>
                    <th>部门</th>
                    <th>角色</th>
                    <th>请假原因</th>
                    <th class="leave-time">请假时间</th>
                    <th class="opt">操作</th>
                </tr>
                <LeaveApplication v-for="(item, index) in leaveApplications" :key="item" :leaveInfo="item" />
            </tbody>
        </table>
    </div>


</template>

<script setup>
import LeaveApplication from '../../components/LeaveApplication.vue'
import {onMounted, ref} from "vue"
import instance from "../../api/DataAxios"
import axios from 'axios'
import {ElMessage} from "element-plus"

let leaveApplications = ref([])

onMounted(() => {
    axios.all([getAllLeaveApplications()])
})

function getAllLeaveApplications() {
    return instance.get("/approve/getAllLeaveApplication").then(
        response => {
            let data = response.data
            for (let a of data) {
                let leaveInfo = {
                    id: a.leaveApplicationId,
                    createTime: new Date(a.leaveApplicationCreateTime).toLocaleString(),
                    name: a.user.userName,
                    dept: a.user.deptName,
                    role: a.user.roleName,
                    reason: a.leaveApplicationReason,
                    detail: a.leaveApplicationDetail,
                    email: a.user.userEmail,
                    leaveStartTime: new Date(a.leaveApplicationLeaveStartTime).toLocaleDateString(),
                    leaveEndTime: new Date(a.leaveApplicationLeaveEndTime).toLocaleDateString(),
                }
                leaveApplications.value.push(leaveInfo)
            }
        }
    )
}
</script>

<style scoped>
.custom-total {
    margin: -20px;
}

table {
    text-align: center;
    font-family: 华文宋体, serif;
    border-collapse: collapse;
    width:100%;
    border:1px solid #c6c6c6 !important;
    margin-bottom:20px;
}

tr {
    height: 45px;
}

tr:first-child {
    height: 60px;
}

.leave-time, .opt {
    width: 250px;
}

th{
    width: 130px;
    border-collapse: collapse;
    border-right:1px solid #c6c6c6 !important;
    border-bottom:1px solid #c6c6c6 !important;
    background-color:#ddeeff !important;
    padding:5px 9px;
    font-size:19px;
    font-weight:normal;
    text-align:center;
}
</style>