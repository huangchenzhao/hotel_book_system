<template>
<div>
    <Header></Header>
    <el-tabs type="border-card">
        <el-tab-pane label="首页">
            <el-form ref="select-form" :model="selectForm" label-width="200px">
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="酒店名称" prop="hotelName">
                            <el-input v-model="selectForm.hotelName" style="width: 100%" prefix-icon="el-icon-school"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10" :offset="1">
                        <el-form-item label="入住地点" prop="cityValue">
                            <el-row>
                                <el-cascader
                                        size="large"
                                        :options="selectForm.options"
                                        v-model="selectForm.selectedOptions"
                                        @change="handleChange" style="width: 100%">
                                </el-cascader>
                            </el-row>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="时间" prop="mydate">
                            <div class="block">
                                <el-date-picker
                                        v-model="selectForm.mydate"
                                        type="daterange"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期" style="width: 100%">
                                </el-date-picker>
                            </div>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10" :offset="1">
                        <el-form-item label="房型" prop="room">
                            <el-select v-model="selectForm.room" placeholder="请选择" style="width: 100%" prefix-icon="el-icon-house">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col span="12" offset="7">
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit">确定</el-button>
                            <el-button>取消</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <el-row type="flex" justify="space-around">
                <el-col :span="6">
                    <el-card :body-style="{ padding: '0px' }" shadow="always">
                        <img src="../../../../assets/images/recommend1.jpeg" class="image" width="70px" height="400px">
                        <div style="padding: 14px;">
                            <span>如家连锁酒店</span>
                            <div class="bottom clearfix">
                                <el-button type="text" class="button">查看详情</el-button>
                            </div>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="6">
                    <el-card :body-style="{ padding: '0px' }" shadow="always">
                        <img src="../../../../assets/images/recommend2.jpeg" class="image" width="70px" height="400px">
                        <div style="padding: 14px;">
                            <span>希尔顿酒店</span>
                            <div class="bottom clearfix">
                                <el-button type="text" class="button">查看详情</el-button>
                            </div>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="6">
                    <el-card :body-style="{ padding: '0px' }" shadow="always">
                        <img src="../../../../assets/images/recommend3.jpeg" class="image" width="70px" height="400px">
                        <div style="padding: 14px;">
                            <span>学生旅社</span>
                            <div class="bottom clearfix">
                                <el-button type="text" class="button">查看详情</el-button>
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </el-tab-pane>
        <el-tab-pane label="我的账户">
            <el-card class="user-card" style="margin: auto">
                <el-descriptions class="des-user" :column="2" border :size="size" title="账户信息">
                    <template slot="extra">
                        <el-button type="primary" @click="centerDialogVisible = true">修改头像</el-button>
                        <el-dialog
                                title="上传头像"
                                :visible.sync="centerDialogVisible"
                                width="30%"
                                center>
                            <span>
                                <el-upload
                                        class="avatar-uploader"
                                        action="/api/file/upload"
                                        :show-file-list="false"
                                        :http-request="uploadImg"
                                        :on-success="handleAvatarSuccess"
                                        :before-upload="beforeAvatarUpload">
  <img v-if="imageUrl" :src="imageUrl" class="avatar">
  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
</el-upload>
                            </span>
                            <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
  </span>
                        </el-dialog>
                    </template>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-user"></i>
                            用户名
                        </template>
                        kooriookami
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-mobile-phone"></i>
                            密码
                        </template>
                        xiaoming
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-location-outline"></i>
                            邮箱
                        </template>
                        543892034@qq.com
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-tickets"></i>
                            备注
                        </template>
                        <el-tag size="small">用户</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-office-building"></i>
                            头像
                        </template>
                        <img src="../../../../assets/images/user2.jpeg" alt="">
                    </el-descriptions-item>
                </el-descriptions>
            </el-card>
        </el-tab-pane>
        <el-tab-pane label="我的订单">角色管理</el-tab-pane>
    </el-tabs>
</div>
</template>

<script>
import Header from '../../../../components/Header'
import { regionData, CodeToText } from 'element-china-area-data'
export default {
  name: 'userPage',
  components: {Header},
  data () {
    return {
      selectForm: {
        hotelName: '',
        cityValue: [],
        room: '',
        mydate: '',
        selectedOptions: [],
        options: regionData
      },
      options: [{
        value: '标准间',
        label: '标准间'
      }, {
        value: '双人房',
        label: '双人房'
      }, {
        value: '大床房',
        label: '大床房'
      }, {
        value: '亲子房',
        label: '亲子房'
      }, {
        value: '总统套房',
        label: '总统套房'
      }],
      centerDialogVisible: false,
      imageUrl: ''
    }
  },
  methods: {
    handleChange () {
      let loc = ''
      for (let i = 0; i < this.selectForm.selectedOptions.length; i++) {
        loc += CodeToText[this.selectForm.selectedOptions[i]]
      }
      console.info(loc)
      // console.info(this.selectForm.selectedOptions[this.selectForm.selectedOptions.length - 1])
    },
    onSubmit () {
      console.log(this.selectForm.hotelName)
      console.log(this.selectForm.selectedOptions[this.selectForm.selectedOptions.length - 1])
      console.log(this.selectForm.mydate)
      console.log(this.selectForm.room)
    },
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>

<style scoped>
    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }
    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }
    .image {
        width: 100%;
        display: block;
    }
    .button {
        padding: 0;
        float: right;
    }
    .user-card {
        width: 700px;
    }
    .avatar-uploader {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
    .avatar-uploader{
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>
