<template>

    <div class="custom-total">
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
            backApplications.value.splice(0, backApplications.value.length)
            let data = response.data
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
                    leaveStartTime: new Date(a.leaveApplication.leaveApplicationLeaveStartTime).toLocaleDateString(),
                    leaveEndTime: new Date(a.leaveApplication.leaveApplicationLeaveEndTime).toLocaleDateString(),
                }
                backApplications.value.push(backInfo)
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