<template>

    <div class="custom-form">
        <el-form :model="queryUser" :inline="true" class="query-form" id="my-query-form">
            <div class="custom-not-button-group">
                <el-form-item label="姓名">
                    <el-input v-model="queryUser.userName" class="name-input" placeholder="支持模糊匹配"></el-input>
                </el-form-item>
                <el-form-item v-show="isOpen" label="性别">
                    <el-select v-model="queryUser.userSex" class="select-input" placeholder="请选择">
                        <el-option v-for="sex in sexs" :value="sex">{{ sex }}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item v-show="isOpen" label="年龄">
                    <el-input v-model="queryUser.userAgeStart" class="age-input"></el-input>
                    <span>&nbsp;-&nbsp;</span>
                    <el-input v-model="queryUser.userAgeEnd" class="age-input"></el-input>
                </el-form-item>
                <el-form-item v-show="isOpen" label="部门">
                    <el-select v-model="queryUser.deptName" class="select-input" placeholder="请选择">
                        <el-option v-for="dept in depts" :value="dept">{{ dept }}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item v-show="isOpen" label="角色">
                    <el-select v-model="queryUser.roleName" class="select-input" placeholder="请选择">
                        <el-option v-for="role in roles" :value="role">{{ role }}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item v-show="isOpen" label="状态">
                    <el-select v-model="queryUser.userState" class="select-input" placeholder="请选择">
                        <el-option v-for="state in states" :value="state">{{ state }}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item v-show="isOpen" label="家庭住址">
                    <el-input v-model="queryUser.userProvince" class="address-input" placeholder="省"></el-input>
                    <el-input v-model="queryUser.userCity" class="address-input" placeholder="市"></el-input>
                    <el-input v-model="queryUser.userCommunity" class="address-input" placeholder="县 / 区"></el-input>
                </el-form-item>
                <el-form-item v-show="isOpen" label="入职时间">
                    <el-date-picker type="daterange"
                                    unlink-panels
                                    range-separator="至"
                                    start-placeholder="开始日期"
                                    end-placeholder="结束日期"
                                    v-model="userInter"
                    />
                </el-form-item>
            </div>
            <el-form-item class="custom-button-group">
                <el-button type="primary" @click="query()">查询</el-button>
                <el-button type="danger" @click="resetForm()">重置</el-button>
                <el-button type="primary" @click="dialogFormVisible = true">添加员工</el-button>
                <el-button type="danger" style="margin-left: 12px;" @click="deleteSelectUser()">删除选中员工</el-button>
                <span class="custom-span-switch" @click="isOpen = !isOpen"><span v-show="isOpen">合并<el-icon><ArrowUp /></el-icon></span><span v-show="!isOpen">展开<el-icon><ArrowDown /></el-icon></span></span>
            </el-form-item>
        </el-form>
    </div>

    <hr>

    <div class="custom-table">
        <el-table :data="userinfoTable" border class="user-table" @selection-change="handleSelectChange">
            <el-table-column type="selection" width="55"/>

            <el-table-column prop="userName" label="姓名"/>
            <el-table-column prop="userSex" label="性别" width="70"/>
            <el-table-column prop="userAge" label="年龄" width="60"/>
            <el-table-column prop="deptName" label="部门"/>
            <el-table-column prop="roleName" label="角色"/>
            <el-table-column prop="userEmail" label="联系方式" width="200"/>
            <el-table-column prop="userAddress" label="家庭住址" width="170"/>
            <el-table-column prop="userInterTime" label="入职时间" width="160"/>
            <el-table-column prop="userState" label="状态"/>

            <el-table-column label="相关操作" width="120" fixed="right">
                <template #default="scope">
                    <el-button @click="editDialogFormVisible = true; editUser(scope.row)" type="primary" :icon="Edit" circle/>
                    <el-button @click="deleteUser(scope.row)" type="danger" :icon="Delete" circle/>
                </template>
            </el-table-column>
        </el-table>
    </div>

    <div class="custom-pagination">
        <el-pagination
            v-model:currentPage="currentPage"
            v-model:page-size="pageSize"
            :page-sizes="[5, 10, 15, 20]"
            :background="true"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalNum"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
        />
    </div>

    <el-form :inline="true" style="float: right; margin-top: 9px;">
        <el-form-item>
            <el-button type="success" @click="downloadUserInfo">下载员工信息Excel</el-button>
        </el-form-item>
        <el-form-item label="从excel批量导入员工">
            <input @change="uploadUserInfo" type="file" style="width: 120px; border-radius: 5px;" />
        </el-form-item>
    </el-form>

    <el-dialog v-model="dialogFormVisible" title="添加员工">
        <el-form :model="addUserForm" id="add-user-form">
            <el-form-item label="姓名" :label-width="formLabelWidth">
                <el-input v-model="addUserForm.userName" placeholder="请输入员工姓名" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="性别" :label-width="formLabelWidth">
                <el-select v-model="addUserForm.userSex" placeholder="请选择员工性别">
                    <el-option v-for="sex in sexs" :value="sex">{{ sex }}</el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="年龄" :label-width="formLabelWidth">
                <el-input v-model="addUserForm.userAge" placeholder="请输入员工年龄" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth">
                <el-input v-model="addUserForm.userEmail" placeholder="请输入员工邮箱" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" :label-width="formLabelWidth">
                <el-input v-model="addUserForm.userPassword" placeholder="请输入员工密码" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="部门" :label-width="formLabelWidth">
                <el-select v-model="addUserForm.deptName" placeholder="请选择">
                    <el-option v-for="dept in depts" :value="dept">{{ dept }}</el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="角色" :label-width="formLabelWidth">
                <el-select v-model="addUserForm.roleName" placeholder="请选择">
                    <el-option v-for="role in roles" :value="role">{{ role }}</el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="家庭住址" :label-width="formLabelWidth">
                <el-input v-model="addUserForm.userProvince" placeholder="省"></el-input>
                <el-input v-model="addUserForm.userCity" placeholder="市"></el-input>
                <el-input v-model="addUserForm.userCommunity" placeholder="县 / 区"></el-input>
            </el-form-item>
            <el-form-item label="入职时间" :label-width="formLabelWidth">
                <el-date-picker type="date"
                                placeholder="请选择入职时间"
                                v-model="addUserForm.userInterTime"
                />
            </el-form-item>
        </el-form>
        <template #footer>
                    <span class="dialog-footer">
                        <el-button @click="dialogFormVisible = false">取消</el-button>
                        <el-button type="primary" @click="dialogFormVisible = false; addUserPost()">添加</el-button>
                    </span>
        </template>
    </el-dialog>
    <el-drawer
        ref="drawerRef"
        v-model="editDialogFormVisible"
        title="修改员工信息"
        direction="rtl"
        custom-class="demo-drawer"
    >
        <div class="demo-drawer__content">
            <el-form :model="editUserForm">
                <el-form-item label="姓名" :label-width="formLabelWidth">
                    <el-input v-model="editUserForm.userName" placeholder="请输入员工姓名"/>
                </el-form-item>
                <el-form-item label="性别" :label-width="formLabelWidth">
                    <el-select v-model="editUserForm.userSex" placeholder="请选择员工性别">
                        <el-option v-for="sex in sexs" :value="sex">{{ sex }}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="年龄" :label-width="formLabelWidth">
                    <el-input v-model="editUserForm.userAge" placeholder="请输入员工年龄"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" :label-width="formLabelWidth">
                    <el-input v-model="editUserForm.userEmail" placeholder="请输入员工邮箱"></el-input>
                </el-form-item>
                <el-form-item label="密码" :label-width="formLabelWidth">
                    <el-input v-model="editUserForm.userPassword" placeholder="请输入员工新密码"></el-input>
                </el-form-item>
                <el-form-item label="部门" :label-width="formLabelWidth">
                    <el-select v-model="editUserForm.deptName" placeholder="请选择">
                        <el-option v-for="dept in depts" :value="dept">{{ dept }}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="角色" :label-width="formLabelWidth">
                    <el-select v-model="editUserForm.roleName" placeholder="请选择">
                        <el-option v-for="role in roles" :value="role">{{ role }}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态" :label-width="formLabelWidth">
                    <el-select v-model="editUserForm.userState" placeholder="请选择">
                        <el-option v-for="state in states" :value="state">{{ state }}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="家庭住址" :label-width="formLabelWidth">
                    <el-input v-model="editUserForm.userProvince" placeholder="省"></el-input>
                    <el-input v-model="editUserForm.userCity" placeholder="市"></el-input>
                    <el-input v-model="editUserForm.userCommunity" placeholder="县 / 区"></el-input>
                </el-form-item>
                <el-form-item label="入职时间" :label-width="formLabelWidth">
                    <el-date-picker type="date"
                                    placeholder="请选择入职时间"
                                    v-model="editUserForm.userInterTime"
                    />
                </el-form-item>
            </el-form>
            <div class="demo-drawer__footer">
                <el-button @click="editDialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="editDialogFormVisible = false; editUserPost()">确认修改</el-button>
            </div>
        </div>
    </el-drawer>
