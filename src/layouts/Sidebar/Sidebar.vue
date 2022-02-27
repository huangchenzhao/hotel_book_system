<template>
  <div>
    <div id="sidebar-logo" class="sidebar-logo" :style="{backgroundColor: theme === 'light' ? '#ffffff' : '#1f2c35'}">
      <div class="sidebar-logo-png">
      <img :src="logo.src" :alt="logo.alt" />
      </div>
      <transition name="fade" :duration="300">
        <div v-if="!collapse" class="sidebar-logo-title">
          <p>{{logo.firstDes}}</p>
          <p>{{logo.secondDes}}</p>
        </div>
      </transition>
    </div>
    <s-side-bar-scroll
      :collapse="collapse"
    >
      <s-menu
        mode="vertical"
        :default-active="defaultActive"
        :collapse="collapse"
        :background-color="backgroundColor"
        :text-color="textColor"
        :active-text-color="activeTextColor"
        @select="handleMenuSelect"
      >
        <s-sidebar-item
          :sidebar-list="sidebarList"
        />
      </s-menu>
    </s-side-bar-scroll>
  </div>

</template>
<script>
import {Menu} from 'element-ui'
import {_isArrayFn} from '@/tools/utils'
import SideBarScroll from './SideBarScroll'
import SidebarItem from './SidebarItem'

export default {
  name: 's-navbar',
  props: {
    sidebarList: Array,
    logo: Object,
    collapse: Boolean,
    backgroundColor: String,
    textColor: String,
    activeTextColor: String,
    theme: String
  },
  components: {
    's-menu': Menu,
    's-sidebar-item': SidebarItem,
    's-side-bar-scroll': SideBarScroll
  },
  computed: {
    defaultActive () {
      let pathList = this.getRoutesList()
      let Route = this.$route
      let active = ''
      let urlMap = {}
      Route.matched.forEach(item => {
        urlMap[item.path] = true
      })
      let found = pathList.find(item => urlMap[item])
      if (found) {
        active = found
        return active
      }
      pathList.some(item => {
        if (Route.path.indexOf(item) === 0) {
          active = item
          return true
        }
        return false
      })
      return active
    }
  },
  methods: {
    getRoutesList () {
      let pathList = []
      this.findAllPath(this.sidebarList, pathList)
      return pathList
    },
    findAllPath (arr, list) {
      arr.forEach(item => {
        if (_isArrayFn(item.children)) {
          this.findAllPath(item.children, list)
        } else {
          list.push(item.path)
        }
      })
    },
    handleMenuSelect (route) {
      if (route.indexOf('http://') > -1 || route.indexOf('https://') > -1) {
        window.open(route)
      } else {
        this.$router.push(route)
      }
    }
  }
}

</script>
