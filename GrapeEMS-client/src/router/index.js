import { createRouter, createWebHashHistory } from 'vue-router'


//定义路由组件
import Login from '../pages/Login.vue'
import Home from '../pages/Home.vue'
import Error from '../pages/Error.vue'

import Front from '../pages/Front/Front.vue'
import Salary from '../pages/Finance/Salary.vue'
import Back from '../pages/Approval/Back.vue'
import Leave from '../pages/Approval/Leave.vue'
import Log from '../pages/Approval/Log.vue'
import MoreWork from '../pages/Approval/MoreWork.vue'
import User from '../pages/User/User.vue'
import Dept from '../pages/Department/Dept.vue'
import Announce from '../pages/Announcement/Announce.vue'
import Check from '../pages/Announcement/Check.vue'


//定义一些路由
const routes = [
    {
        path: '/',
        name: 'home',
        component: Home,
        children: [
            {
                path: '',
                component: Front,
            },
            {
                path: 'index',
                component: Front,
            },
            {
                path: 'salary',
                component: Salary,
            },
            {
                path: 'leave',
                component: Leave,
            },
            {
                path: 'back',
                component: Back,
            },
            {
                path: 'morework',
                component: MoreWork,
            },
            {
                path: 'log',
                component: Log,
            },
            {
                path: 'user',
                component: User,
            },
            {
                path: 'dept',
                component: Dept,
            },
            {
                path: 'announce',
                component: Announce,
            },
            {
                path: 'check',
                component: Check,
            }
        ]
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
    if (!localStorage.getItem("isAdmin") && to.name !== 'login') {
        return {name: 'login'}
    }
})


export default router
