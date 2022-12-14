import { createRouter, createWebHashHistory } from 'vue-router'


//定义路由组件
import PlainUserHome from '../pages/PlainUserHome.vue'
import AdminHome from '../pages/AdminHome.vue'
import Error from '../pages/Error.vue'
import Login from '../pages/Login.vue'
import NotFound from '../pages/404.vue'

import Front from '../pages/Front/Front.vue'
import EpidemicMap from '../pages/Epidemic/EpidemicMap.vue'
import Salary from '../pages/Finance/Salary.vue'
import Back from '../pages/Approval/Back.vue'
import Leave from '../pages/Approval/Leave.vue'
import Log from '../pages/Approval/Log.vue'
import UserCenter from '../pages/User/UserCenter.vue'
import Visualization from '../pages/User/Visualization.vue'
import DeptCenter from '../pages/Department/DeptCenter.vue'
import Publish from '../pages/Announcement/Publish.vue'
import Check from '../pages/Announcement/Check.vue'

import UserIndex from '../pages/PlainUser/Index.vue'
import AnnounceDetail from '../pages/PlainUser/AnnounceDetail.vue'

//定义一些路由
const routes = [
    {
        path: '/',
        component: Login
    },
    {
        path: '/login',
        name: 'login',
        component: Login,
    },
    {
        path: '/error',
        name: 'error',
        component: Error,
    },
    {
        path: '/404',
        name: 'notFound',
        component: NotFound,
        hidden: true
    },
    {
        path: '/:pathMath(.*)',
        redirect: '/404'
    },
    {
        path: '/plainUser',
        name: 'plainUser',
        component: PlainUserHome,
        children: [
            {
                path: '',
                name: 'userIndex',
                component: UserIndex
            },
            {
                path: 'index',
                component: UserIndex
            },
            {
                path: 'detail',
                component: AnnounceDetail
            },
        ]
    },
    {
        path: '/admin',
        name: 'adminHome',
        component: AdminHome,
        children: [
            {
                path: '',
                name: 'adminIndex',
                component: Front,
            },
            {
                path: 'index',
                component: Front,
            },
            {
                path: 'epidemicMap',
                component: EpidemicMap,
            },
            {
                path: 'approve',
                children: [
                    {
                        path: 'leave',
                        component: Leave,
                    },
                    {
                        path: 'back',
                        component: Back,
                    },
                    {
                        path: 'log',
                        component: Log,
                    },
                ]
            },
            {
                path: 'user',
                children: [
                    {
                        path: 'userCenter',
                        component: UserCenter,
                    },
                    {
                        path: 'visualization',
                        component: Visualization,
                    },
                ]
            },
            {
                path: 'dept',
                children: [
                    {
                        path: 'deptCenter',
                        component: DeptCenter,
                    },
                ]
            },
            {
                path: 'announce',
                children: [
                    {
                        path: 'publish',
                        component: Publish,
                    },
                    {
                        path: 'check',
                        component: Check,
                    },
                ]
            },
        ]
    }
]


//创建路由实例
const router = createRouter({
    history: createWebHashHistory(),
    routes,
})


//处理页面错误
router.onError = function () {
    return '/error'
}



//导航守卫(全局前置守卫) -> 登录验证
router.beforeEach(async (to, from) => {
    //进入管理后台页面的判断
    if (!sessionStorage.getItem("isAdmin") && to.name !== 'login' && (to.fullPath.split('/')[1] === 'admin')) {
        return {name: 'login'}
    }
    //进入页面用户的判断
    if (!sessionStorage.getItem("isPlainUser") && to.name !== 'login' && (to.fullPath.split('/')[1] === 'plainUser')) {
        return {name: 'login'}
    }
})


export default router
