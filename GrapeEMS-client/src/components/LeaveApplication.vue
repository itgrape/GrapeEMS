<template>

    <tr>
        <td>{{leaveInfo.createTime}}</td>
        <td>{{leaveInfo.name}}</td>
        <td>{{leaveInfo.dept}}</td>
        <td>{{leaveInfo.role}}</td>
        <td>病假</td>
        <td>{{leaveInfo.leaveTime}}</td>
        <td>
            <el-button type="primary">处理</el-button>
            <el-button type="danger">忽略</el-button>
        </td>
    </tr>

</template>

<script setup>
defineProps({
    leaveInfo: Object
})
</script>

<style scoped></style>