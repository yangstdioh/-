<template>
   <div class="login_container">
         <div class="login_box">
              <div class="avatar_box">
                    <img src="../assets/image/40513.jpg" alt="" id="login_jpg"/>
              </div>  
              <el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" class="login_form" label-width="0">
                 <!--用戶名-->
                 <el-form-item prop="userName">
                      <el-input v-model="loginForm.userName" class="button" prefix-icon="iconfont icondenglu1" type="text" placeholder="请输入用户名"></el-input>
                 </el-form-item>
                 <!--密碼-->
                 <el-form-item prop="password">
                      <el-input v-model="loginForm.password" class="button" prefix-icon="iconfont iconmima" type="password" placeholder="请输入密码"></el-input>
                 </el-form-item>

                 <!--按鈕-->
                 <el-form-item>
                      <el-button type="primary" @click = "login()">登 录</el-button>
                      <el-button type="info" @click = "resetForm()" >重 置</el-button>
                 </el-form-item>
              </el-form>
         </div>
   </div>
</template>

<script>
export default {
  data() {
    return {
      // 表单数据
      loginForm: {
        userName: "",
        password: ""
      },
      // 验证对象
      loginRules: {
        // 验证用户名
        userName: [
            { required: true, message: '请输入用户名称', trigger: 'blur' },
            { min: 2, max: 12, message: '用户名的长度在 2 到 12 个字符', trigger: 'blur' }
        ],
        password: [
              { required: true, message: '请输入密码', trigger: 'blur' },
              { min: 4, max: 10, message: '密码的长度在 4 ~ 10 个字符', trigger: 'blur' } 
        ]
      }
    }
  },
  // 按钮事件
  methods: {
    // 重置表单
    resetForm(){
      this.$refs.loginFormRef.resetFields();
    },
    // 处理登录
    login(){
      // 验证校验规则
      this.$refs.loginFormRef.validate(async valid =>{
        //console.log(valid);
        if (! valid) return;//验证失败
        const {data:res} =  await this.$http.post("/login", this.loginForm);
        //console.log(res);
        if ( res.flag == "ok"){
             this.$message.success("登录成功!");//成功提示
             window.sessionStorage.setItem("user",res.user);// 存储user对象
             this.$router.push({path:'/home'});//跳转到首页
        } else {
             this.$message.error("用户名或者密码错误!");// 失败提示
        }
      })
      
    }
  }    
}
</script>

<style  scoped>

    
  .login_container{
    background-color: #2b4b6b;
    height: 100%;
  }
   
  .login_box{
    width: 450px;
    height: 300px;
    background-color: #ffff;
    border-radius: 3px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
    
  } 

  .avatar_box{
      width: 130px;
      height: 130px;
      border: 1px solid #eeee;
      border-radius: 50%;
      padding: 10px;
      box-shadow: 0 0 10px #ddd;
      left: 50%;
      transform: translate(-50%,-50%);
      background-color: #0ee;
      position: absolute;
  }
  img{
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #0ee;
  }

  .button{
    display: flex;
    text-justify: flex-end;;
  }

  .login_form{
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;
  }

</style>
