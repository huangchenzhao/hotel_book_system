<template>
<div>
    <Header></Header>
    <el-tabs type="border-card">
        <el-tab-pane label="搜索结果">
            <el-table
                    :data="hotel"
                    style="width: 100%"
                    max-height="510">
                <el-table-column
                        prop="id"
                        label="序号"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="名称"
                        width="250">
                </el-table-column>
                <el-table-column
                        prop="photo"
                        label="图片"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="地址"
                        width="250">
                </el-table-column>
                <el-table-column
                        prop="price"
                        label="最低价格"
                        width="200">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="120">
                    <el-button
                            @click.native.prevent="deleteRow(scope.$index, hotelData)"
                            type="text"
                            size="small" @click="dialogTableVisible = true">
                        查看详情
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
/* import {hotel} from '../../../../api/api' */

export default {
  name: 'searchResult',
  components: {Header},
  methods: {
    deleteRow (index, rows) {
      rows.splice(index, 1)
    }
    /* getHotel: function (obj) {
      hotel(obj)
    } */
  },
  data () {
    return {
      hotel: [],
      room: [],
      dialogTableVisible: false
    }
  }
}
</script>

<style scoped>

</style>
