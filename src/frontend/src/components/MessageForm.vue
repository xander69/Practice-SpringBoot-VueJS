<template>
  <div>
    <input type="text" placeholder="Write message" v-model="text"/>
    <input type="button" value="Save" @click="save">
  </div>
</template>

<script>
import axios from "axios"

export default {
  name: "MessageForm",
  props: ['messages', 'messageToEdit', 'addMessage', 'updateMessage'],
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
      const message = {text: this.text}
      if (this.id) {
        axios.put(`/api/message/${this.id}`, message).then(response => {
          const data = response.data
          this.updateMessage(data)
          this.text = ''
          this.id = ''
        })
      } else {
        axios.post('/api/message', message).then(response => {
          this.addMessage(response.data)
          this.text = ''
        })
      }
    }
  }
}
</script>

<style scoped>

</style>