</template>

<script setup>
import {Edit, Delete} from '@element-plus/icons-vue'
import {ElMessage, ElDrawer, ElMessageBox} from 'element-plus'
import axios from 'axios'
import instance from '../../api/DataAxios'
import {onMounted, reactive, ref} from "vue"
import {matchEmail, matchNumber} from "../../utils/RegUtils"

let userinfoTable = ref([])
let userinfo = reactive([])
let userInter = ref(null)
let queryUser = reactive({
    userName: "",
    userSex: "",
    userAgeStart: null,
    userAgeEnd: null,
    deptName: "",
    roleName: "",
    userState: "",
    userProvince: "",
    userCity: "",
    userCommunity: "",
    userInterTimeStart: "",
    userInterTimeEnd: "",
})
let sexs = ref(["男", "女", "非二元性别"])
let depts = ref([])
let roles = ref([])
let states = ref(["正常", "请假中"])

let currentPage = ref(1)
let pageSize = ref(10)
let totalNum = ref(0)

const handleSizeChange = (ps) => {
    pageSize.value = ps
    refresh_user_info_table()
}

const handleCurrentChange = (cp) => {
    currentPage.value = cp
    refresh_user_info_table()
}

const refresh_user_info_table = () => {
    userinfoTable.value = userinfo.slice(pageSize.value * (currentPage.value - 1), pageSize.value * currentPage.value)
}

