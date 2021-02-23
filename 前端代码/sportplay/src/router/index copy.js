import Vue from 'vue'
import VueRouter from 'vue-router'
// 引入登录页面
import Login from '../components/Login'
// 引入主页
import Home from '../components/Home'

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
      component: Home
    }
  ]
  
})

const routes = [
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
    component: Home
  }
]

const router = new VueRouter({
  routes
})

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
   if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
   return originalPush.call(this, location).catch(err => err)
}

router.beforeEach((to, from, next) => {
  if (to.path == '/login') {
    next();
  }
  var tokenStr = window.sessionStorage.getItem('user') ;
  if (!tokenStr) {
    next();
  } else {
    next("/login");
  }
})

//export default router;







