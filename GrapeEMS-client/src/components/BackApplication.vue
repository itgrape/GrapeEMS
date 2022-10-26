<template>

    <tr>
        <td width="200">{{backInfo.createTime}}</td>
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
import router from "../router";

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
        router.go(0)
        ElMessage.success("销假成功")
    }).catch(() => {
        //未销假
    })
}
</script>

<style scoped>
td{
    border-collapse: collapse;
    border-right:1px solid #c6c6c6 !important;
    border-bottom:1px solid #c6c6c6 !important;
    padding:5px 9px;
    font-size:15px;
    font-weight:normal;
    text-align:center;
    word-break: break-all;
}
tr:nth-child(odd){
    background-color:#fff !important;
}
tr:nth-child(even){
    background-color: #f8f8f8 !important;
}
</style>