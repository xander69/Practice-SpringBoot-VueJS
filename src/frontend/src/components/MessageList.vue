<template>
  <v-container>
    <MessageForm :messages="messages"
                 :messageToEdit="messageToEdit"/>
    <MessageRow v-for="message in sortedMessages"
                :key="message.id"
                :message="message"
                :messages="messages"
                :editMessage="editMessage"
                :deleteMessage="deleteMessage"/>
  </v-container>
</template>

<script>
import MessageRow from '@/components/MessageRow'
import MessageForm from '@/components/MessageForm'
import {addHandler} from '@/util/ws'
import messageApi from '../util/messages'

export default {
  name: "MessageList",
  components: {
    MessageRow,
    MessageForm
  },
  data() {
    return {
      messageToEdit: null,
      messages: []
    }
  },
  created() {
    messageApi.getAll().then(response => this.messages = response.data)
    addHandler(data => {
      if (data.objectType === 'MESSAGE') {
        let index = this.messages.findIndex(item => item.id === data.body.id)
        switch (data.eventType) {
          case 'CREATE':
          case 'UPDATE':
            if (index > -1) {
              this.messages.splice(index, 1, data.body)
            } else {
              this.messages.push(data.body)
            }
            break
          case 'REMOVE':
            this.messages.splice(index, 1)
            break
          default:
            console.error('Looks like the event type if unknown "${data.eventType}"')

        }
      } else {
        console.error('Looks like the object tpe if unknown "${data.objectType}"')
      }
    })
  },
  computed: {
    sortedMessages() {
      return [...this.messages].sort((a, b) => -(a.id - b.id))
    }
  },
  methods: {
    editMessage(message) {
      this.messageToEdit = message;
    },
    deleteMessage(message) {
      messageApi.remove(message.id)
    }
  }
}
</script>

<style scoped>
</style>