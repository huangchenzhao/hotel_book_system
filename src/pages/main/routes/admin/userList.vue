<template>
    <div>
        <el-container class="el-container">
            <el-header class="el-header" height="100px">
                <h2 style="margin-top: 2.5%">微旅——您身边的酒店预订系统</h2>
            </el-header>
            <el-container>
                <el-aside width="200px" class="el-aside">
                    <el-menu class="el-menu" @open="handleOpen"
                             @close="handleClose" :default-openeds="openeds" router :default-active="$route.path">
                        <el-submenu index="1">
                            <template slot="title">
                                <i class="el-icon-office-building"></i>
                                <span>酒店信息</span>
                            </template>
                            <el-menu-item index="/hotel/list">酒店列表</el-menu-item>
                            <el-menu-item index="/hotel/add">增加酒店</el-menu-item>
                            <el-menu-item index="/hotel/manage">酒店管理</el-menu-item>
                        </el-submenu>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-sell"></i>
                                <span>订单信息</span>
                            </template>
                            <el-menu-item index="2-1">订单列表</el-menu-item>
                        </el-submenu>
                        <el-submenu index="3">
                            <template slot="title">
                                <i class="el-icon-user"></i>
                                <span>用户信息</span>
                            </template>
                            <el-menu-item index="/user/list">用户列表</el-menu-item>
                            <el-menu-item index="3-2">用户管理</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main class="el-main">
                    <el-table
                            :data="user.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                            stripe
                            style="width: 100%">
                    <el-table-column
                            prop="name"
                            label="用户名"
                            width="150" align="center" header-align="center" >
                    </el-table-column>
                    <el-table-column
                            prop="password"
                            label="密码"
                            width="150" align="center" header-align="center" >
                    </el-table-column>
                    <el-table-column
                            prop="mail"
                            label="邮箱"
                            width="150" align="center" header-align="center" >
                    </el-table-column>
                    <el-table-column
                            prop="photo"
                            label="头像"
                            width="250" align="center" header-align="center" >
                    </el-table-column>
                    <el-table-column
                            prop="order"
                            label="预约次数"
                            width="200" align="center" sortable header-align="center" >
                    </el-table-column>
                        <el-table-column
                                prop="money"
                                label="消费金额"
                                width="200" align="center" sortable header-align="center" >
                        </el-table-column>
                    <el-table-column
                            label="操作"
                            align="center" header-align="center" >
                        <template slot-scope="scope">
                            <el-popconfirm
                                    title="确定删除这个用户吗?" @onConfirm="delUser">
                                <el-button slot="reference" type="primary" icon="el-icon-delete"></el-button>
                            </el-popconfirm>
                            <el-button
                                    type="primary" icon="el-icon-edit" @click="dialogFormVisible = true">
                            </el-button>
                            <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
                                <el-form :model="userForm">
                                    <el-form-item label="用户名">
                                        <el-input v-model="userForm.name" autocomplete="off"></el-input>
                                    </el-form-item>
                                    <el-form-item label="密码">
                                        <el-input v-model="userForm.pwd" autocomplete="off"></el-input>
                                    </el-form-item>
                                </el-form>
                                <div slot="footer" class="dialog-footer">
                                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                                    <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
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
export default {
  name: 'userList',
  components: {Header},
  data () {
    return {
      openeds: ['1'],
      user: [],
      pageSize: 10,
      currentPage: 1,
      dialogFormVisible: false,
      userForm: {
        name
      }
    }
  },
  methods: {
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
    delUser (user) {
    }
  }
}
</script>

<style scoped>
    .el-header {
        background: url('../../../../assets/images/header.png');
        text-align: center;
        color: #dcdfe6;
        font-family:Arial,Helvetica,sans-serif;
        font-size: large;
        font-weight: bolder;
    }

    .el-aside {
        background-color: #336699;
        text-align: center;
        line-height: 200px;
        height:135vh;
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
</style>
