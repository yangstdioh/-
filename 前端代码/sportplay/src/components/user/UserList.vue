<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{path:'/home'}">首页</el-breadcrumb-item>
        <el-breadcrumb-item>权限管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 用户列表主体部分-->
        <el-card>
             <!-- 搜索区域-->
             <el-row :gutter="25">
                <el-col :span="15">
                  <el-input placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getUserList">
                     <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
                  </el-input>
                </el-col>
                <el-col :span="4">
                     <el-button type="primary" @click="addDialogVisible = true">添加用户</el-button>
                </el-col>
             </el-row>
             <!-- 用户列表部分-->
            <el-table :data="userList" border stripe >
                <el-table-column label="用户ID" prop="id" width="80%"></el-table-column><!-- 索引列-->
                <el-table-column label="用户名" prop="userName"></el-table-column>
                <el-table-column label="邮箱" prop="email"></el-table-column>
                <el-table-column label="角色" prop="role"></el-table-column>
                <el-table-column label="状态" prop="state">
                    <!--作用域插槽 -->
                    <template slot-scope="scope">
                        <el-switch v-model="scope.row.state" @change="userStateChange(scope.row)"></el-switch>
                    </template>
                </el-table-column>
                <el-table-column label="操作" >
                    
                    <template slot-scope="scope">
                        <!--修改 -->
                        <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
                        <!--删除 -->
                        <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteUser(scope.row.id)"></el-button>
                        <!--权限 --> 
                        <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
                        
                    </template>
                </el-table-column>
           </el-table>  
           <p></p>
           <!-- 分页部分-->  
            <div>
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="queryInfo.pageNum"
                :page-sizes="[1, 2, 5, 10]"
                :page-size="queryInfo.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
            </div>
        </el-card>

        <!-- 新增用户区域-->
        <el-dialog title="添加用户" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
            <el-form :model="addForm" :rules="addUserFormRules" ref="addFormRef" label-width="70px">
              <!-- 用户名-->
              <el-form-item label="用户名" prop="userName">
                 <el-input v-model="addForm.userName"></el-input>
              </el-form-item>
              <!-- 密码-->
              <el-form-item label="密码" prop="password">
                 <el-input v-model="addForm.password"></el-input>
              </el-form-item>
              <!-- 邮箱-->
              <el-form-item label="邮箱" prop="email">
                 <el-input v-model="addForm.email"></el-input>
              </el-form-item>
            </el-form>

            <!-- 内容底部区域-->
            <span slot="footer" class="dialog-footer">
                  <el-button @click="addDialogVisible = false">取 消</el-button>
                  <el-button type="primary" @click="addUser">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 修改区域-->
        <el-dialog title="修改用户" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
            <el-form :model="editForm" :rules="editUserFormRules" ref="editUserFormRef" label-width="70px">
              <!-- 用户名-->
              <el-form-item label="用户名" prop="userName">
                 <el-input v-model="editForm.userName" disabled></el-input>
              </el-form-item>
              <!-- 密码-->
              <el-form-item label="密码" prop="password">
                 <el-input v-model="editForm.password"></el-input>
              </el-form-item>
              <!-- 邮箱-->
              <el-form-item label="邮箱" prop="email">
                 <el-input v-model="editForm.email"></el-input>
              </el-form-item>
            </el-form>

            <!-- 内容底部区域-->
            <span slot="footer" class="dialog-footer">
                  <el-button @click="editDialogVisible = false">取 消</el-button>
                  <el-button type="primary" @click="editUser">修 改</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    created(){
       this.getUserList();
    },
    data(){
        return {
            queryInfo: {
                query: "",
                pageNum: 1,
                pageSize:5,
            },
            userList: [],// 用户列表
            total: 0 ,// 总记录数
            addDialogVisible: false,//对话框状态
            // 添加用户表单
            addForm: {
                userName:'',
                password:'',
                email:''
            },
            // 修改用户信息
            editForm: {
                userName:'',
                password:'',
                email:''
            },
            // 显示/隐藏修改用户栏
            editDialogVisible: false,
            // 添加表单验证
            addUserFormRules: {
               userName: [
                      { required: true, message: '请输入用户名', trigger: 'blur' },
                      { min: 2, max: 10, message: '用户名长度在 2 ~ 10 个字符', trigger: 'blur' }
               ],
               password: [
                      { required: true, message: '请输入密码', trigger: 'blur' },
                      { min: 6, max: 8, message: '密码长度在 6 ~ 8 个字符', trigger: 'blur' }
               ],
               email: [
                      { required: true, message: '请输入邮箱', trigger: 'blur' },
                      { min: 5, max: 15, message: '邮箱长度在 5 到 15 个字符', trigger: 'blur' }
               ],
            },
            // 修改表单验证
            editUserFormRules: {
               password: [
                      { required: true, message: '请输入密码', trigger: 'blur' },
                      { min: 6, max: 8, message: '密码长度在 6 ~ 8 个字符', trigger: 'blur' }
               ],
               email: [
                      { required: true, message: '请输入邮箱', trigger: 'blur' },
                      { min: 5, max: 15, message: '邮箱长度在 5 到 15 个字符', trigger: 'blur' }
               ],
            },
        }
    },
    methods: {
        // 获取所有用户
        async getUserList(){
          const {data:res} = await this.$http.get("/getUserList",{params:this.queryInfo});
          console.log(res);
          this.userList = res.users;// 用户列表数据
          this.total = res.userCount;// 总用户数
        },
        // 最大数
        handleSizeChange(newSize){
          this.queryInfo.pageSize = newSize;
          this.getUserList();
        },
        // pageNum的触发动作
        handleCurrentChange(newPageNum){
            this.queryInfo.pageNum = newPageNum;
            this.getUserList();
        },
        // 更新用户状态
        async userStateChange(userInfo){
            const {data:res} = await this.$http.put('/updateState?id='+userInfo.id+'&state='+userInfo.state);
            if (res != "success"){
                userInfo.id = !userInfo.id;
                return this.$message.console.error("操作失败!!");
            }
            this.$message.success("操作成功!");
        },
        // 监听添加用户
        addDialogClosed(){
           this.$refs.addFormRef.resetFields();
        },
        // 添加新用户
        addUser(){
            this.$refs.addFormRef.validate(async valid=>{
                // 验证失败
                if(!valid) return;

                const {data:res} = await this.$http.post("addUser",this.addForm);

                if (res != "success"){
                    return this.$message.console.error("操作失败!!");
                }
                this.$message.success("操作成功!");
                // 隐藏添加用户窗口
                this.addDialogVisible = false;
                // 重新查询用户
                this.getUserList();

            });
        },
        // 根据ID删除用户
        async deleteUser(id){
            const confirmResult = await this.$confirm('此操作将永久删除用户,是否继续','提示',{
               confirmButtonText:'确定',
               cancelButtonText:'取消',
               type:'warning'
            }).catch(err=>err);
            if (confirmResult!='confirm'){
                return this.$message.info("操作已取消");
            }
            const {data:res} = await this.$http.delete("deleteUser?id="+id);
            // 删除失败
            if (res != "success"){
                return this.$message.error("删除失败!")
            }
            this.$message.success("删除成功!")
            // 重新查询用户
            this.getUserList();
        },
        // 显示对话框
        async showEditDialog(id){
            // 根据ID查询用户信息
            const {data:res} = await this.$http.get("getUpdateUser?id="+id);
            // 将返回的数据绑定到form中
            this.editForm = res;
            // 显示编辑菜单
            this.editDialogVisible = true;
        },
        // 关闭窗口
        editDialogClosed(){
             // 重置信息
             this.$refs.editUserFormRef.resetFields();
        },
        // 修改用户信息
        editUser(){
            this.$refs.editUserFormRef.validate(async valid =>{
                // 验证失败
                if(!valid) return;
                // 修改用户信息
                const {data:res} = await this.$http.post("updateUser",this.editForm);

                if (res != "success"){
                    return this.$message.console.error("修改失败!!");
                }
                this.$message.success("修改成功!");
                // 隐藏添加用户窗口
                this.editDialogVisible = false;
                // 重新查询用户
                this.getUserList();
            });
        },
    }
};
</script>

<style scoped>
.el-breadcrumb{
    margin-bottom: 15px;
    font-size: 15px;
}

.el-card{
    box-shadow: 0 1px 1px rgba(0, 8, 10, 0.15) !important;
}
</style>