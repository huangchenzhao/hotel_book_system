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
        <el-tab-pane label="我的账户">配置管理</el-tab-pane>
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
  props: {
    areaCode: null
  },
  model: {
    prop: 'areaCode',
    event: 'cityChange'
  },

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
        value: '选项1',
        label: '标准间'
      }, {
        value: '选项2',
        label: '双人房'
      }, {
        value: '选项3',
        label: '大床房'
      }, {
        value: '选项4',
        label: '亲子房'
      }, {
        value: '选项5',
        label: '总统套房'
      }]
    }
  },
  methods: {
    handleChange () {
      let loc = ''
      for (let i = 0; i < this.selectForm.selectedOptions.length; i++) {
        loc += CodeToText[this.selectForm.selectedOptions[i]]
      }
      alert(loc)
    },
    onSubmit () {
      console.log('submit!')
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
</style>
