<template>
  <!--引入布局 -->
  <el-container class="home-container">
    <!--头部 -->
    <el-header>
      <div class="head-div">
        <img src="../assets/image/7.png" alt id="headImage" />
        <span class="head-span">个人运动平台</span>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <!--主体 -->
    <el-container>
      <!--侧边栏 -->
      <el-aside :width="isCollapse?'64px':'200px'">
        <div class="toggle-button" @click="toggleCollapase()">|||</div>
        <el-menu background-color="#545c64" text-color="#fff" active-text-color="#409eff"
         class="el-menu" unique-opened :collapse="isCollapse" :collapse-transition="false" 
         :router="true" :default-active="activePath">
          <!--一级导航 -->
          <el-submenu :index="item.id + ''" v-for="item in menuList" :key="item.id">
            <template slot="title">
              <i :class="iconsObject[item.id]"></i>
              <span>{{item.title}}</span>
            </template>
            <!--二级导航 -->
            <el-menu-item :index="it.path + ''" v-for="it in item.subMenuList" :key="it.id" @click="saveNavState(it.path)">
              <template slot="title">
                <i :class="iconsObject[it.id]"></i>
                <span>
                  {{it.title}}
                </span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <!--主体内容 -->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {

  data(){
     return {
       // 菜单列表
       menuList: [],
       isCollapse: false,// 伸缩
       iconsObject: {
         '100':'iconfont iconquanxianguanli',
         '200':'iconfont iconyundong',
         '101':'iconfont iconyonghuliebiao',
         '102':'iconfont iconquanxian',
         '103':'iconfont icon51',
         '104':'iconfont iconshangpin',
         '201':'iconfont icondianlikepu',
         '202':'iconfont iconqialuli',
         '203':'iconfont iconyingyang',
       },
       activePath:'/welcome'//默认为空
     }
  },
  created(){
     // 查询menulist
     this.getMenuList();
     // 获取session中的路径
     this.activePath = window.sessionStorage.getItem('activePath');
  },
  methods: {
    logout() {
      window.sessionStorage.clear(); //清除session
      this.$router.push("/login");
    },
    // 获取导航栏方法
    async getMenuList(){
       const {data:res} = await this.$http.get("/getAllMenus");
       console.log(res);
       // 访问失败
       if (res.state != 200) {
         return this.$message.error("获取导航列表失败!!");
       } else {
         // 访问成功
         this.menuList = res.mainMenus;
       }
    },
    // 控制伸缩
    toggleCollapase(){
         this.isCollapse = !this.isCollapse;
    },
    // 保存路径
    saveNavState(activePath){
      window.sessionStorage.setItem('activePath',activePath);// 放到session中
      this.activePath = activePath;
    }
  },
  beforeRouteEnter: (to, from, next) => {
    if (!window.sessionStorage.getItem("user")) {
      alert("还没有登录，请先登录！");
      next("/login"); //转入login登录页面，登录成功后会将token存入localStorage
    } else {
      next();
    }
  },
};
</script>

<style scoped>
.home-container {
  height: 100%;
}

/**头部样式 */
.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0px;
  color: #fff;
  font-size: 20px;
}

.head-div {
  display: flex;
  align-items: center;
}

.head-span {
  margin-left: 15px;
}
/**侧边栏样式 */
.el-aside {
  background-color: #333744;
  color: #333;
  text-align: center;
  line-height: 200px;
}

/**侧边栏样式 */
.el-menu{
  border-right: none;
}

/**主体样式 */
.el-main {
  background-color: #eaedf1;
  color: #333;
  text-align: center;
  line-height: 160px;
}

#headImage {
  width: 65px;
  height: 55px;
}

/**按钮 */
.toggle-button{
  background-color: #4A5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  /**显示小手 */
  cursor: pointer;
}

</style>