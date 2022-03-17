<template>
  <v-container>
    <MessageForm :messageToEdit="messageToEdit"/>
    <MessageRow v-for="message in sortedMessages"
                :key="message.id"
                :message="message"
                :editMessage="editMessage"/>
  </v-container>
</template>

<script>
import MessageRow from '@/components/message/MessageRow'
import MessageForm from '@/components/message/MessageForm'
import {addHandler} from '@/util/ws'
import {mapGetters, mapMutations} from 'vuex'

export default {
  name: "MessageList",
  components: {
    MessageRow,
    MessageForm
  },
  data() {
    return {
      messageToEdit: null
    }
  },
  created() {
    addHandler(data => {
      if (data.objectType === 'MESSAGE') {
        if (data.eventType === 'CREATE') {
          this.addMessageMutation(data.body)
        } else if (data.eventType === 'UPDATE') {
          this.updateMessageMutation(data.body)
        } else if (data.eventType === 'REMOVE') {
          this.deleteMessageMutation(data.body)
        } else {
          console.error('Looks like the event type if unknown "${data.eventType}"')
        }
      } else if (data.objectType === 'COMMENT') {
        if (data.eventType === 'CREATE') {
          this.addCommentMutation(data.body)
        } else {
          console.error('Looks like the event type if unknown "${data.eventType}"')
        }
      } else {
        console.error('Looks like the object tpe if unknown "${data.objectType}"')
      }
    })
  },
  computed: {
    ...mapGetters([
        'sortedMessages'
    ])
  },
  methods: {
    ...mapMutations([
      'addMessageMutation',
      'updateMessageMutation',
      'deleteMessageMutation',
      'addCommentMutation'
    ]),
    editMessage(message) {
      this.messageToEdit = message;
    }
  }
}
</script>

<style scoped>
</style>