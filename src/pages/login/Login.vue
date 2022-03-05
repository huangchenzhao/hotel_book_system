<template xmlns:text-align="http://www.w3.org/1999/xhtml">
<!--        <canvas class="cavs" width="100%" height="100%"></canvas>-->
    <div class="home">
        <Header></Header>
        <el-container>
            <el-header></el-header>
            <el-main>
                <el-card class="login-card" shadow="always" style="margin: auto">
                    <div slot="header" class="clearfix">
                        <span>微旅——您身边的酒店预订系统</span>
                    </div>
                    <el-form class="loginForm" :model="loginForm">
                        <el-row>
                            <el-col :span="18" :offset="2">
                                <el-form-item label="用户名" :label-width="formLabelWidth" prop="userName" :rules="[
      { required: true, message: '请输入用户名', trigger: 'blur'}
    ]">
                                    <el-input v-model="loginForm.userName" autocomplete="off" placeholder="请输入用户名" prefix-icon="el-icon-mobile-phone"></el-input>
                                </el-form-item>
                            </el-col>
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
                        <el-row type="flex" justify="center">
                            <el-col :span="4" >
                                <el-link icon="el-icon-edit" :disabled="this.loginForm.radio==='0'" @click="centerDialogVisible=true">忘记密码</el-link>
                            </el-col>
                        </el-row>
                        <el-dialog
                                title="忘记密码"
                                :visible.sync="centerDialogVisible"
                                width="40%"
                                center :modal-append-to-body='false'>
                            <span>
                                <el-form :model="getPwdForm" label-width="80px">
                                    <el-row type="flex" justify="center">
  <el-col :span="20" :offset="2">
      <el-form-item label="邮箱" prop="mail">
    <el-input type="text" v-model="getPwdForm.mail" style="width: 93%" autocomplete="off" placeholder="请输入邮箱" prefix-icon="el-icon-document-checked"></el-input>
  </el-form-item>
  </el-col>
</el-row>
                                    <el-row type="flex" justify="center">
  <el-col :span="20" :offset="2">
      <div>
          <el-form-item label="验证码" prop="check">
                                    <el-input type="text" max="6" v-model="getPwdForm.check" style="width: 55%" autocomplete="off" placeholder="请输入验证码" prefix-icon="el-icon-document-checked">
                                    </el-input>
                                    <el-button icon="el-icon-mobile-phone" @click="pwdSend" type="success" :disabled="disabled=!show1" >
                                        <span v-show="show1">获取验证码</span>
                                        <span v-show="!show1" class="count">{{count1}}s后再试</span>
                                    </el-button>
              </el-form-item>
                                </div>
  </el-col>
</el-row>
                                    <el-row type="flex" justify="center">
  <el-col :span="20" :offset="2">
      <el-form-item label="新密码" prop="pwd">
    <el-input type="text" v-model="getPwdForm.pwd" style="width: 93%" autocomplete="off" placeholder="请输入密码" prefix-icon="el-icon-document-checked"></el-input>
  </el-form-item>
  </el-col>
