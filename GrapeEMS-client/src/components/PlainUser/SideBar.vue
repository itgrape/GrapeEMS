<template>
    <div id="menu">

        <el-tooltip class="box-item" effect="light" content="发起申请" placement="left-start">
            <el-button @click="dialogFormVisible = true" type="primary" :icon="Position" circle style="margin-bottom: 10px" />
        </el-tooltip>

        <br>
        <el-dropdown>
            <span class="el-dropdown-link">
                <el-avatar :icon="UserFilled" :size="32" />
            </span>
            <template #dropdown>
                <el-dropdown-menu>
                    <el-dropdown-item :command="1" @click="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </template>
        </el-dropdown>

    </div>

    <el-dialog v-model="dialogFormVisible" width="40%" title="发起申请" center>
        <el-form>
            <el-form-item label="申请类型" label-width="30%">
                <el-select v-model="applicationKind">
                    <el-option value="请假申请">请假申请</el-option>
                    <el-option value="销假申请">销假申请</el-option>
                </el-select>
            </el-form-item>

            <!--以下三项仅在请假申请时显示-->
            <el-form-item v-if="isLeaveApplication" label="请假原因" label-width="30%">
                <el-select v-model="leaveApplication.reason">
                    <el-option value="病假">病假</el-option>
                    <el-option value="事假">事假</el-option>
                </el-select>
            </el-form-item>
            <el-form-item v-if="isLeaveApplication" label="详细描述" label-width="30%">
                <el-input type="textarea" v-model="leaveApplication.detail" />
            </el-form-item>
            <el-form-item v-if="isLeaveApplication" label="请假时间" label-width="30%">
                <el-date-picker
                    v-model="leaveTime"
                    type="daterange"
                    range-separator="To"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false; applicationPost()">发起申请</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
import {UserFilled, Position} from '@element-plus/icons-vue'
import instance from "../../api/DataAxios";
import router from "../../router";
import {reactive, ref, watch} from "vue";

const logout = () => {
    instance.post("/user/logout").then(
        response => {
            ElMessage.success("退出登录成功")
            sessionStorage.clear()
            router.push("/login")
        }
    )
}

let dialogFormVisible = ref(false)
let applicationKind = ref('请假申请')
let isLeaveApplication = ref(true)
let leaveTime = ref()
let leaveApplication = reactive({
    userId: sessionStorage.getItem("id"),
    reason: null,
    detail: null,
    startTime: null,
    endTime: null
})

const applicationPost = () => {
    
}

watch(applicationKind, async (newValue, oldValue) => {
    isLeaveApplication.value = (newValue === '请假申请')
})
</script>

<style scoped>
#menu {
    position: fixed;
    bottom: 65px;
    right: 30px;
}

</style>