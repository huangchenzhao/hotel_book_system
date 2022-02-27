<template xmlns:text-align="http://www.w3.org/1999/xhtml">
<!--        <canvas class="cavs" width="100%" height="100%"></canvas>-->
    <div  style="width: 100%">
        <el-row class="card-form">
            <el-col><div class="grid-content bg-purple">
                <el-card class="login-card" shadow="always">
                    <div slot="header" class="clearfix" >
                        <span class="login-title">微旅——您身边的酒店预订系统</span>
                    </div>
                    <el-row>
                        <div class="input_userName">
                            <el-input placeholder="请输入账号" v-model="userName" prefix-icon="el-icon-mobile-phone"></el-input>
                        </div>
                    </el-row>
                    <el-row>
                        <div class="input_pwd">
                            <el-input placeholder="请输入密码" v-model="pwd" show-password prefix-icon="el-icon-lock"></el-input>
                        </div>
                    </el-row>
                    <el-row class="choice">
                        <el-radio v-model="radio" label="1">用户登录</el-radio>
                        <el-radio v-model="radio" label="2">管理员登陆</el-radio>
                    </el-row>
                    <el-row>
                        <el-button type="primary" class="login-btn" @click="login">确认登陆</el-button>
                        <el-button type="primary" plain class="reg-btn" @click="dialogFormVisible = true">注册一个</el-button>
                        <el-dialog title="注册" :visible.sync="dialogFormVisible" @closed="handleClose" append-to-body="true" center>
                            <el-form :model="form" label-width="180px">
                                <el-form-item label="用户名" :label-width="formLabelWidth" prop="regUserName">
                                    <el-input v-model="regUserName" autocomplete="off" placeholder="请输入用户名" prefix-icon="el-icon-mobile-phone"></el-input>
                                </el-form-item>
                                <el-form-item label="密码" :label-width="formLabelWidth" prop="regPwd">
                                    <el-input placeholder="请输入密码" show-password prefix-icon="el-icon-lock">
                                        <el-input v-model="regPwd" autocomplete="off"></el-input>
                                    </el-input>
                                </el-form-item>
                                <el-form-item label="邮箱" :label-width="formLabelWidth" prop="regMail">
                                    <el-input placeholder="请输入qq邮箱" prefix-icon="el-icon-message">
                                        <el-input v-model="regMail" autocomplete="off"></el-input>
                                    </el-input>
                                </el-form-item>
                            </el-form>
                            <div slot="footer" class="dialog-footer">
                                <el-button @click="dialogFormVisible = false">取 消</el-button>
                                <el-button type="primary" @click="handleSave" >确 定</el-button>
                            </div>
                        </el-dialog>
                    </el-row>
                </el-card>
            </div></el-col>
        </el-row>
    </div>
</template>

<script>
import {user} from '@/api/api'

export default {
  name: 'login',
  data () {
    return {
      title: '登陆',
      userName: '',
      pwd: '',
      radio: 1,
      regUserName: '',
      regPwd: '',
      regMail: '',
      dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: '120px'
    }
  },
  methods: {
    login: function () {
      let myuser = {username: this.userName, password: this.pwd}
      user(myuser).then(res => {
        this.$router.push({path: '/userlist'})
      })
    },
    handleClose () {
      this.$refs.ruleForm.resetFields()
      this.form = {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    },
    handleSave () {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          console.log('输入正确')
          this.dialogFormVisible = false
        } else {
          console.log('输入错误')
        }
      })
    }
  },
}
</script>

<style scoped>
    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

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
        margin: auto;
        vertical-align: center;
    }
    .clearfix {
text-align: center;
    }
    .login-title{
        color: #364041;
        height: 60px;
        font-size:20px;
        text-align: center;
        margin-top: -20px;
    }
    .input_userName {
        margin-bottom: 20px;
        width: 300px;
        margin-left: 125px;
    }
    .input_pwd {
        margin-bottom: 20px;
        width: 300px;
        margin-left: 125px;
    }
    .login-btn{
        margin-left: 150px;
        margin-bottom: 20px;
    }
    .reg-btn{
        margin-left: 51px;
        margin-bottom: 20px;
    }
    .card-form{
        margin-top: 200px;
    }
    .choice{
        text-align: center;
        margin-bottom: 20px;
    }
</style>
