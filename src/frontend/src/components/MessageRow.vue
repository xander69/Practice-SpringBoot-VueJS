<template>
  <v-card class="my-2">
    <v-card-text>
      <i>({{ message.id }})</i> {{ message.text }}
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

export default {
  name: "MessageRow",
  props: ['message', 'editMessage'],
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