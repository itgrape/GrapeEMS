<template>

    <tr>
        <td>{{leaveInfo.createTime}}</td>
        <td>{{leaveInfo.name}}</td>
        <td>{{leaveInfo.dept}}</td>
        <td>{{leaveInfo.role}}</td>
        <td>{{leaveInfo.leaveTime}}</td>
        <td>
            <el-button type="primary">确认销假</el-button>
        </td>
    </tr>

</template>

<script setup>
defineProps({
    leaveInfo: Object
})
</script>

<style scoped></style>