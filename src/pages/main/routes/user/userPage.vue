<template>
<div>
    <el-container class="el-container">
        <el-header class="el-header" height="100px">
            <h2 style="margin-top: 2.5%">微旅——您身边的酒店预订系统</h2>
        </el-header>
        <el-main>
            <el-tabs type="border-card">
        <el-tab-pane label="首页">
            <el-carousel :interval="4000" type="card" height="390px">
                <el-carousel-item>
                    <div class="item">
                        <div class="item__content">
                            {{hotelName1}}
                        </div>
                        <img class="item__image" src="../../../../assets/images/recommend1.jpeg" alt="" />
                    </div>
                </el-carousel-item>
                <el-carousel-item>
                    <div class="item">
                        <div class="item__content">
                            {{hotelName2}}
                        </div>
                        <img class="item__image" src="../../../../assets/images/recommend2.jpeg" alt="" />
                    </div>
                </el-carousel-item>
                <el-carousel-item>
                    <div class="item">
                        <div class="item__content">
                            {{hotelName3}}
                        </div>
                        <img class="item__image" src="../../../../assets/images/recommend3.jpeg" alt="" />
                    </div>
                </el-carousel-item>
            </el-carousel>
            <el-form ref="selectForm" :model="selectForm" label-width="200px">
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
                                        end-placeholder="结束日期" style="width: 100%" :clearable="false" :picker-options="pickerOptions"
                                        value-format=" yyyy-MM-dd HH:mm:ss"
                                        format="yyyy-MM-dd HH:mm:ss">
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
                <el-row >
                    <el-col :span="2" :offset="8">
                        <el-form-item class="el-form-item">
                            <el-button type="primary" @click="onSubmit">确定</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
<!--            <el-row type="flex" justify="space-around">-->
<!--                <el-col :span="6">-->
<!--                    <el-card :body-style="{ padding: '0px' }" shadow="always">-->
<!--                        <img src="../../../../assets/images/recommend1.jpeg" class="image" width="70px" height="400px">-->
<!--                        <div style="padding: 14px;">-->
<!--                            <span>{{hotelName1}}</span>-->
<!--                            <div class="bottom clearfix">-->
<!--                                <font size="2">{{hotelAddress1}}</font>-->
<!--                                &lt;!&ndash;                                <el-button type="text" class="button" @click="getHotel1">查看详情</el-button>&ndash;&gt;-->
<!--                            </div>-->
<!--                        </div>-->
<!--                    </el-card>-->
<!--                </el-col>-->
<!--                <el-col :span="6">-->
<!--                    <el-card :body-style="{ padding: '0px' }" shadow="always">-->
<!--                        <img src="../../../../assets/images/recommend2.jpeg" class="image" width="70px" height="400px">-->
<!--                        <div style="padding: 14px;">-->
<!--                            <span>{{hotelName2}}</span>-->
<!--                            <div class="bottom clearfix">-->
<!--                                <font size="2">{{hotelAddress2}}</font>-->
<!--                                &lt;!&ndash;                                <el-button type="text" class="button" @click="dialogTableVisible2 = true">查看详情</el-button>&ndash;&gt;-->
<!--                            </div>-->
<!--                        </div>-->
<!--                    </el-card>-->
<!--                </el-col>-->
<!--                <el-col :span="6">-->
<!--                    <el-card :body-style="{ padding: '0px' }" shadow="always">-->
<!--                        <img src="../../../../assets/images/recommend3.jpeg" class="image" width="70px" height="400px">-->
<!--                        <div style="padding: 14px;">-->
<!--                            <span>{{hotelName3}}</span>-->
<!--                            <div class="bottom clearfix">-->
<!--                                <font size="2">{{hotelAddress3}}</font>-->
<!--                                &lt;!&ndash;                                <el-button type="text" class="button" @click="dialogTableVisible3 = true">查看详情</el-button>&ndash;&gt;-->
<!--                            </div>-->
<!--                        </div>-->
<!--                    </el-card>-->
<!--                </el-col>-->
<!--            </el-row>-->
        </el-tab-pane>
        <el-tab-pane label="我的账户" :key="mykey">
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
    <el-button type="primary" @click="changePhoto">确 定</el-button>
  </span>
                        </el-dialog>
                        <el-button @click="centerDialogVisible1 = true">修改密码</el-button>
                        <el-dialog
                                title="修改密码"
                                :visible.sync="centerDialogVisible1"
                                width="40%"
                                center>
                            <span>
                                <el-form :model="pwdForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="pwdForm" :label-position="right">
  <el-form-item label="原密码" prop="oldPwd">
    <el-input type="password" v-model="pwdForm.oldPwd" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="新密码" prop="newPwd">
    <el-input type="password" v-model="pwdForm.newPwd" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="确认密码" prop="checkPwd">
    <el-input type="password" v-model="pwdForm.checkPwd" autocomplete="off"></el-input>
  </el-form-item>
                                </el-form>
                            </span>
                            <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible1 = false">取 消</el-button>
    <el-button type="primary" @click="submitPwd">确 定</el-button>
  </span>
                        </el-dialog>
                    </template>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-user"></i>
                            用户名
                        </template>
                      {{this.userInfo.username}}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-mobile-phone"></i>
                            密码
                        </template>
                      ********
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-location-outline"></i>
                            邮箱
                        </template>
                      {{this.userInfo.mail}}
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
                        <img :src="this.userInfo.photoUrl" alt="" id="imgid" class="userPhoto">
                    </el-descriptions-item>
                </el-descriptions>
            </el-card>
        </el-tab-pane>
        <el-tab-pane label="我的订单">
            <orderList></orderList>
        </el-tab-pane>
    </el-tabs>
            <div class="loginOut">
                <div v-if="userInfo.username">
                    <span class="m-r-10">{{"欢迎您 "+userInfo.username}}</span>
                    <el-popconfirm
                            title="确定退出登录吗" @confirm="logOut">
                        <el-button slot="reference" size="mini">登出</el-button>
                    </el-popconfirm>
                </div>
                <div v-else>
                    <span class="m-r-10">未检测到登录状态，请先登录</span>
                    <el-popconfirm
                            title="确定退出登录吗" @confirm="logOut">
                        <el-button slot="reference" size="mini">登出</el-button>
                    </el-popconfirm>
                </div>
            </div>
        </el-main>
    </el-container>
