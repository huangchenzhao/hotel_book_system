<template>
  <div>
    <el-container class="el-container">
      <el-header class="el-header" height="100px">
        <h2 style="margin-top: 2.5%">微旅——您身边的酒店预订系统</h2>
      </el-header>
      <el-container>
        <el-aside width="200px" class="el-aside">
          <el-menu class="el-menu" @open="handleOpen"
                   @close="handleClose" router :default-active="$route.path">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-office-building"></i>
                <span>酒店信息</span>
              </template>
              <el-menu-item index="/hotel/list">酒店列表</el-menu-item>
              <el-menu-item index="/hotel/add">增加酒店</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-sell"></i>
                <span>订单信息</span>
              </template>
              <el-menu-item index="/order/list">订单列表</el-menu-item>
              <el-menu-item index="/order/charts">订单分析</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-user"></i>
                <span>用户信息</span>
              </template>
              <el-menu-item index="/user/list">用户列表</el-menu-item>
            </el-submenu>
          </el-menu>
          <div class="loginOut">
            <div>
              <el-popconfirm
                      title="确定退出登录吗" @confirm="logOut">
                <el-button slot="reference" icon="el-icon-bell">登出</el-button>
              </el-popconfirm>
            </div>
          </div>
        </el-aside>
        <el-main class="el-main">
          <el-table
              :data="user.slice((currentPage-1)*pageSize,currentPage*pageSize)"
              stripe
              style="width: 100%">
            <el-table-column
                prop="username"
                label="用户名"
                width="100" align="center" header-align="center">
            </el-table-column>
            <el-table-column
                prop="password"
                label="密码"
                width="150" align="center" header-align="center">
            </el-table-column>
            <el-table-column
                prop="mail"
                label="邮箱"
                width="150" align="center" header-align="center">
            </el-table-column>
            <el-table-column
                prop="photoUrl"
                label="头像"
                width="200" align="center" header-align="center">
              <template slot-scope="scope">
                <img :src="scope.row.photoUrl" alt="" width="90" height="90">
              </template>
            </el-table-column>
            <el-table-column
                prop="countt"
                label="预约次数"
                :formatter="counttFormat"
                width="150" align="center" sortable header-align="center">
            </el-table-column>
            <el-table-column
                prop="summ"
                label="消费金额"
                :formatter="summFormat"
                width="200" align="center" sortable header-align="center">
            </el-table-column>
            <el-table-column
                align="center" header-align="center">
              <template slot="header" slot-scope="scope">
                <el-button round @click="addUser">新增用户</el-button>
                <el-dialog title="新增用户" :visible.sync="addUserFormVisible" width="40%">
                  <el-form :model="addUserForm" :rules="rules">
                    <el-form-item label="用户名" prop="name" :label-width="FormLabelWidth" :rules="[
      { required: true, message: '请输入用户名', trigger: 'blur' }
    ]">
                      <el-input v-model="addUserForm.name" autocomplete="off" placeholder="请输入用户名"
                                prefix-icon="el-icon-mobile-phone"></el-input>
                    </el-form-item>
                    <el-form-item label="用户密码" prop="pwd" :label-width="FormLabelWidth" :rules="[
      { required: true, message: '请输入密码', trigger: 'blur' }
    ]">
                      <el-input v-model="addUserForm.pwd" autocomplete="off" show-password placeholder="请输入用户密码"
                                prefix-icon="el-icon-lock"></el-input>
                    </el-form-item>
                    <el-form-item label="用户邮箱" prop="mail" :label-width="FormLabelWidth" :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },
      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
    ]">
                      <el-input v-model="addUserForm.mail" autocomplete="off" show-password placeholder="请输入用户邮箱"
                                prefix-icon="el-icon-lock"></el-input>
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="addUserFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="commitNewUser">确 定</el-button>
                  </div>
                </el-dialog>
              </template>
              <template slot-scope="scope">
                <el-popconfirm
                    title="确定删除这个用户吗?" @confirm="delUser(scope.row)">
                  <el-button slot="reference" type="primary" icon="el-icon-delete"></el-button>
                </el-popconfirm>
                <el-button
                    type="primary" icon="el-icon-edit" @click="getUserDefault(scope.row)">
                </el-button>
                <el-dialog title="管理用户信息" :visible.sync="dialogFormVisible" width="40%">
                  <el-form :model="userForm">
                    <el-form-item label="用户名" :label-width="FormLabelWidth">
                      <el-input disabled="true" v-model="userForm.name" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" :label-width="FormLabelWidth">
                      <el-input v-model="userForm.pwd" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="editUser">确 定</el-button>
                  </div>
                </el-dialog>
              </template>
            </el-table-column>
          </el-table>
          <el-col :span="24" class="toolbar" style="text-align:center">
            <el-pagination
                @current-change="handleCurrentChange" @size-change="handleSizeChange" :current-page="currentPage"
                layout="total, prev, pager, next"
                :page-size="pageSize"
                :total="this.user.length"
            ></el-pagination>
          </el-col>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Header from '../../../../components/Header'
