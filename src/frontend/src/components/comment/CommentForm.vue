<template>
  <v-layout row class="mt-2">
    <v-text-field
        counter="500"
        label="Add comment"
        placeholder="Write something"
        @keyup.enter="save"
        v-model="text"/>
    <v-btn @click="save">
      Add
    </v-btn>
  </v-layout>
</template>

<script>
import {mapActions, mapState} from 'vuex'

export default {
  name: 'CommentForm',
  props: ['messageId'],
  computed: mapState(['profile']),
  data() {
    return {
      text: ''
    }
  },
  methods: {
    ...mapActions(['addComment']),
    async save() {
      await this.addComment({
        text: this.text,
        message: {
          id: this.messageId
        }
      })
      this.text = ''
    }
  }
}
</script>

<style scoped>

</style>