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
                            <el-menu-item index="1-3">酒店管理</el-menu-item>
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
                            <el-menu-item index="3-1">用户列表</el-menu-item>
                            <el-menu-item index="3-2">用户管理</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main class="el-main">
                    <el-form :model="hotelForm" :rules="rules" ref="ruleForm" class="hotelForm" :label-position="labelPosition">
                        <el-row :gutter="20">
                            <el-col :span="6">
                                <el-form-item label="酒店名称" prop="name">
                                    <el-input v-model="hotelForm.name" placeholder="请输入酒店名称"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="酒店省市区" prop="cityValue">
                                    <el-cascader
                                            size="large"
                                            :options="hotelForm.options"
                                            v-model="hotelForm.selectedOptions"
                                            @change="handleChange" style="width: 100%">
                                    </el-cascader>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="酒店地址" prop="address">
                                    <el-input v-model="hotelForm.address" placeholder="请填写具体地址，如：'西航港街道101号'"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="酒店图片" prop="photo">
                                    <el-upload
                                            class="avatar-uploader"
                                            action="/api/file/upload"
                                            :http-request="uploadImg"
                                            :on-success="handleAvatarSuccess"
                                            :before-upload="beforeAvatarUpload">
                                        <el-button type="primary">点击上传</el-button>
<!--                                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
                                    </el-upload>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="16">
                                <baidu-map
                                        :scroll-wheel-zoom="true"
                                        :center="center"
                                        :zoom="zoom"
                                        class="bm-view"
                                        ak="3VcKkDmuaFz8ur9Q6RfLP7GKdVyQq6Kl"
                                        @moving="syncCenterAndZoom"
                                        @moveend="syncCenterAndZoom"
                                        @zoomend="syncCenterAndZoom"
                                        @ready="handler">
                                    <bm-marker :position="center" :dragging="false">
                                        <bm-label :content="addressName" :labelStyle="{color: 'red', fontSize : '10px'}" :offset="{width: -35, height: 30}"/>
                                    </bm-marker>
                                </baidu-map>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="酒店经度" prop="lng">
                                <el-input v-model.number="center.lng" ></el-input>
                                </el-form-item>
                                    <el-form-item label="酒店纬度" prop="lat">
                                <el-input v-model.number="center.lat"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row type="flex" justify="space-around">
                            <el-col :span="6">
                                <el-form-item label="房型" prop="room1">
                                    <el-select v-model="hotelForm.room1" placeholder="请选择房型" style="width: 100%" prefix-icon="el-icon-house">
                                        <el-option
                                                v-for="item in roomOptions"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="房间数量" prop="quantity1">
                                    <el-input-number size="medium" v-model="hotelForm.quantity1" @change="handleRoom1Num" :min="1" :max="100" label="描述文字"></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="房间1图片" prop="roomPhoto1">
                                    <el-upload
                                            class="avatar-uploader"
                                            action="/api/file/upload"
                                            :http-request="uploadImg"
                                            :on-success="handleAvatarSuccess"
                                            :before-upload="beforeAvatarUpload">
                                        <el-button type="primary">点击上传</el-button>
<!--                                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
                                    </el-upload>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row type="flex" justify="space-around">
                            <el-col :span="6">
                                <el-form-item label="房型" prop="room2">
                                    <el-select v-model="hotelForm.room2" @change="handleRoom1Num" placeholder="请选择房型" style="width: 100%" prefix-icon="el-icon-house">
                                        <el-option
                                                v-for="item in roomOptions"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="房间数量" prop="quantity1">
                                    <el-input-number size="medium" v-model="hotelForm.quantity2" :min="1" :max="100" label="描述文字"></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="房间2图片" prop="roomPhoto2">
                                    <el-upload
                                            class="avatar-uploader"
                                            action="/api/file/upload"
                                            :http-request="uploadImg"
                                            :on-success="handleAvatarSuccess"
                                            :before-upload="beforeAvatarUpload">
                                        <el-button type="primary">点击上传</el-button>
                                        <!--                                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
                                    </el-upload>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import Header from '../../../../components/Header'
import { regionData, CodeToText, addHotel } from 'element-china-area-data'
export default {
  name: 'addHotel',
  components: {Header},
  data () {
    return {
      hotelForm: {
        name: '',
        cityValue: [],
        selectedOptions: [],
        options: regionData,
        address: '',
        photo: '',
        imageUrl: '',
        room1: '',
        lng: 1,
        lat: 1,
        quantity1: 1,
        roomPhoto1: '',
        room2: '',
        quantity2: 1,
        roomPhoto2: '',
        areaCode: '',
        province: '',
        city: '',
        district: ''
      },
      labelPosition: 'top',
      center: {
        lng: 116.404,
        lat: 39.915
      },
      zoom: 15,
      roomOptions: [{
        id: 1,
        value: '标准间',
        label: '标准间',
        disabled: false
      }, {
        id: 2,
        value: '双人房',
        label: '双人房',
        disabled: false
      }, {
        id: 3,
        value: '大床房',
        label: '大床房',
        disabled: false
      }, {
        id: 4,
        value: '亲子房',
        label: '亲子房',
        disabled: false
      }, {
        id: 5,
        value: '总统套房',
        label: '总统套房',
        disabled: false
      }],
      rules: {
        name: [
          { required: true, message: '请输入酒店名称', trigger: 'blur' }
        ],
        cityValue: [
          { required: true, message: '请选择省市区', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请填写具体地址，如：\'西航港街道101号\'', trigger: 'blur' }
        ],
        room1: [
          { required: true, message: '请选择房型', trigger: 'blur' }
        ]
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
    handleChange () {
      this.hotelForm.province = CodeToText[this.hotelForm.selectedOptions[0]]
      this.hotelForm.city = CodeToText[this.hotelForm.selectedOptions[1]]
      this.hotelForm.district = CodeToText[this.hotelForm.selectedOptions[2]]
    },
    handleAvatarSuccess (res, file) {
      this.hotelForm.imageUrl = URL.createObjectURL(file.raw)
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
    commitHotel () {
      let newHotel = {name: this.hotelForm.name,
        detail: this.hotelForm.address,
        province: this.hotelForm.province,
        city: this.hotelForm.city,
        district: this.hotelForm.district,
        areaCode: this.hotelForm.areaCode,
        longitude: this.center.lng,
        latitude: this.center.lat
      }
      addHotel(newHotel).then(res => {
        console.info(res.data)
      })
    },
    syncCenterAndZoom (e) {
      const {lng, lat} = e.target.getCenter()
      this.center.lng = lng
      this.center.lat = lat
      this.zoom = e.target.getZoom()
    },
    handler ({BMap, map}) {
      console.log(BMap, map)
      this.center.lng = 116.404
      this.center.lat = 39.915
      this.zoom = 15
    },
    handleRoom1Num (value) {
      console.log(value)
    },
    handleRoom1Type () {
      for (var i = 1; i < 6; i++) {
        this.roomOptions.forEach()
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
        font-family:Arial,Helvetica,sans-serif;
        font-size: large;
        font-weight: bolder;
    }

    .el-aside {
        background-color: #336699;
        text-align: center;
        line-height: 200px;
        height:100vh;
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
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

    .bm-view {
      width: 100%;
      height: 300px;
    }

</style>
