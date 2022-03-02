<template>
<div>
    <Header></Header>
    <el-tabs type="border-card">
        <el-tab-pane label="搜索结果">
            <el-table
                    :data="hotel"
                    style="width: 100%"
                    max-height="510">
<!--                <el-table-column
                        prop="id"
                        label="序号"
                        width="150">
                </el-table-column>-->
                <el-table-column
                        prop="name"
                        label="名称"
                        sortable
                        width="250">
                </el-table-column>
                <el-table-column
                        prop="photo.photoUrl"
                        label="图片"
                        width="200">
                </el-table-column>
              <el-table-column label="地址">
                <el-table-column
                        prop="address.province"
                        label="省"
                        width="90">
                </el-table-column><el-table-column
                        prop="address.city"
                        label="市"
                        width="90">
                </el-table-column><el-table-column
                        prop="address.district"
                        label="区"
                        width="90">
                </el-table-column>
              </el-table-column>
                <el-table-column
                        prop="room.price"
                        label="最低价格"
                        sortable
                        width="200">
                </el-table-column>
              <el-table-column
                  prop="star"
                  label="星级"
                  sortable
                  width="200">
              </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="160">
                    <el-button
                            @click.native.prevent="deleteRow(scope.$index, hotelData)"
                            type="text"
                            size="small" @click="dialogTableVisible = true">
                        查看详情
                    </el-button>
                  <el-button
                      @click.native.prevent="deleteRow(scope.$index, hotelData)"
                      type="text"
                      size="small" @click="dialogTableVisible = true">
                    查看地图
                  </el-button>
                    <el-dialog title="收货地址" :visible.sync="dialogTableVisible">
                        <el-table :data="room">
                            <el-table-column property="date" label="日期" width="150"></el-table-column>
                            <el-table-column property="name" label="姓名" width="200"></el-table-column>
                            <el-table-column property="address" label="地址"></el-table-column>
                        </el-table>
                    </el-dialog>
                </el-table-column>
            </el-table>
        </el-tab-pane>
        <el-tab-pane label="我的账户">配置管理</el-tab-pane>
        <el-tab-pane label="我的订单">角色管理</el-tab-pane>
    </el-tabs>
</div>
</template>

<script>
import Header from '../../../../components/Header'

export default {
  name: 'searchResult',
  components: {Header},
  data () {
    return {
      hotel: [],
      room: [],
      dialogTableVisible: false,
      value: 3.7
    }
  },
  methods: {
    deleteRow (index, rows) {
      rows.splice(index, 1)
    }
  },
  created () {
    // this.hotel = this.$route.params.hotelName
    /* console.info(this.$route.params.hotelName) */
    console.info(this.$route.params.returnData)
    this.hotel = this.$route.params.returnData
  }
}
</script>

<style scoped>

</style>
