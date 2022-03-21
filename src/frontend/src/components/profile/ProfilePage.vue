<template>
  <v-container>
    <div class="text-h3">User profile</div>
    <v-row class="my-2">
      <v-col cols="4">
        <UserPicture :user="profile" :size="256"/>
      </v-col>
      <v-col>
        <v-container>
          <v-row>
            <v-col cols="3">Name:</v-col>
            <v-col class="align-content-start">{{ profile.name }}</v-col>
          </v-row>
          <v-row>
            <v-col cols="3">Email:</v-col>
            <v-col>{{ profile.email }}</v-col>
          </v-row>
          <v-row>
            <v-col cols="3">Gender:</v-col>
            <v-col>{{ profile.gender }}</v-col>
          </v-row>
          <v-row>
            <v-col cols="3">Locale:</v-col>
            <v-col>{{ profile.locale }}</v-col>
          </v-row>
          <v-row>
            <v-col cols="3">Last visit:</v-col>
            <v-col>{{ formatDate(profile.lastVisit) }}</v-col>
          </v-row>
          <v-row>
            <v-col cols="3">Subscriptions:</v-col>
            <v-col>{{ profile.subscriptions && profile.subscriptions.length }}</v-col>
          </v-row>
          <v-row>
            <v-col cols="3">Subscribers:</v-col>
            <v-col>{{ profile.subscribers && profile.subscribers.length }}</v-col>
          </v-row>
        </v-container>
        <v-btn v-if="!isMyProfile"
               @click="changeSubscription">
          {{ isSubscribed ? 'Unsubscribe' : 'Subscribe' }}
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import profileApi from '@/api/profile'
import UserPicture from '@/components/UserPicture'

export default {
  name: 'ProfilePage',
  components: {UserPicture},
  data() {
    return {
      profile: {}
    }
  },
  computed: {
    isMyProfile() {
      return !this.$route.params.id
          || this.$route.params.id === this.$store.state.profile.id
    },
    isSubscribed() {
      return this.profile.subscribers &&
          this.profile.subscribers.find(subscription => {
            return subscription.subscriber === this.$store.state.profile.id
          })
    }
  },
  beforeMount() {
    this.updateProfile()
  },
  watch: {
    '$route'() {
      this.updateProfile()
    }
  },
  methods: {
    formatDate: dateStr => new Date(dateStr).toLocaleString(),
    async changeSubscription() {
      const response = await profileApi.changeSubscription(this.profile.id)
      this.profile = await response.data
    },
    async updateProfile() {
      const id = this.$route.params.id || this.$store.state.profile.id
      const response = await profileApi.get(id)
      this.profile = await response.data
      this.$forceUpdate()
    }
  }
}
</script>

<style scoped>
</style>