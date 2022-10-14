<template>

    <tr>
        <td>{{backInfo.createTime}}</td>
        <td>{{backInfo.name}}</td>
        <td>{{backInfo.dept}}</td>
        <td>{{backInfo.role}}</td>
        <td>{{backInfo.leaveStartTime}} - {{backInfo.leaveEndTime}}</td>
        <td>
            <el-button type="primary" @click="handleApplication">确认销假</el-button>
        </td>
    </tr>

</template>

<script setup>
import {defineProps} from "vue/dist/vue.esm-browser";
import {ElMessage, ElMessageBox} from "element-plus";
import instance from "../api/DataAxios";

const props = defineProps({
    backInfo: Object
})

const handleApplication = () => {
    ElMessageBox.confirm(
        '确定为此员工销假吗',
        '销假提示', {
            confirmButtonText: '确定销假',
            type: 'warning',
        }
    ).then(() => {
        instance.get("/approve/acceptBack/" + props.backInfo.id)
        ElMessage.success("销假成功")
    }).catch(() => {
        //未销假
    })
}
</script>

<style scoped></style>