<template>
  <v-card class="my-2">
    <v-card-header>
      <v-card-avatar>
        <UserPicture :user="message.author" :size="36"/>
      </v-card-avatar>
      <v-card-header-text>
        <router-link :to="`/user/${message.author.id}`">
          {{ message.author.name }}
        </router-link>
        <span class="ml-4">
          {{ message.createDt }}
        </span>
      </v-card-header-text>
      <div v-if="message.author.id === profile.id">
        <v-btn @click="edit" size="small">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
        <v-btn @click="del" size="small" class="ml-2">
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </div>
    </v-card-header>

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
import UserPicture from '@/components/UserPicture'

export default {
  name: 'MessageRow',
  props: ['message', 'editMessage'],
  components: {MediaContent, CommentList, UserPicture},
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