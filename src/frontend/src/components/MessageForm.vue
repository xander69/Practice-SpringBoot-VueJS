<template>
  <div>
    <input type="text" placeholder="Write message" v-model="text"/>
    <input type="button" value="Save" @click="save">
  </div>
</template>

<script>
import {sendMessage} from '@/util/ws'

export default {
  name: "MessageForm",
  props: ['messages', 'messageToEdit'],
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
    save() {
      const message = {id: this.id, text: this.text}
      sendMessage(message)
      this.text = ''
      this.id = ''
    }
  }
}
</script>

<style scoped>

</style>