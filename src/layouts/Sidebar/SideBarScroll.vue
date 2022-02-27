<template>
  <div
    class="sidebarScroll"
    ref="sidebarScroll"
    v-bar
    :style="{height: height}"
  >
    <div>
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: 'side-bar-scroll',
  props: {
    'collapse': Boolean
  },
  data () {
    return {
      height: '100%'
    }
  },
  watch: {
    collapse: function (newVal, oldVal) {
      if (newVal !== oldVal) {
        this.setScrollTop(0)
      }
    }
  },
  mounted () {
    this.setHeight()
  },
  methods: {
    setHeight () {
      let logoHeight = document.getElementById('sidebar-logo').offsetHeight
      this.height = `calc(100% - ${logoHeight}px)`
    },
    setScrollTop (top) {
      let ref = this.$vuebar.getState(this.$refs['sidebarScroll'])
      ref.el2.scrollTop = top
    }
  }
}
</script>

<style lang="less" scoped>
  .scroll-container {
    width: 100%;
    background-color: transparent;
  }
</style>
