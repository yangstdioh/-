import Vue from 'vue'
import VueRouter from 'vue-router'
// 引入登录页面
import Login from '../components/Login'
// 引入主页
import Home from '../components/Home'
import Welcome from '../components/Welcome.vue'
// 导入用户列表
import UserList from '../components/user/UserList.vue'

// 安装路由
Vue.use(VueRouter)

// 配置导出路由
export default new VueRouter({
  mode: 'history',
  routes: [
    {
      // 路由路径
      path: '/login',
      name: 'login',
      // 跳转的组件
      component: Login
    },
    {
      // 路由路径
      path: '/',
      redirect: "/login"
    },
    {
      // 首页
      path: '/home',
      component: Home,
      redirect:"/welcome",
      children:[
        {
          // 路径
          path: '/welcome',
          component: Welcome
        },
        {
          // 用户列表
          path: '/user',
          component: UserList
        },
        {
          // 路径
          path: '/welcome',
          component: Welcome
        }
      ]
    },
    {
      // 修改权限
      path: '/rights',
      redirect:"/welcome",
      component: Home
    },
    {
      //  运动模块
      path: '/sport',
      redirect:"/welcome",
      component: Home
    },
    {
      // 商品模块
      path: '/goods',
      redirect:"/welcome",
      component: Home
    },
    {
      // 运动科普
      path: '/Introduction',
      redirect:"/welcome",
      component: Home
    },
    {
      // 卡路里
      path: '/calories',
      redirect:"/welcome",
      component: Home
    },
    {
      // 营养配餐
      path: '/food',
      redirect:"/welcome",
      component: Home
    },
  ]
  
})









