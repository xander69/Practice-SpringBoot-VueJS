<template>
  <v-card class="my-2">
    <v-list-item class="d-flex">
      <v-list-item-avatar left>
        <v-avatar :image="message.author.userpic"></v-avatar>
      </v-list-item-avatar>
      <v-list-item-header>
        <v-list-item-title>
          {{ message.author.name }}, {{ message.createDt }}
        </v-list-item-title>
      </v-list-item-header>
      <v-list-item-title v-if="message.author.id === profile.id">
        <v-btn @click="edit">
          Edit
        </v-btn>
        <v-btn @click="del">
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </v-list-item-title>
    </v-list-item>

    <MediaContent v-if="message.link" :message="message"></MediaContent>
    <v-card-text v-else class="text-body-1">
      {{ message.text }}
    </v-card-text>

    <v-card-text>
      <CommentList :comments="message.comments"
                   :messageId="message.id"/>
    </v-card-text>
  </v-card>
</template>

<script>

import {mapActions, mapState} from 'vuex'
import MediaContent from '@/components/media/MediaContent'
import CommentList from '@/components/comment/CommentList'

export default {
  name: 'MessageRow',
  props: ['message', 'editMessage'],
  components: {MediaContent, CommentList},
  computed: mapState(['profile']),
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
.v-toolbar {
  width: 100%;
}
</style>