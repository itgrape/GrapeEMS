<template>

    <div>
        <table>
            <tbody>
            <tr>
                <th>创建时间</th>
                <th>姓名</th>
                <th>部门</th>
                <th>角色</th>
                <th class="leave-time">请假时间</th>
                <th class="opt">操作</th>
            </tr>
            <BackApplication v-for="(item, index) in backApplications" :key="item" :backInfo="item" />
            </tbody>
        </table>
    </div>


</template>



<script setup>
import BackApplication from '../../components/BackApplication.vue'
import {onMounted, ref} from "vue";
import instance from "../../api/DataAxios";
import axios from 'axios'
import {ElMessage} from "element-plus";

let backApplications = ref([])

onMounted(() => {
    axios.all([getAllBackApplications()])
})

function getAllBackApplications() {
    return instance.get("/approve/getAllBackApplication").then(
        response => {
            let data = response.data
            console.log(data)
            for (let a of data) {
                let backInfo = {
                    id: a.backApplicationId,
                    createTime: new Date(a.backApplicationCreateTime).toLocaleString(),
                    name: a.user.userName,
                    dept: a.user.deptName,
                    role: a.user.roleName,
                    reason: a.leaveApplication.leaveApplicationReason,
                    detail: a.leaveApplication.leaveApplicationDetail,
                    email: a.user.userEmail,
                    leaveStartTime: new Date(a.leaveApplication.leaveApplicationLeaveStartTime).toLocaleString(),
                    leaveEndTime: new Date(a.leaveApplication.leaveApplicationLeaveEndTime).toLocaleString(),
                }
                backApplications.value.push(backInfo)
            }
        }, error => {
            ElMessage.error("系统繁忙，请稍后再试")
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