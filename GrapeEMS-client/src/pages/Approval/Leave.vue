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

let leaveApplications = [
    {
        createTime: "2022.09.15",
        name: "张三",
        dept: "开发部",
        role: "总经理",
        leaveTime: "2022.09.15-2022.10.30",
        detail: "老子就是想请假，就问你批不批吧！"
    }
]
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