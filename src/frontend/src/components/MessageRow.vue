<template>
  <v-card class="my-2">
    <MediaContent v-if="message.link" :message="message"></MediaContent>
    <v-card-text v-else>
      {{ message.text }}
    </v-card-text>
    <v-card-actions>
      <v-row>
        <v-col cols="1"
               align-self="center"
               class="text-center">
          <img :src="message.author.userpic" alt="userpic" width="24"/>
        </v-col>
        <v-col cols="3"
               align-self="center"
               class="font-italic text-body-2">
          {{ message.author.name }}
        </v-col>
        <v-col cols="3"
               align-self="center"
               class="font-italic text-body-2">
          {{ message.createDt }}
        </v-col>
        <v-col v-if="message.author.id === profile.id" class="text-right">
          <v-btn @click="edit">
            Edit
          </v-btn>
          <v-btn @click="del">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-card-actions>
  </v-card>
</template>

<script>

import {mapActions, mapState} from 'vuex'
import MediaContent from '@/components/media/MediaContent'

export default {
  name: 'MessageRow',
  props: ['message', 'editMessage'],
  components: {MediaContent},
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