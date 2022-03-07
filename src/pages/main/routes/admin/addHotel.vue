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
                            <el-menu-item index="/hotel/manage">酒店管理</el-menu-item>
                        </el-submenu>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-sell"></i>
                                <span>订单信息</span>
                            </template>
                            <el-menu-item index="/order/list">订单列表</el-menu-item>
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
                                <el-form-item label="酒店星级" prop="lat">
                                    <el-rate
                                            v-model="hotelForm.star"
                                            :colors="colors">
                                    </el-rate>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="6">
                                <el-form-item label="房型" prop="room1">
                                    <el-select v-model="hotelForm.room1" :disabled="true" placeholder="请选择房型" style="width: 100%" prefix-icon="el-icon-house">
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
                                    <el-input-number v-model="hotelForm.quantity1" :min="0" :max="100"></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="房间价格" prop="price1">
                                    <el-input-number v-model="hotelForm.price1" :precision="2" :step="0.1" :max="5000"></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="标准间图片" prop="roomPhoto1">
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
                            <el-col :span="6">
                                <el-form-item label="房型" prop="room2">
                                    <el-select v-model="hotelForm.room2" :disabled="true" placeholder="请选择房型" style="width: 100%" prefix-icon="el-icon-house">
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
                                <el-form-item label="房间数量" prop="quantity2">
                                    <el-input-number size="medium" v-model="hotelForm.quantity2" :min="0" :max="100" label="描述文字"></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="房间价格" prop="price2">
                                    <el-input-number v-model="hotelForm.price2" :precision="2" :step="0.1" :max="5000"></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="双人间图片" prop="roomPhoto2">
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
                            <el-col :span="6">
                                <el-form-item label="房型" prop="room3">
                                    <el-select v-model="hotelForm.room3" :disabled="true" placeholder="请选择房型" style="width: 100%" prefix-icon="el-icon-house">
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
                                <el-form-item label="房间数量" prop="quantity3">
                                    <el-input-number size="medium" v-model="hotelForm.quantity3" :min="0" :max="100" label="描述文字"></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="房间价格" prop="price3">
                                    <el-input-number v-model="hotelForm.price3" :precision="2" :step="0.1" :max="5000"></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="大床房图片" prop="roomPhoto3">
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
                            <el-col :span="6">
                                <el-form-item label="房型" prop="room4">
                                    <el-select v-model="hotelForm.room4" :disabled="true" placeholder="请选择房型" style="width: 100%" prefix-icon="el-icon-house">
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
                                <el-form-item label="房间数量" prop="quantity4">
                                    <el-input-number size="medium" v-model="hotelForm.quantity4" :min="0" :max="100" label="描述文字"></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="房间价格" prop="price4">
                                    <el-input-number v-model="hotelForm.price4" :precision="2" :step="0.1" :max="5000"></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="亲子房图片" prop="roomPhoto4">
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
                            <el-col :span="6">
                                <el-form-item label="房型" prop="room5">
                                    <el-select v-model="hotelForm.room5" :disabled="true" placeholder="请选择房型" style="width: 100%" prefix-icon="el-icon-house">
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
                                <el-form-item label="房间数量" prop="quantity5">
                                    <el-input-number size="medium" v-model="hotelForm.quantity5" :min="0" :max="100" label="描述文字"></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="房间价格" prop="price5">
                                    <el-input-number v-model="hotelForm.price5" :precision="2" :step="0.1" :max="5000"></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="总统套房图片" prop="roomPhoto5">
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
                            <el-col :span="12" :offset="9">
                                <el-button type="primary" @click="hotelSubmit">立即创建</el-button>
                                <el-button>取消</el-button>
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
        room1: '标准间',
        lng: 1,
        lat: 1,
        quantity1: 0,
        roomPhoto1: '',
        room2: '双人间',
        room3: '大床房',
        room4: '亲子房',
        room5: '总统套房',
        price1: 1,
        price2: 1,
        price3: 1,
        price4: 1,
        price5: 1,
        quantity2: 0,
        quantity3: 0,
        quantity4: 0,
        quantity5: 0,
        roomPhoto5: '',
        roomPhoto2: '',
        roomPhoto3: '',
        roomPhoto4: '',
        areaCode: '',
        province: '',
        city: '',
        district: '',
        star: null
      },
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
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
    hotelSubmit () {
      if ((this.hotelForm.quantity1 +
          this.hotelForm.quantity2 +
          this.hotelForm.quantity3 +
          this.hotelForm.quantity4 +
          this.hotelForm.quantity5) === 0) {
        this.$message.error({message: '请添加至少一间房型！', center: true})
      } else {

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
