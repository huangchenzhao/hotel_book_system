<template>
  <div :class="[{'white': theme === 'light'}, 'app-wrapper', {'hideSidebar': collapse}]">
  <s-sidebar
    class="sidebar-container"
    :collapse="collapse"
    :logo="logo"
    :theme="theme"
    :sidebar-list="sidebarList"
    :background-color="sidebarBackgroundColor"
    :text-color="sidebarTextColor"
    :active-text-color="sidebarActiveTextColor"
  />
  <div class="main-container">
    <s-header
      :collapse-trigger="collapseTrigger"
      :collapse="collapse"
      :logout="logout"
      :userName="userName"
      :roleName="roleName"
    />
    <div class="layout-content" v-bar ref="scroll_div">
      <div>
        <transition name="fade-transverse">
          <router-view></router-view>
        </transition>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import Header from './Header'
import Sidebar from './Sidebar/Sidebar'
import {mapGetters} from 'vuex'

export default {
  name: 'suncaper-layout',
  props: {
    theme: String,
    sidebarList: Array,
    logo: Object,
    logout: Function
  },
  data () {
    return {
      collapse: false
    }
  },
  watch: {
    '$route': function () {
      this.setScrollTop(0)
    }
  },
  computed: {
    ...mapGetters({
      userType: 'user/getUserType',
      userName: 'user/getUserName',
      getRoleName: 'role/getRoleName',
      getSidebarTheme: 'sidebar/getSidebarTheme'
    }),
    roleName () {
      return this.getRoleName(this.userType)
    },
    sidebarBackgroundColor () {
      return this.getSidebarTheme(this.theme).background
    },
    sidebarTextColor () {
      return this.getSidebarTheme(this.theme).text
    },
    sidebarActiveTextColor () {
      return this.getSidebarTheme(this.theme).activeText
    }
  },
  methods: {
    collapseTrigger: function () {
      this.collapse = !this.collapse
    },
    setScrollTop (top) {
      let ref = this.$vuebar.getState(this.$refs['scroll_div'])
      ref.el2.scrollTop = top
    }
  },
  components: {
    's-header': Header,
    's-sidebar': Sidebar
  }
}
</script>
<style lang="less" scoped>
  .app-wrapper {
    position: relative;
    height: 100%;
    width: 100%;
    background-color: #f3f5f9;
  }
  .layout-content {
    height: calc(~'100% - 60px');
    width: 100%;
    overflow: auto;
  }
</style>