onMounted(() => {
    axios.all([getAllUserCenterUsers(), getAllRoleName(), getAllDeptName(), getUserTotalNum()])
})

function getUserTotalNum() {
    return instance.get("/userCenter/getTotalNum").then(
        response => {
            totalNum.value = response.data
        }
    )
}

function getAllDeptName() {
    return instance.get("/userCenter/getAllDeptName").then(
        response => {
            depts.value = []
            for (let dept of response.data) {
                depts.value.push(dept)
            }
        }
    )
}

function query() {
    if (userInter.value !== null) {
        queryUser.userInterTimeStart = userInter.value[0]
        queryUser.userInterTimeEnd = userInter.value[1]
    }
    instance.post("/userCenter/queryUserCenterUsers", queryUser).then(
        response => {
            userinfo.splice(0, userinfo.length)
            for (let user of response.data) {
                let singleUser = {
                    userId: user.userId,
                    userName: user.userName,
                    userSex: user.userSex,
                    userAge: user.userAge,
                    deptName: user.deptName,
                    roleName: user.roleName,
                    userEmail: user.userEmail,
                    userAddress: '',
                    userInterTime: new Date(user.userInterTime).toLocaleDateString(),
                    userState: user.userState
                }
                if (user.userProvince !== null && user.userProvince !== '' && user.userProvince !== undefined) singleUser.userAddress = user.userProvince;
                if (user.userCity !== null && user.userCity !== '' && user.userCity !== undefined) singleUser.userAddress += user.userCity;
                if (user.userCommunity !== null && user.userCommunity !== '' && user.userCommunity !== undefined) singleUser.userAddress += user.userCommunity;
                userinfo.push(singleUser)
            }
            totalNum.value = userinfo.length
            refresh_user_info_table()
        }
    )
}

