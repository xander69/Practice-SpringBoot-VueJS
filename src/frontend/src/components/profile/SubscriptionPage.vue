<template>
  <v-container>
    <v-card v-for="item in subscriptions" v-bind:key="item.id"
            class="pa-5 mb-3">
      <v-row>
        <v-col>
          <UserPicture :user="item.subscriber" :size="24"></UserPicture>
          <span class="pl-10">
            <router-link :to="`/user/${item.subscriber.id}`">
              {{ item.subscriber.name }}
            </router-link>
          </span>
        </v-col>
        <v-col class="text-right">
          <v-btn @click="changeSubscriptionStatus(item.subscriber.id)">
            {{ item.active ? 'Dismiss' : 'Approve' }}
          </v-btn>
        </v-col>
      </v-row>
      <v-row v-if="subscriptions.length === 0">
        <v-col class="text-center font-italic">
          No subscribers
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import UserPicture from '@/components/UserPicture'
import profileApi from '@/api/profile'

export default {
  name: 'SubscriptionPage',
  components: {UserPicture},
  data() {
    return {
      subscriptions: []
    }
  },
  methods: {
    async changeSubscriptionStatus(subscriberId) {
      await profileApi.changeSubscriptionStatus(subscriberId)
      const subscriptionIndex = this.subscriptions.findIndex(item => item.subscriber.id === subscriberId)
      const subscription = this.subscriptions[subscriptionIndex]
      this.subscriptions = [
        ...this.subscriptions.slice(0, subscriptionIndex),
        {
          ...subscription,
          active: !subscription.active
        },
        ...this.subscriptions.slice(subscriptionIndex + 1)
      ]
    }
  },
  async beforeMount() {
    const response = await profileApi.subscriberList(this.$store.state.profile.id)
    this.subscriptions = await response.data
  }
}
</script>

<style scoped>

</style>