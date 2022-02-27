<template>
  <div>
    <el-card class="crumbs-card">
      <div class="crumbs">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/main/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: '/main/first' }">一级菜单1</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </el-card>
    <el-card class="container">
      <el-tabs v-model="activeName"
               @tab-click="handleClick">
        <el-tab-pane v-for="(item,index) in schoolList"
                     :key="index"
                     :label="item.name"
                     :name="item.id"
                     style="margin:0px">
          <el-row style="border-bottom:1px solid black">
            <el-col :span="12" style="border-right:1px solid black">
              <el-row>
                <p style="border-left:5px solid blue;padding-left:5px;font-size:20px">状态类型</p>
              </el-row>
              <el-row>
                <el-col style="margin-top:20px">正常打卡时间段：</el-col>
                <el-col style="margin-top:20px">晚归打卡时间段：</el-col>
              </el-row>
            </el-col>
            <el-col :span="12" style="padding-left:30px">
              <p style="border-left:5px solid blue;padding-left:5px;font-size:20px">系统规则说明</p>
              <p style="padding-left:5px">学生当天有考勤：超过当天24：00打卡的学生自动视为(未归且缺卡)；
                若学生第二天有/无考勤，则24：00至8：00学生打卡状态为(未归)，过8：00之后学生
                打卡状态为（未到时间），直至正常打卡时间段开始；</p>
            </el-col>

          </el-row>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'second',
  data () {
    return {
      activeName: '1',
      schoolList: [
        {
          name: 'A学院',
          id: 0
        },
        {
          name: 'B学院',
          id: 1
        }, {
          name: 'C学院',
          id: 2
        }
      ]
    }
  },
  methods: {
    haha (key) {
      alert(key)
    }
  }
}
</script>