function resetForm() {
    userInter.value = null
    queryUser.userName = null
    queryUser.userSex = null
    queryUser.userAgeStart = null
    queryUser.userAgeEnd = null
    queryUser.deptName = null
    queryUser.roleName = null
    queryUser.userState = null
    queryUser.userProvince = null
    queryUser.userCommunity = null
    queryUser.userInterTimeStart = null
    queryUser.userInterTimeEnd = null
}

function getAllUserCenterUsers() {
    return instance.get("/userCenter/getAllUserCenterUsers").then(
        response => {
            userinfo.splice(0, userinfo.length)
            for (let user of response.data) {
                let singleUser = {
                    userId: user.userId,
                    userName: user.userName,
                    userSex: user.userSex,
                    userAge: user.userAge,
                    deptName: user.deptName,
                    roleName: user.roleName,
                    userEmail: user.userEmail,
                    userAddress: '',
                    userInterTime: new Date(user.userInterTime).toLocaleDateString(),
                    userState: user.userState
                }
                if (user.userProvince !== null && user.userProvince !== '' && user.userProvince !== undefined) singleUser.userAddress = user.userProvince;
                if (user.userCity !== null && user.userCity !== '' && user.userCity !== undefined) singleUser.userAddress += user.userCity;
                if (user.userCommunity !== null && user.userCommunity !== '' && user.userCommunity !== undefined) singleUser.userAddress += user.userCommunity;
                userinfo.push(singleUser)
            }
            getUserTotalNum()
            refresh_user_info_table()
        }
    )
}

function getAllRoleName() {
    return instance.get("/userCenter/getAllRoleName").then(
        response => {
            for (let role of response.data) {
                roles.value.push(role)
            }
        }
    )
}


const formLabelWidth = '140px'
const dialogFormVisible = ref(false)
const addUserForm = reactive({
    userName: null,
    userSex: null,
    userAge: null,
    userEmail: null,
    userPassword: null,
    deptName: null,
    roleName: null,
    userState: "正常",
    userProvince: null,
    userCity: null,
    userCommunity: null,
    userInterTime: null,
})
function addUserPost() {
    if (matchEmail(addUserForm.userEmail) && matchNumber(addUserForm.userAge) && addUserForm.userAge > 0 && addUserForm.userName !== null && addUserForm.userSex !== null && addUserForm.userPassword !== null && addUserForm.deptName !== null && addUserForm.roleName !== null && addUserForm.userCity !== null && addUserForm.userInterTime !== null) {
        instance.post("/userCenter/addNewUser", addUserForm).then(
            response => {
                if (response.data) {
                    ElMessage.success("添加成功")
                    userinfo.splice(0,userinfo.length)
                    getAllUserCenterUsers()
                } else {
                    ElMessage.warning("添加失败")
                }
            }
        )
    } else {
        ElMessage.warning("请输入正确的信息")
    }
}

