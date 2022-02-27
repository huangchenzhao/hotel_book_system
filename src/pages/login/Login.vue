<template xmlns:text-align="http://www.w3.org/1999/xhtml">
<!--        <canvas class="cavs" width="100%" height="100%"></canvas>-->
    <div  style="width: 100%">
        <el-row :gutter="20">
            <el-col :span="12" :offset="6"><div class="grid-content bg-purple">
                <el-card class="login-card">
                    <div slot="header" class="clearfix" >
                        <span class="login-title">微旅——您身边的酒店预订系统</span>
                    </div>
                    <el-row>
                        <div class="input_userName">
                            <el-input placeholder="请输入账号" v-model="userName"></el-input>
                        </div>
                    </el-row>
                    <el-row>
                        <div class="input_pwd">
                            <el-input placeholder="请输入密码" v-model="pwd" show-password></el-input>
                        </div>
                    </el-row>
                    <el-row type="flex" class="row-bg" justify="center">
                        <el-col :span="6"><div class="grid-content bg-purple">
                            <el-button type="primary" plain class="login-btn">确认登陆</el-button>
                        </div></el-col>
                        <el-col :span="6"><div class="grid-content bg-purple-light"></div></el-col>
                        <el-col :span="6"><div class="grid-content bg-purple">
                            <el-button type="primary" plain class="login-btn">注册一个</el-button>
                        </div></el-col>
                    </el-row>
                </el-card>
            </div></el-col>
        </el-row>
    </div>
</template>

<script>

export default {
  name: 'login',
  data () {
    return {
      title: '登陆',
      userName: '',
      pwd: ''
    }
  },
  methods: {
    login: function () {

    }
  }
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
</style>
