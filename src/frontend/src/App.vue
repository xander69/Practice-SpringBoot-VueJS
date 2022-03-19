<template>
  <v-app>
    <v-toolbar>
      <v-toolbar-title>
        Messenger with Vue-3
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn text
             @click="showMessages">
        Messages
      </v-btn>
      <v-btn @click="showProfile">
        <span class="mr-2">Profile</span>
        <UserPicture :user="profile" :size="40"></UserPicture>
      </v-btn>
      <v-tooltip bottom>
        <template v-slot:activator="{props}">
          <v-btn @click="logout"
                 v-bind="props">
            <v-icon>mdi-export</v-icon>
          </v-btn>
        </template>
        <span>Logout</span>
      </v-tooltip>
    </v-toolbar>
    <v-main>
      <router-view/>
    </v-main>
    <v-footer app>
      <v-card
          elevation="0"
          width="100%"
          class="text-center">
        <v-card-text>
          Xander (c) 2022
        </v-card-text>
      </v-card>
    </v-footer>
  </v-app>
</template>

<script>
import axios from 'axios'
import {connectWs, disconnectWs} from '@/util/ws'
import {mapActions, mapGetters} from 'vuex'
import UserPicture from '@/components/UserPicture'

const AUTHORIZATION_HEADER = 'Authorization'
const TOKEN_MIN_VALIDITY_SECONDS = 70

export default {
  name: 'App',
  props: ['keycloak'],
  components: {UserPicture},
  created() {
    axios.interceptors.request.use(async config => {
      await this.keycloak.updateToken(TOKEN_MIN_VALIDITY_SECONDS)
      const token = this.keycloak.token
      config.headers.common[AUTHORIZATION_HEADER] = `Bearer ${token}`
      return config
    })
    axios.interceptors.response.use((response) => {
      return response
    })
    this.setProfile()
    this.loadPage()
    connectWs()
  },
  computed: {
    ...mapGetters([
      'profile'
    ])
  },
  methods: {
    ...mapActions([
      'setProfile',
      'loadPage'
    ]),
    showMessages() {
      this.$router.push('/')
    },
    showProfile() {
      this.$router.push('/user')
    },
    logout() {
      const logoutOptions = {redirectUri: "http://localhost:8080/"}
      this.keycloak.logout(logoutOptions).then((success) => {
        disconnectWs()
        console.log("--> log: logout success ", success)
      }).catch((error) => {
        console.log("--> log: logout error ", error)
      });
    }
  }
}
</script>

<style>
</style>
