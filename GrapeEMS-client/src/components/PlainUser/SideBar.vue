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

            <!--以下一项仅在销假申请时显示-->
            <el-form-item v-if="!isLeaveApplication" label="销哪次假" label-width="30%">
                <el-select v-model="backApplication.leaveApplicationId">
                    <el-option v-for="item in leaveApplicationNotApprove" :value="item.leaveApplicationId">{{new Date(item.leaveApplicationCreateTime).toLocaleString()}}</el-option>
                </el-select>
            </el-form-item>

            <!--以下三项仅在请假申请时显示-->
            <el-form-item v-if="isLeaveApplication" label="请假原因" label-width="30%">
                <el-select v-model="leaveApplication.leaveApplicationReason">
                    <el-option value="病假">病假</el-option>
                    <el-option value="事假">事假</el-option>
                </el-select>
            </el-form-item>
            <el-form-item v-if="isLeaveApplication" label="详细描述" label-width="30%">
                <el-input type="textarea" v-model="leaveApplication.leaveApplicationDetail" />
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
import {onMounted, reactive, ref, watch} from "vue";
import {ElMessage} from "element-plus";

onMounted(() => {
    instance.get("/plainUser/approve/getLeaveApplicationByUserId/" + sessionStorage.getItem("userId")).then(
        response => {
            leaveApplicationNotApprove.value.splice(0, leaveApplicationNotApprove.value.length)
            const data = response.data
            for (let d of data) {
                if (d.leaveApplicationIsApprove === 1) {
                    leaveApplicationNotApprove.value.push(d)
                }
            }
        }
    )
})

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
let leaveTime = ref(null)
let leaveApplication = reactive({
    userId: sessionStorage.getItem("userId"),
    leaveApplicationReason: null,
    leaveApplicationDetail: null,
    leaveApplicationLeaveStartTime: null,
    leaveApplicationLeaveEndTime: null
})
let leaveApplicationNotApprove = ref([])
let backApplication = reactive({
    userId: sessionStorage.getItem("userId"),
    leaveApplicationId: null,
})

function applicationPost() {
    if (isLeaveApplication.value) {
        //请假申请
        if (leaveTime.value !== null && leaveApplication.leaveApplicationReason !== null && leaveApplication.leaveApplicationDetail !== null) {
            leaveApplication.leaveApplicationLeaveStartTime = leaveTime.value[0]
            leaveApplication.leaveApplicationLeaveEndTime = leaveTime.value[1]
            instance.post("/plainUser/approve/leaveApprove", leaveApplication).then(
                response => {
                    if (response.data) {
                        ElMessage.success("申请成功")
                        leaveApplication.leaveApplicationReason = null
                        leaveApplication.leaveApplicationDetail = null
                        leaveTime.value = null
                    } else {
                        ElMessage.warning("申请失败")
                    }
                }
            )
        } else {
            ElMessage.warning("请检查填写信息是否完整或信息是否有误")
        }
    } else {
        if (backApplication.leaveApplicationId !== null) {
            //销假申请
            instance.post("/plainUser/approve/backApprove", backApplication).then(
                response => {
                    if (response.data) {
                        ElMessage.success("申请成功")
                        backApplication.leaveApplicationId = null
                    } else {
                        ElMessage.error("申请失败")
                    }
                }
            )
        } else {
            ElMessage.warning("请检查填写信息是否完整")
        }
    }
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