<template>
  <div v-if="type === 'href' && message.linkCover">
    <v-img :src="message.linkCover"
           class="white--text align-end"
           height="200px"
           cover>
    </v-img>
    <v-card-title>
      <a :href="message.link" target="_blank">
        {{ message.linkTitle || message.link }}
      </a>
    </v-card-title>
    <v-card-subtitle v-if="message.linkDescription" class="mb-3">
      {{ message.linkDescription }}
    </v-card-subtitle>
  </div>
  <div v-if="type === 'href' && !message.linkCover">
    <v-card-title>
      <a :href="message.link" target="_blank">
        {{ message.linkTitle || message.link }}
      </a>
    </v-card-title>
    <v-card-subtitle v-if="message.linkDescription">
      {{ message.linkDescription }}
    </v-card-subtitle>
  </div>
  <div v-if="type === 'image'">
    <v-card-text>
      <a :href="message.link" target="_blank">
        <v-img v-if="message.linkCover" :src="message.linkCover" aspect-ratio="4"></v-img>
      </a>
    </v-card-text>
  </div>
  <div v-if="type === 'youtube'">
    <v-card-text>
      <YoutubeMediaContent :src="message.link"></YoutubeMediaContent>
    </v-card-text>
  </div>
</template>

<script>
import YoutubeMediaContent from '@/components/media/YoutubeMediaContent'

export default {
  name: 'MediaContent',
  components: {YoutubeMediaContent},
  props: ['message'],
  data() {
    return {
      type: 'href'
    }
  },
  beforeMount() {
    if (this.message.link.indexOf('youtu') > -1) {
      this.type = 'youtube'
    } else if (this.message.link.match(/\.(jpeg|jpg|gif|png)$/) !== null) {
      this.type = 'image'
    } else {
      this.type = 'href'
    }
  }
}
</script>

<style scoped>

</style>