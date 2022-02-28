<template xmlns:text-align="http://www.w3.org/1999/xhtml">
<!--        <canvas class="cavs" width="100%" height="100%"></canvas>-->
    <div class="home">
        <el-container>
            <el-header></el-header>
            <el-main>
                <el-card class="login-card" shadow="always" style="margin: auto">
                    <div slot="header" class="clearfix">
                        <span>微旅——您身边的酒店预订系统</span>
                    </div>
                    <el-form class="loginForm" :model="loginForm">
                        <el-row>
                            <el-col :span="18" :offset="2"><div class="grid-content bg-purple">
                                <el-form-item label="用户名" :label-width="formLabelWidth" prop="userName" :rules="[
      { required: true, message: '请输入用户名', trigger: 'blur'}
    ]">
                                    <el-input v-model="loginForm.userName" autocomplete="off" placeholder="请输入用户名" prefix-icon="el-icon-mobile-phone"></el-input>
                                </el-form-item>
                            </div></el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="18" :offset="2"><div class="grid-content bg-purple">
                                <el-form-item label="密码" :label-width="formLabelWidth" prop="pwd" :rules="[
      { required: true, message: '请输入密码', trigger: 'blur' }
    ]">
                                    <el-input v-model="loginForm.pwd" autocomplete="off" show-password placeholder="请输入密码" prefix-icon="el-icon-lock"></el-input>
                                </el-form-item>
                            </div></el-col>
                        </el-row>
                        <el-row>
                            <el-col span="12" offset="8"><div class="grid-content bg-purple">
                                <el-form-item>
                                    <el-radio v-model="loginForm.radio" label="1">用户登录</el-radio>
                                    <el-radio v-model="loginForm.radio" label="0">管理员登陆</el-radio>
                                </el-form-item>
                            </div></el-col>
                        </el-row>
                        <el-row>
                            <el-col span="12" offset="8"><div class="grid-content bg-purple">
                                <el-form-item>
                                    <el-button type="primary" class="login-btn" @click="login">确认登陆</el-button>
                                    <el-button type="primary" plain class="reg-btn" @click="dialogFormVisible = true">注册一个</el-button>
                                </el-form-item>
                            </div></el-col>
                        </el-row>
                    </el-form>
                    <el-dialog title="注册" :visible.sync="dialogFormVisible" @closed="handleClose" append-to-body="true" center>
                        <el-form :model="regForm" label-width="180px">
                            <el-form-item label="用户名" :label-width="formLabelWidth" prop="regUserName" :rules="[
      { required: true, message: '请输入用户名', trigger: 'blur' }
    ]">
                                <el-input v-model="regForm.regUserName" autocomplete="off" placeholder="请输入用户名" prefix-icon="el-icon-mobile-phone"></el-input>
                            </el-form-item>
                            <el-form-item label="密码" :label-width="formLabelWidth" prop="regPwd" :rules="[
      { required: true, message: '请输入密码', trigger: 'blur' }
    ]">
                                <el-input v-model="regForm.regPwd" autocomplete="off" show-password placeholder="请输入密码" prefix-icon="el-icon-lock"></el-input>
                            </el-form-item>
                            <el-form-item label="邮箱" :label-width="formLabelWidth" prop="regMail" :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },
      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
    ]">
                                <el-input v-model="regForm.regMail" autocomplete="off" placeholder="请输入邮箱" prefix-icon="el-icon-message"></el-input>
                            </el-form-item>
                            <el-form-item label="验证码" :label-width="formLabelWidth" prop="regUserName" :rules="[
      { required: true, message: '请输入验证码', trigger: 'blur' }
    ]">
                                <div>
                                    <el-input type="text" max="6" v-model="regForm.regCheck" autocomplete="off" placeholder="请输入验证码" prefix-icon="el-icon-document-checked">
                                        <template slot="append">
                                            <el-button type="primary" plain @click="getCode">发送验证码</el-button>
                                        </template>
                                    </el-input>
                                </div>
                            </el-form-item>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="dialogFormVisible = false">取 消</el-button>
                            <el-button type="primary" @click="handleSave" >确 定</el-button>
                        </div>
                    </el-dialog>
                </el-card>
            </el-main>
        </el-container>
    </div>
</template>

<script>
import {user, regUser} from '@/api/api'

export default {
  name: 'login',
  data () {
    return {
      loginForm: {
        userName: '',
        pwd: '',
        radio: '1',
        logReturn: {}
      },
      regForm: {
        regUserName: '',
        regPwd: '',
        regMail: '',
        regCheck: '',
        regReturn: {}
      },
      title: '登陆',
      dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: '120px'
    }
  },
  methods: {
    login: function () {
      let myUser = {username: this.loginForm.userName, password: this.loginForm.pwd, usertype: this.loginForm.radio}
      user(myUser).then(res => {
        this.loginForm.logReturn = res
        if (this.loginForm.logReturn.data === null) {
          this.$message.error({message: '登陆失败，用户名或密码输入错误哦~', center: true})
        } else {
          this.$router.push({path: '/userPage'})
        }
      })
    },
    handleSave: function () {
      let newUser = {username: this.regForm.regUserName, password: this.regForm.regPwd, usertype: '1'}
      regUser(newUser).then(res => {
        this.regForm.regReturn = res
        if (this.regForm.regReturn.data === null) {
          this.$message.error({message: '注册失败，用户名已存在哦~', center: true})
        } else {
          this.$message({
            message: '恭喜您，注册成功~',
            type: 'success',
            center: true
          })
          this.dialogFormVisible = false
        }
      })
    }
  }
}
</script>

<style scoped>
    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }

    .login-card {
        width: 580px;
        margin-top: 25%;
        height: 50%;
        box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
    }
    .clearfix {
text-align: center;
        font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
        font-size: 20px;
        font-weight: bold;
    }
    .home {
        position: fixed;
        height: 100%;
        width: 100%;
        background-color: #658ea9 !important;
    }
</style>
