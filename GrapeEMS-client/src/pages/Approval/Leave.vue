<template>

    <div>
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
                    leaveStartTime: new Date(a.leaveApplicationLeaveStartTime).toLocaleString(),
                    leaveEndTime: new Date(a.leaveApplicationLeaveEndTime).toLocaleString(),
                }
                leaveApplications.value.push(leaveInfo)
            }
        }
    )
}
</script>

<style scoped>
table {
    text-align: center;
    font-family: 华文宋体, serif;
    width: 100%;
}

tr {
    height: 50px;
}

th {
    width: 130px;
}

.leave-time, .opt {
    width: 250px;
}
</style>