</div>
</template>

<script>
import {searchHotel, getUserInfo, getTempPhoto, changePassword, hotelRecommend} from '@/api/api'
import Header from '../../../../components/Header'
import { regionData, CodeToText } from 'element-china-area-data'
import orderList from './orderList'
export default {
  name: 'userPage',
  components: {Header, orderList},
  data: function () {
    var checkOldPwd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入原密码'))
      } else {
        if (this.pwdForm.oldPwd !== this.userInfo.password) {
          callback(new Error('请输入正确的原密码'))
        }
        callback()
      }
    }
    var checkNewPwd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.pwdForm.newPwd) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      dataimg: [{
        src: require('../../../../assets/images/recommend1.jpeg'),
        txt: this.hotelName1,
        txt2: '一站式服务'
      },
      {
        src: require('../../../../assets/images/recommend2.jpeg')
      },
      {
        src: require('../../../../assets/images/recommend3.jpeg')
      }
      ],
      center: {lng: 0, lat: 0},
      zoom: 3,
      latitude1: 0,
      longitude1: 0,
      latitude2: 0,
      longitude2: 0,
      latitude3: 0,
      longitude3: 0,
      addressName: '',
      hotelDetail1: [],
      rules: {
        oldPwd: [
          { required: true, validator: checkOldPwd, trigger: 'blur' }
        ],
        newPwd: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        checkPwd: [
          { required: true, validator: checkNewPwd, trigger: 'blur' }
        ]
      },
      pickerOptions: {
        disabledDate (time) {
          return time.getTime() < Date.now() - 24 * 60 * 60 * 1000
        }
      },
      selectForm: {
        hotelName: '',
        cityValue: [],
        room: '',
        mydate: '',
        selectedOptions: [],
        options: regionData,
        searchReturn: {
          hotelName: [],
          imgUrl: [],
          site: [],
          lowestPrice: []
        }
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
      centerDialogVisible1: false,
      imageUrl: '',
      imgReturn: '',
      orderData: [],
      dialogTableVisible1: false,
      dialogTableVisible2: false,
      dialogTableVisible3: false,
      roomData: [],
      mykey: 0,
      userInfo: {
        username: '',
        password: '',
        photoUrl: '',
        mail: ''
      },
      score: null,
      iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'],
      pageSize1: 3,
      currentPage1: 1,
      order: [],
      pwdForm: {
        oldPwd: '',
        newPwd: '',
        checkPwd: ''
      },
      hotelName1: '',
      hotelAddress1: '',
      hotelName2: '',
      hotelAddress2: '',
      hotelName3: '',
      hotelAddress3: ''
    }
  },
  created () {
    let date = new Date()
    let year = date.getFullYear().toString()
    let month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1).toString() : (date.getMonth() + 1).toString()
    let da = date.getDate() < 10 ? '0' + date.getDate().toString() : date.getDate().toString()
    let day3 = new Date()
    day3.setTime(day3.getTime() + 24 * 60 * 60 * 1000)
    let s3 = day3.getFullYear() + '-' + (day3.getMonth() + 1) + '-' + day3.getDate()
    let tomorrow = s3.toString()
    let beg = year + '-' + month + '-' + da
    let end = tomorrow
    this.selectForm.mydate = [beg, end]
    getUserInfo().then(res => {
      this.userInfo.username = res.data.username
      this.userInfo.password = res.data.password
      this.userInfo.mail = res.data.mail
      this.userInfo.photoUrl = res.data.photoUrl
    })
    hotelRecommend().then(res => {
      this.hotelName1 = res.data[0].name
      this.hotelAddress1 = res.data[0].address.detail
      this.hotelName2 = res.data[1].name
      this.hotelAddress2 = res.data[1].address.detail
      this.hotelName3 = res.data[2].name
      this.hotelAddress3 = res.data[2].address.detail
      this.longitude1 = res.data[0].longitude
      this.latitude1 = res.data[0].latitude1
      this.hotelDetail1 = res.data[0]
    })
  },
  methods: {
    handleChange () {
      let loc = ''
      for (let i = 0; i < this.selectForm.selectedOptions.length; i++) {
        loc += CodeToText[this.selectForm.selectedOptions[i]]
      }
      console.info(loc)
      console.info(this.selectForm.selectedOptions[this.selectForm.selectedOptions.length - 1])
    },
    logOut () {
      console.info('hhhhhhh')
      this.$router.push({name: 'login'})
    },
    onSubmit () {
      let myHotel = {name: this.selectForm.hotelName, checkin: this.selectForm.mydate[0], checkout: this.selectForm.mydate[1], roomtype: this.selectForm.room, code: this.selectForm.selectedOptions[this.selectForm.selectedOptions.length - 1]}
      searchHotel(myHotel).then(res => {
        /* this.selectForm.searchReturn.hotelName = res.data[0].name
        this.selectForm.searchReturn.imgUrl = res.data[0].photo.photoUrl
        this.selectForm.searchReturn.site = res.data[0].address.province + res.data[0].address.city + res.data[0].address.district
        this.selectForm.searchReturn.lowestPrice = res.data[0].room.price */
        console.info(res.data)
        /* this.$router.push({name: 'searchResult',
          params: {hotelName: res.data[0].name,
            imgUrl: res.data[0].photo.photoUrl,
            site: res.data[0].address.province + res.data[0].address.city + res.data[0].address.district,
            lowestPrice: res.data[0].room.price}}) */
        this.$router.push({name: 'searchResult', params: {returnData: res.data, userInfo: this.$route.params.userInfo}})
      })
    },
    handler1 ({BMap, map}) {
      console.log(BMap, map)
      this.center.lng = this.longitude1
      this.center.lat = this.latitude1
      this.zoom = 15
    },
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
      this.imgReturn = res.url.slice(1)
      console.info(this.imgReturn)
      // document.setElementById('imgid').setAttribute('src', this.imgReturn)
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
    },
    changePhoto () {
      getTempPhoto().then(res => {
      })
      setTimeout(() => {
        getUserInfo().then(res => {
          this.userInfo.photoUrl = res.data.photoUrl
        })
        this.centerDialogVisible = false
        this.mykey += 1
        console.info('100ms后执行')
      }, 100)
    },
    submitPwd () {
      let myPwd = {oldpaw: this.pwdForm.oldPwd, newpaw: this.pwdForm.newPwd}
      changePassword(myPwd).then(res => {
        console.info(res.data)
        this.$message({
          message: '修改成功',
          type: 'success',
          center: true
        })
        this.$router.push({name: 'login'})
      })
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
        border: 1px dashed #6b9bce;
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
    .userPhoto {
        width: auto;
        height: auto;
        max-width: 100%;
        max-height: 100%;
    }

    .el-header {
        background: url('../../../../assets/images/header.png');
        text-align: center;
        color: #dcdfe6;
        font-family:Arial,Helvetica,sans-serif;
        font-size: large;
        font-weight: bolder;
    }
    .loginOut{
        position: absolute;right:25px;top:123px;
        color: #6b9bce;
        font-weight: 600;
        font-size: 14px;
    }
    .item__content {
        color: #364041;
        font-weight: 600;
        font-size: 20px;
        -webkit-text-stroke: 1px #253d41;
        -webkit-text-fill-color: transparent;
    }

</style>
