<template>
  <svg
    class="icon"
    aria-hidden="true"
  >
    <use :xlink:href="`#${icon}`"></use>
  </svg>
</template>

<script>
export default {
  name: 's-icon',
  props: {
    icon: String
  }
}
</script>
