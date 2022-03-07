<template>
  <div>
    <div id="userpic">
      <img :src="user.userpic" alt="userpic"/><br/>
      <a href="#" @click="logout">Sign out</a>
    </div>
    <h1>Hello, {{ user.name }}!</h1>
    <MessageList/>
  </div>
</template>

<script>
import axios from 'axios'
import MessageList from '@/components/MessageList'

const AUTHORIZATION_HEADER = 'Authorization'
const TOKEN_MIN_VALIDITY_SECONDS = 70

export default {
  name: 'App',
  props: ['keycloak'],
  components: {
    MessageList
  },
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
  },
  data() {
    return {
      user: {}
    }
  },
  mounted() {
    axios.get('/api/user/current')
        .then(response => {
          this.user = response.data
        })
  },
  methods: {
    logout() {
      const logoutOptions = {redirectUri: "http://localhost:8080/"}
      this.keycloak.logout(logoutOptions).then((success) => {
        console.log("--> log: logout success ", success)
      }).catch((error) => {
        console.log("--> log: logout error ", error)
      });
    }
  }
}
</script>

<style>
#userpic {
  float: right;
}
#userpic img {
  width: 60px;
  height: 60px;
}
</style>
