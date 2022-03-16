<template>
  <v-container>
    <div class="text-h3">User profile</div>
    <v-row class="my-2">
      <v-col cols="4">
        <img :src="profile.userpic" width="256" alt="userpic"/>
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
        </v-container>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: "ProfilePage",
  computed: mapState(['profile']),
  methods: {
    formatDate: dateStr => new Date(dateStr).toLocaleString()
  }
}
</script>

<style scoped>
</style>