import {adminGetUserList, adminDelUser, adminChangeUserPwd, addUser} from '@/api/api'

export default {
  name: 'userList',
  components: {Header},
  data () {
    return {
      openeds: ['1'],
      user: [],
      pageSize: 4,
      currentPage: 1,
      dialogFormVisible: false,
      userForm: {
        name: '',
        pwd: '',
        id: ''
      },
      addUserFormVisible: false,
      FormLabelWidth: '120px',
      addUserForm: {
        name: '',
        pwd: '',
        mail: ''
      },
      rules: {
        name: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        pwd: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
        mail: [
          {required: true, message: '请输入邮箱地址', trigger: 'blur'},
          {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}
        ]
      }
    }
  },
  created () {
    adminGetUserList().then(res => {
      this.user = res.data
      console.info(res.data)
    })
  },
  methods: {
    logOut () {
      this.$router.push({name: 'login'})
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    handleCurrentChange (currentPage) {
      this.currentPage = currentPage
    },
    handleSizeChange (val) {
      this.pageSize = val
    },
    delUser (myRow) {
      let deletedUser = {u_id: myRow.uId}
      adminDelUser(deletedUser).then(res => {
        this.$message({
          message: '删除成功~',
          type: 'success',
          center: true
        })
      })
    },
    editUser () {
      if (this.userForm.pwd === '') {
        this.$message.error({message: '密码不能为空噢~', center: true})
      } else {
        let updatedUser = {uid: this.userForm.id, pass: this.userForm.pwd}
        console.info(updatedUser)
        adminChangeUserPwd(updatedUser).then(res => {
          this.$message({
            message: '修改成功~',
            type: 'success',
            center: true
          })
          this.dialogFormVisible = false
        })
      }
    },
    getUserDefault (user) {
      console.info(user)
      this.userForm.name = user.username
      this.userForm.pwd = user.password
      this.userForm.id = user.uId
      this.dialogFormVisible = true
    },
    addUser () {
      this.addUserFormVisible = true
    },
    counttFormat (row) {
      return row.countt == null ? '0' : row.countt
    },
    summFormat (row) {
      return row.summ == null ? '0' : row.summ
    },
    commitNewUser () {
      if (this.addUserForm.name === '' || this.addUserForm.pwd === '' || this.addUserForm.mail === '') {
        this.$message.error({message: '用户名/密码/邮箱不能为空~', center: true})
      } else {
        let newUser = {username: this.addUserForm.name, password: this.addUserForm.pwd, mail: this.addUserForm.mail}
        addUser(newUser).then(res => {
          if (res.data === '该用户名已存在') {
            this.$message.error({message: '用户名已存在~', center: true})
          } else {
            if (res.data === '邮箱已存在') {
              this.$message.error({message: '该邮箱已注册过~', center: true})
            } else {
              if (res.data === '添加失败') {
                this.$message.error({message: '用户已存在~', center: true})
              } else {
                this.$message({
                  message: '添加成功~',
                  type: 'success',
                  center: true
                })
                this.addUserFormVisible = false
              }
            }
          }
        })
      }
    }
  }
}
</script>

<style scoped>
.el-header {
  background: url('../../../../assets/images/header.png');
  text-align: center;
  color: #dcdfe6;
  font-family: Arial, Helvetica, sans-serif;
  font-size: large;
  font-weight: bolder;
}

.el-aside {
  background-color: #336699;
  text-align: center;
  /*line-height: 200px;*/
  height: 135vh;
}

.el-submenu {
  background-color: #FFFFCC;
}

.el-menu-item {
  background-color: #99CCCC !important;
}

.el-menu {
  background-color: #FFFFCC;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  /*line-height: 160px;*/
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
  .loginOut {
    padding-top: 5px;
  }
</style>
