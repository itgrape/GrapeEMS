<template>

    <tr>
        <td width="200">{{leaveInfo.createTime}}</td>
        <td>{{leaveInfo.name}}</td>
        <td>{{leaveInfo.dept}}</td>
        <td>{{leaveInfo.role}}</td>
        <td>{{leaveInfo.reason}}</td>
        <td>{{leaveInfo.leaveStartTime}} - {{leaveInfo.leaveEndTime}}</td>
        <td>
            <el-button type="primary" @click="handleApplication">处理</el-button>
        </td>
    </tr>

</template>

<script setup>
import {ElMessage, ElMessageBox} from "element-plus";
import {defineProps} from "vue/dist/vue.esm-browser";
import instance from "../api/DataAxios";
import router from "../router";
const props = defineProps({
    leaveInfo: Object
})

const applicationText = `
<table>
<tr><td style="width: 130px;">申请时间：</td><td>` + props.leaveInfo.createTime + `</td></tr>
<tr><td>申请人姓名：</td><td>` + props.leaveInfo.name + `</td></tr>
<tr><td>申请人部门：</td><td>` + props.leaveInfo.dept + `</td></tr>
<tr><td>申请人身份：</td><td>` + props.leaveInfo.role + `</td></tr>
<tr><td>申请人联系方式：</td><td>` + props.leaveInfo.email + `</td></tr>
<tr><td>请假原因：</td><td>` + props.leaveInfo.reason + `</td></tr>
<tr><td>描述信息：</td><td>` + props.leaveInfo.detail + `</td></tr>
<tr><td>请假时间：</td><td>` + props.leaveInfo.leaveStartTime + ' - ' + props.leaveInfo.leaveEndTime + `</td></tr>
</table>
`
const handleApplication = () => {
    ElMessageBox.confirm(
        applicationText,
        '处理请假申请',
        {
            distinguishCancelAndClose: true,
            confirmButtonText: '批准',
            cancelButtonText: '拒绝',
            dangerouslyUseHTMLString: true,
        }
    )
        .then(() => {
            instance.get("/approve/acceptLeave/" + props.leaveInfo.id)
            router.go(0)
            ElMessage.success("审批接受")
        })
        .catch((action) => {
            if (action === 'cancel') {
                instance.get("/approve/rejectLeave/" + props.leaveInfo.id)
                router.go(0)
                ElMessage.warning("审批拒绝")
            } else if (action === 'close') {
                //关闭
            }
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