const editDialogFormVisible = ref(false)
let editUserForm = reactive({
    userId: null,
    userName: null,
    userSex: null,
    userAge: null,
    deptName: null,
    roleName: null,
    userEmail: null,
    userPassword: null,
    userProvince: null,
    userCity: null,
    userCommunity: null,
    userInterTime: null,
    userState: null
})
function editUser(row) {
    instance.get("/userCenter/getOneUserById/" + row.userId).then(
        response => {
            const data = response.data
            editUserForm.userId = data.userId
            editUserForm.userName = data.userName
            editUserForm.userSex = data.userSex
            editUserForm.userAge = data.userAge
            editUserForm.deptName = data.deptName
            editUserForm.roleName = data.roleName
            editUserForm.userEmail = data.userEmail
            editUserForm.userProvince = data.userProvince
            editUserForm.userCity = data.userCity
            editUserForm.userCommunity = data.userCommunity
            editUserForm.userInterTime = new Date(data.userInterTime)
            editUserForm.userState = data.userState
        }
    )
}
function editUserPost() {
    if (matchEmail(editUserForm.userEmail) && matchNumber(editUserForm.userAge) && editUserForm.userName !== null && editUserForm.userCity !== null && editUserForm.userInterTime !== null) {
        instance.post("/userCenter/editOneUser" ,editUserForm).then(
            response => {
                if (response.data) {
                    ElMessage.success("修改成功")
                    userinfo.splice(0,userinfo.length)
                    getAllUserCenterUsers()
                } else {
                    ElMessage.warning("修改失败")
                }
            }
        )
    } else {
        ElMessage.warning("请输入正确的信息")
    }
}

function deleteUser(row) {
    ElMessageBox.confirm(
        '确定删除此员工吗？想想它的优点~~',
        '员工删除提示', {
            confirmButtonText: '确定删除',
            cancelButtonText: '我再想想',
            type: 'warning',
        }
    ).then(() => {
        instance.get("/userCenter/deleteOneUserById/" + row.userId).then(
            respone => {
                ElMessage({
                    type: 'success',
                    message: '员工已删除',
                })
                userinfo.splice(0,userinfo.length)
                getAllUserCenterUsers()
            }
        )
    }).catch(() => {
        ElMessage({
            type: 'info',
            message: '取消删除，真有你的',
        })
    })
}

let deleteUserIdList = ref([])
const handleSelectChange = (users) => {
    deleteUserIdList.value = []
    for (let user of users) {
        deleteUserIdList.value.push(user.userId)
    }
}
function deleteSelectUser() {
    for (let id of deleteUserIdList.value) {
        instance.get("/userCenter/deleteOneUserById/" + id).then(
            respone => {
                ElMessage({
                    type: 'success',
                    message: '员工已删除',
                })
                userinfo.splice(0, userinfo.length)
                getAllUserCenterUsers()
            }
        )
    }
}


function downloadUserInfo() {
    window.open("http://localhost:12280/excel/download")
}
const uploadUserInfo = (event) => {
    let formData = new FormData()
    formData.append("file", event.target.files[0])
    instance.post("/excel/upload", formData, {
        headers: {
            "Content-Type": "multipart/form-data",
        }
    }).then(
        response => {
            getAllUserCenterUsers()
        }
    )
}



//美化页面JS代码
let isOpen = ref(false)
import eleResizeDetector from "element-resize-detector"
import router from "../../router";
onMounted(() => {
    let erd = eleResizeDetector()
    erd.listenTo(document.querySelector(".custom-not-button-group"), element => {
        document.querySelector(".user-table").style.height = "calc(100vh - " + (213 + element.offsetHeight) + "px)"
    })
})

</script>

<style scoped>
.el-input {
    width: 300px;
}
.el-select {
    width: 300px;
}

.custom-form {
    padding-right: 330px;
}

.custom-button-group {
    position: absolute;
    right: 10px;
    top: 115px;
}

.user-table {
    width: 100%;
}

.name-input {
    width: 120px;
}

.address-input {
    width: 70px;
}

.select-input {
    width: 120px;
}

.age-input {
    width: 50px;
}

.custom-span-switch {
    margin-left: 10px;
    cursor: pointer;
    color: #51afff;
}

.custom-pagination {
    margin-top: 10px;
    float: left;
}
</style>