<template>
  <v-row>
    <v-col cols="10">
      <v-text-field
          counter="500"
          label="New message"
          placeholder="Write something"
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
import messageApi from '../util/messages'

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
      if (this.id) {
        messageApi.update(message)
      } else {
        messageApi.add(message)
      }
      this.text = ''
      this.id = ''
    }
  }
}
</script>

<style scoped>

</style>