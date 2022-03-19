<template>
  <span></span>
</template>

<script>
import {mapActions, mapState} from 'vuex'

export default {
  name: 'LazyLoader',
  methods: mapActions(['loadPage']),
  computed: mapState(['currentPage', 'totalPages']),
  mounted() {
    window.onscroll = () => {
      const element = document.documentElement
      const isBottomOfScreen =
          ((element.scrollTop + window.innerWidth) >= element.offsetHeight)
          && (this.currentPage + 1 < this.totalPages)
      if (isBottomOfScreen) {
        this.loadPage()
      }
    }
  }
}
</script>

<style scoped>

</style>