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
            ElMessage.success("审批接受")
        })
        .catch((action) => {
            if (action === 'cancel') {
                instance.get("/approve/rejectLeave/" + props.leaveInfo.id)
                ElMessage.error("审批拒绝")
            } else if (action === 'close') {
                //关闭
            }
        })
}

</script>