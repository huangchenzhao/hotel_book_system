<template>
<div>
    <Header></Header>
    <el-tabs type="border-card">
        <el-tab-pane label="搜索结果">
            <el-table
                    :data="hotel.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                    style="width: 100%"
                    max-height="510">
                <el-table-column
                        prop="name"
                        label="名称"
                        sortable
                        width="150" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="photo.photoUrl"
                        label="图片"
                        width="300" align="center" header-align="center" >
                    <template slot-scope="scope">
                        <img :src="scope.row.photo.photoUrl" alt="" width="90" height="90">
                    </template>
                </el-table-column>
              <el-table-column
                        prop="address.detail"
                        label="详细地址"
                        width="200" align="center" header-align="center">
                </el-table-column>
                <el-table-column
                        prop="room.price"
                        label="最低价格"
                        sortable
                        width="150" align="center" header-align="center" >
                </el-table-column>
              <el-table-column
                  prop="star"
                  label="得分"
                  sortable
                  width="200" align="center" header-align="center" >
                  <template slot-scope="scope" >
                      <el-rate v-model="scope.row.star" :allow-half="true" show-score disabled text-color="#ff9900"></el-rate>
                  </template>
              </el-table-column>
                <el-table-column
                        label="操作"
                        align="center" header-align="center" >
                    <template slot-scope="scope">
                        <el-button
                                type="text" @click="handleClick(scope.row)">
                            查看详情
                        </el-button>
                    </template>
                    <el-dialog :visible.sync="dialogVisible" append-to-body="true" width="80%">
                        <el-row :gutter="20">
                            <el-col :span="12">
                              <baidu-map class="bm-view" :center="center" :key="mykey" :zoom="zoom" @ready="handler" ak="3VcKkDmuaFz8ur9Q6RfLP7GKdVyQq6Kl">
                                <bm-marker :position="center" :dragging="false">
                                   <bm-label :content="addressName" :labelStyle="{color: 'red', fontSize : '10px'}" :offset="{width: -35, height: 30}"/>
                                </bm-marker>
                              </baidu-map>
                            </el-col>
                            <el-col :span="12">
                                <el-table :data="room">
                                    <el-table-column property="latitude" label="房型" width="100" align="center" header-align="center"></el-table-column>
                                    <el-table-column property="star" label="图片" width="200" align="center" header-align="center"></el-table-column>
                                    <el-table-column property="name" label="价格" width="100" align="center" header-align="center"></el-table-column>
                                    <el-table-column property="longitude" label="数量" align="center" header-align="center"></el-table-column>
                                </el-table>
                            </el-col>
                        </el-row>
                    </el-dialog>
                </el-table-column>
            </el-table>
            <el-col :span="24" class="toolbar" style="text-align:center">
                <el-pagination
                        @current-change="handleCurrentChange" @size-change="handleSizeChange" :current-page="currentPage"
                        layout="total, prev, pager, next"
                        :page-size="pageSize"
                        :total="this.hotel.length"
                ></el-pagination>
            </el-col>
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
    <el-button type="primary" @click="changePhoto">确 定</el-button>
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
                      {{this.userInfo.password}}
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
            <el-table
                    :data="order.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                    style="width: 100%"
                    max-height="510">
                <el-table-column
                        prop="name"
                        label="酒店名称"
                        width="200" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="roomType"
                        label="房型"
                        width="150" align="center" header-align="center" >
                    <template slot-scope="scope">
                        <img :src="scope.row.photo.photoUrl" alt="" width="90" height="90">
                    </template>
                </el-table-column>
                <el-table-column
                        prop="quantity"
                        label="数量"
                        width="150" align="center" header-align="center">
                </el-table-column>
                <el-table-column
                        prop="totalPrice"
                        label="总价格"
                        sortable
                        width="150" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="checkIn"
                        label="入住时间"
                        width="200" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="checkOut"
                        label="退房时间"
                        width="200" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="state"
                        label="状态"
                        width="150" align="center" header-align="center"><el-tag type="success">标签二</el-tag>
                </el-table-column>
                <el-table-column
                        label="评分"
                        align="center" header-align="center" >
                    <template slot-scope="scope">
                        <el-rate
                                v-model="score"
                                :icon-classes="iconClasses"
                                void-icon-class="icon-rate-face-off"
                                :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
                        </el-rate>
                    </template>
                    <el-dialog :visible.sync="dialogVisible" append-to-body="true" width="80%">
                        <el-row :gutter="20">
                            <el-col :span="12">
                                <baidu-map class="bm-view" :center="center" :key="mykey" :zoom="zoom" @ready="handler" ak="3VcKkDmuaFz8ur9Q6RfLP7GKdVyQq6Kl">
                                    <bm-marker :position="center" :dragging="false">
                                        <bm-label :content="addressName" :labelStyle="{color: 'red', fontSize : '10px'}" :offset="{width: -35, height: 30}"/>
                                    </bm-marker>
                                </baidu-map>
                            </el-col>
                            <el-col :span="12">
                                <el-table :data="room">
                                    <el-table-column property="latitude" label="房型" width="100" align="center" header-align="center"></el-table-column>
                                    <el-table-column property="star" label="图片" width="200" align="center" header-align="center"></el-table-column>
                                    <el-table-column property="name" label="价格" width="100" align="center" header-align="center"></el-table-column>
                                    <el-table-column property="longitude" label="数量" align="center" header-align="center"></el-table-column>
                                </el-table>
                            </el-col>
                        </el-row>
                    </el-dialog>
                </el-table-column>
            </el-table>
            <el-col :span="24" class="toolbar" style="text-align:center">
                <el-pagination
                        @current-change="handleCurrentChange1" @size-change="handleSizeChange1" :current-page="currentPage1"
                        layout="total, prev, pager, next"
                        :page-size="pageSize1"
                        :total="this.order.length"
                ></el-pagination>
            </el-col>
        </el-tab-pane>
    </el-tabs>
