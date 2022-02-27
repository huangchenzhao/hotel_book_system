<template>
  <div
    class="scroll-container"
    ref="scrollContainer"
    :style="{height: height}"
    @wheel.prevent="handleScroll"
  >
    <div class="scroll-wrapper" ref="scrollWrapper" :style="{top: top + 'px'}">
      <slot></slot>
    </div>
  </div>
</template>

<script>
const delta = 15
export default {
  name: 'scrollBar',
  props: {
    'collapse': Boolean
  },
  data () {
    return {
      top: 0,
      height: '100%'
    }
  },
  watch: {
    collapse: function (newVal, oldVal) {
      if (newVal !== oldVal && this.top) {
        this.top = 0
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
    handleScroll (e) {
      const eventDelta = e.wheelDelta || -e.deltaY * 3
      const $container = this.$refs.scrollContainer
      const $containerHeight = $container.offsetHeight
      const $wrapper = this.$refs.scrollWrapper
      const $wrapperHeight = $wrapper.offsetHeight
      if (eventDelta > 0) {
        this.top = Math.min(0, this.top + eventDelta)
      } else {
        if ($containerHeight - delta < $wrapperHeight) {
          if (this.top < -($wrapperHeight - $containerHeight + delta)) {
            this.top = this.top
          } else {
            this.top = Math.max(this.top + eventDelta, $containerHeight - $wrapperHeight - delta)
          }
        } else {
          this.top = 0
        }
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .scroll-container {
    position: relative;
    width: 100%;
    background-color: transparent;
    .scroll-wrapper {
      position: absolute;
      width: 100%!important;
    }
  }
</style>
