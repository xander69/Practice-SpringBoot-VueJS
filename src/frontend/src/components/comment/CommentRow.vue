<template>
  <v-list-item>
    <v-list-item-avatar>
      <UserPicture :user="comment.author" :size="24"/>
    </v-list-item-avatar>
    <v-list-item-title class="ml-4">
      <v-list-item-title>
        {{ comment.text }}
      </v-list-item-title>
      <v-list-item-subtitle>
        <v-list-item-title class="text-caption">
          <router-link :to="`/user/${comment.author.id}`">
            {{ comment.author.name }}
          </router-link>
          <span class="ml-4">
            {{ comment.createDt }}
          </span>
        </v-list-item-title>
      </v-list-item-subtitle>
    </v-list-item-title>
  </v-list-item>
</template>

<script>
import UserPicture from '@/components/UserPicture'

export default {
  name: 'CommentRow',
  props: ['comment'],
  components: {UserPicture}
}
</script>

<style scoped>

</style>