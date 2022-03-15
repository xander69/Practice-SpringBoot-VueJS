<template>
  <v-card class="my-2">
    <MediaContent v-if="message.link" :message="message"></MediaContent>
    <v-card-text v-else>
      {{ message.text }}
    </v-card-text>
    <v-card-actions>
      <v-btn @click="edit">
        Edit
      </v-btn>
      <v-btn @click="del">
        <v-icon>mdi-delete</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>

import {mapActions} from 'vuex'
import MediaContent from '@/components/media/MediaContent'

export default {
  name: 'MessageRow',
  props: ['message', 'editMessage'],
  components: {MediaContent},
  methods: {
    ...mapActions([
      'removeMessage'
    ]),
    edit() {
      this.editMessage(this.message)
    },
    del() {
      this.removeMessage(this.message)
    }
  }
}
</script>

<style scoped>
</style>