</div>
</template>

<script>
import Header from '../../../../components/Header'
import BaiduMap from 'vue-baidu-map/components/map/Map.vue'
import {getDetail, getUserInfo, getTempPhoto} from '@/api/api'

export default {
  name: 'searchResult',
  components: {Header, BaiduMap},
  data () {
    return {
      center: {lng: 0, lat: 0},
      zoom: 3,
      hotel: [],
      room: [],
      dialogVisible: false,
      centerDialogVisible: false,
      imageUrl: '',
      imgReturn: '',
      order: [],
      roomData: [],
      latitude: 0,
      longitude: 0,
      mykey: 0,
      mykeyPhoto: 0,
      addressName: '',
      pageSize: 3,
      currentPage: 1,
      returnDetail: [],
      userInfo: {
        username: '',
        password: '',
        photoUrl: '',
        mail: ''
      },
      score: null,
      iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'],
      pageSize1: 3,
      currentPage1: 1
    }
  },
  created () {
    // this.hotel = this.$route.params.hotelName
    /* console.info(this.$route.params.hotelName) */
    console.info(this.$route.params.returnData)
    this.hotel = this.$route.params.returnData
    getUserInfo().then(res => {
      this.userInfo.username = res.data.username
      this.userInfo.password = res.data.password
      this.userInfo.mail = res.data.mail
      this.userInfo.photoUrl = res.data.photoUrl
    })
  },
  methods: {
    handleClick (row) {
      this.dialogVisible = true
      this.room = row
      this.longitude = row.longitude
      this.latitude = row.latitude
      this.addressName = row.address.detail
      let myDetail = {hid: row.hid}
      getDetail(myDetail).then(res => {
        this.returnDetail = res.data
      })
      this.mykey += 1
    },
    handleCurrentChange (currentPage) {
      this.currentPage = currentPage
    },
    handleCurrentChange1 (currentPage) {
      this.currentPage = currentPage
    },
    handleSizeChange1 (val) {
      this.pageSize = val
    },
    handler ({BMap, map}) {
      console.log(BMap, map)
      this.center.lng = this.longitude
      this.center.lat = this.latitude
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
    handleSizeChange (val) {
      this.pageSize = val
    },
    changePhoto () {
      getTempPhoto().then(res => {
      })
      getUserInfo().then(res => {
        this.userInfo.photoUrl = res.data.photoUrl
      })
      this.centerDialogVisible = false
      this.mykeyPhoto += 1
    }
  }
}
</script>

<style scoped>
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
    .bm-view {
      width: 100%;
      height: 300px;
    }
    .userPhoto {
        width: auto;
        height: auto;
        max-width: 100%;
        max-height: 100%;
    }
</style>
