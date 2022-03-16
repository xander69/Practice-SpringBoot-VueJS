<template>
  <v-row>
    <v-col cols="10">
      <v-text-field
          counter="500"
          label="New message"
          placeholder="Write something"
          @keyup.enter="save"
          v-model="text"/>
    </v-col>
    <v-col cols="2">
      <v-btn @click="save">
        Save
      </v-btn>
    </v-col>
  </v-row>
</template>

<script>
import {mapActions} from 'vuex'

export default {
  name: 'MessageForm',
  props: ['messageToEdit'],
  data() {
    return {
      text: '',
      id: ''
    }
  },
  watch: {
    messageToEdit(newVal/*, oldVal*/) {
      this.text = newVal.text
      this.id = newVal.id
    }
  },
  methods: {
    ...mapActions([
      'addMessage',
      'updateMessage'
    ]),
    save() {
      const message = {id: this.id, text: this.text}
      if (this.id) {
        this.updateMessage(message)
      } else {
        this.addMessage(message)
      }
      this.text = ''
      this.id = ''
    }
  }
}
</script>

<style scoped>
</style>