</el-row>
                                </el-form>
                            </span>
                            <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click=forgetPwd>确 定</el-button>
  </span>
                        </el-dialog>
                        <el-row type="flex" justify="center">
                            <el-col span="10" :offset="2">
                                <el-form-item>
                                    <el-radio v-model="loginForm.radio" label="1">用户登录</el-radio>
                                    <el-radio v-model="loginForm.radio" label="0">管理员登陆</el-radio>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row type="flex" justify="center">
                            <el-col span="12" :offset="4">
                                <el-form-item>
                                    <el-button type="primary" class="login-btn" @click="login">确认登陆</el-button>
                                    <el-button type="primary" plain class="reg-btn" @click="dialogFormVisible = true">注册一个</el-button>
                                </el-form-item>
                            </el-col>
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
                            <el-form-item label="验证码" :label-width="formLabelWidth" prop="regCheck" :rules="[
      { required: true, message: '请输入验证码', trigger: 'blur' }
    ]">
                                <div>
                                    <el-input type="text" max="6" v-model="regForm.regCheck" style="width: 75%" autocomplete="off" placeholder="请输入验证码" prefix-icon="el-icon-document-checked">
                                    </el-input>
                                    <el-button icon="el-icon-mobile-phone" @click="send" type="success" :disabled="disabled=!show" >
                                        <span v-show="show">获取验证码</span>
                                        <span v-show="!show" class="count">{{count}}s后再试</span>
                                    </el-button>
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
import {user, regUser, sendEmail} from '@/api/api'
import Header from '../../components/Header'
const TIME_COUNT = 60
export default {
  name: 'login',
  components: {Header},
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
        regReturn: ''
      },
      getPwdForm: {
        mail: '',
        check: '',
        pwd: ''
      },
      title: '登陆',
      dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      show: true,
      count: '',
      timer: null,
      centerDialogVisible: false,
      show1: true,
      count1: '',
      timer1: null
    }
  },
  methods: {
    forgetPwd () {
      this.centerDialogVisible = false
    },
    login: function () {
      let myUser = {username: this.loginForm.userName, password: this.loginForm.pwd, usertype: this.loginForm.radio}
      user(myUser).then(res => {
        this.loginForm.logReturn = res
        if (this.loginForm.logReturn.data === null) {
          this.$message.error({message: '登陆失败，用户名或密码输入错误哦~', center: true})
        } else {
          this.$router.push({name: 'userPage'})
        }
      })
    },
    pwdSend () {
      if (this.getPwdForm.mail === '') {
        this.$message.error({message: '请输入正确的邮箱地址~', center: true})
      } else {
        if (!this.timer) {
          this.count1 = TIME_COUNT
          this.show1 = false
          this.timer1 = setInterval(() => {
            if (this.count1 > 0 && this.count1 <= TIME_COUNT) {
              this.count1--
            } else {
              this.show1 = true
              clearInterval(this.timer1)
              this.timer1 = null
            }
          }, 1000)
        }
        let myEmail = {email: this.getPwdForm.mail}
        sendEmail(myEmail).then(res => {
          console.info(res.data)
        })
      }
    },
    send () {
      if (this.regForm.regMail === '') {
        this.$message.error({message: '请输入正确的邮箱地址~', center: true})
      } else {
        if (!this.timer) {
          this.count = TIME_COUNT
          this.show = false
          this.timer = setInterval(() => {
            if (this.count > 0 && this.count <= TIME_COUNT) {
              this.count--
            } else {
              this.show = true
              clearInterval(this.timer)
              this.timer = null
            }
          }, 1000)
        }
        let myEmail = {email: this.regForm.regMail}
        sendEmail(myEmail).then(res => {
          console.info(res.data)
        })
      }
    },
    handleSave: function () {
      let newUser = {mail: this.regForm.regMail, code: this.regForm.regCheck, username: this.regForm.regUserName, password: this.regForm.regPwd, usertype: '1'}
      regUser(newUser).then(res => {
        this.regForm.regReturn = res.data
        /* if (this.regForm.regReturn.data === null) {
          this.$message.error({message: '注册失败，用户名已存在哦~', center: true})
        } else {
          let regMsg = {email: this.regForm.regMail, code: this.checkcode}
          verifyCode(regMsg).then(res => {
            if (res.data === null) {
              this.$message.error({message: '注册失败，验证码不正确哦~', center: true})
            } else {
              this.$message({
                message: '注册成功，请登陆~',
                type: 'success',
                center: true
              })
              this.dialogFormVisible = false
            }
          })
        } */
        if (this.regForm.regReturn === '用户名和密码不可为空') {
          this.$message.error({message: '注册失败，用户名和密码不可为空哦~', center: true})
        } else {
          if (this.regForm.regReturn === '用户已存在！') {
            this.$message.error({message: '注册失败，用户名已存在哦~', center: true})
          } else {
            if (this.regForm.regReturn === '邮箱已注册！') {
              this.$message.error({message: '注册失败，该邮箱已注册过哦~', center: true})
            } else {
              if (this.regForm.regReturn === '邮箱或验证码错误！') {
                this.$message.error({message: '注册失败，邮箱或验证码错误哦~', center: true})
              } else {
                this.$message({
                  message: '注册成功，请登陆~',
                  type: 'success',
                  center: true
                })
                this.dialogFormVisible = false
              }
            }
          }
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
        /*box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);*/
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
        /*background-color: #003399 !important;*/
    }
</style>
