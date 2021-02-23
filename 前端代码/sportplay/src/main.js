// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// 添加全局样式
import './assets/css/global.css'
// 引入iconfont图标样式
import './assets/font/iconfont.css'

// 引入element ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// 导入axios
import axios from 'axios'
// 引入less
import less from 'less'


Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(router);
Vue.use(less);

// 使用axios
Vue.prototype.$http = axios;
// 设置访问根路径
axios.defaults.baseURL = "http://localhost:8787"


/* eslint-disable no-new */
new Vue({
  el: '#app',
  // 配置路由
  router,
  components: { App },
  template: '<App/>'
})

router.beforeEach((to,form,next) =>{
  //如果进入到的路由是登录页或者注册页面，则正常展示
  if(to.path == '/login'){     
      next();
  }else if( !(window.sessionStorage.getItem('user')) ){
      alert("还没有登录，请先登录！");
      next('/login');     //转入login登录页面，登录成功后会将token存入localStorage
  }else{
      next();
  }
})
