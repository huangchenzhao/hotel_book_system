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
                    <el-col span="4">
                        <div class="place"></div>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="入住地点" prop="cityValue">
                            <el-row>
                                <el-cascader
                                        :options="city.options"
                                        :props="props"
                                        v-model="selectForm.cityValue"
                                        @active-item-change="handleItemChange"
                                        @change="cityChange" style="width: 100%" prefix-icon="el-icon-map-location">
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
                    <el-col span="4">
                        <div class="place"></div>
                    </el-col>
                    <el-col :span="10">
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
                    <el-card :body-style="{ padding: '0px' }">
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
                    <el-card :body-style="{ padding: '0px' }">
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
                    <el-card :body-style="{ padding: '0px' }">
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
        mydate: ''
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
      }],
      // 所在省市
      city: {
        obj: {},
        options: [{
          value: 'zhinan',
          label: '指南',
          children: [{
            value: 'shejiyuanze',
            label: '设计原则',
            children: [{
              value: 'yizhi',
              label: '一致'
            }, {
              value: 'fankui',
              label: '反馈'
            }, {
              value: 'xiaolv',
              label: '效率'
            }, {
              value: 'kekong',
              label: '可控'
            }]
          }, {
            value: 'daohang',
            label: '导航',
            children: [{
              value: 'cexiangdaohang',
              label: '侧向导航'
            }, {
              value: 'dingbudaohang',
              label: '顶部导航'
            }]
          }]
        }]
      },
      props: { // 级联选择器的属性配置
        value: 'value',
        children: 'cities',
        checkStrictly: true
      }
    }
  },
  computed: {
  },
  created () {
    this._initData()
  },
  mounted () {
  },
  methods: {
    _initData () {
      this.$http({
        method: 'get',
        url: this.API.province + '/156' // 中国
      }).then(res => {
        this.city.options = res.data.body.map(item => { // 所在省市
          return {
            value: item.provinceCode,
            label: item.provinceName,
            cities: []
          }
        })
      })
    },
    cellStyle ({row, column, rowIndex, columnIndex}) {
      return 'text-align:center'
    },
    rowClass ({row, rowIndex}) {
      return 'text-align:center'
    },
    getCodeByAreaCode (code) {
      if (code === undefined) return false
      this.$http({
        method: 'get',
        url: this.API.addressCode + '/' + code
      })
        .then(res => {
          if (res.data.code === this.API.SUCCESS) {
            let provinceCode = res.data.body.provinceCode
            let cityCode = res.data.body.cityCode
            let areaCode = res.data.body.areaCode
            this.cityValue = [provinceCode, cityCode, areaCode]
            this.handleItemChange([provinceCode, cityCode])
          }
        })
        .finally(res => {
        })
    },
    handleItemChange (value) {
      let a = (item) => {
        this.$http({
          method: 'get',
          url: this.API.city + '/' + value[0]
        }).then(res => {
          item.cities = res.data.body.map(ite => {
            return {
              value: ite.cityCode,
              label: ite.cityName,
              cities: []
            }
          })
          if (value.length === 2) { // 如果传入的value.length===2 && 先执行的a()，说明是传入了areaCode，需要初始化多选框
            b(item)
          }
        }).finally(_ => {
        })
      }
      let b = (item) => {
        if (value.length === 2) {
          item.cities.find(ite => {
            if (ite.value === value[1]) {
              if (!ite.cities.length) {
                this.$http({
                  method: 'get',
                  url: this.API.area + '/' + value[1]
                }).then(res => {
                  ite.cities = res.data.body.map(ite => {
                    return {
                      value: ite.areaCode,
                      label: ite.areaName
                    }
                  })
                }).finally(_ => {
                })
              }
            }
          })
        }
      }
      this.city.options.find(item => {
        if (item.value === value[0]) {
          if (item.cities.length) {
            b(item)
          } else {
            a(item)
          }
          return true
        }
      })
    },
    getCityCode () {
      return this.cityValue[2]
    },
    reset () {
      this.cityValue = []
    },
    cityChange (value) {
      if (value.length === 3) {
        this.$emit('cityChange', value[2])
      } else {
        this.$emit('cityChange', null)
      }
    }
  },
  watch: {
    areaCode: {
      deep: true,
      immediate: true,
      handler (newVal) {
        if (newVal) {
          this.getCodeByAreaCode(newVal)
        } else {
          this.$nextTick(() => {
            this.reset()
          })
        }
      }
    }
  },
  onSubmit () {
    console.log('submit!')
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
