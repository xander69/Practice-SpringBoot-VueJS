<template>
  <div style="position: relative; width: 300px;">
    <MessageForm :messages="messages"
                 :messageToEdit="messageToEdit"
                 :addMessage="addMessage"
                 :updateMessage="updateMessage"/>
    <MessageRow v-for="message in messages"
                :key="message.id"
                :message="message"
                :messages="messages"
                :editMessage="editMessage"
                :deleteMessage="deleteMessage"/>
  </div>
</template>

<script>
import MessageRow from '@/components/MessageRow'
import MessageForm from '@/components/MessageForm'
import axios from "axios"

function getMessageIndex(list, id) {
  for (let i = 0; i < list.length; i++) {
    if (list[i].id === id) {
      return i
    }
  }
  return -1
}

export default {
  name: "MessageList",
  components: {
    MessageRow, MessageForm
  },
  data() {
    return {
      messageToEdit: null,
      messages: []
    }
  },
  created() {
    axios.get('/api/message')
        .then(response => {
          this.messages = response.data
        })
  },
  methods: {
    editMessage(message) {
      this.messageToEdit = message;
    },
    addMessage(message) {
      this.messages.push(message)
    },
    updateMessage(message) {
      const index = getMessageIndex(this.messages, message.id);
      this.messages.splice(index, 1, message);
    },
    deleteMessage(message) {
      axios.delete(`/api/message/${message.id}`).then(response => {
        if (response.status === 200) {
          this.messages.splice(this.messages.indexOf(message), 1);
        }
      })
    }
  }
}
</script>

<style scoped>

</style>