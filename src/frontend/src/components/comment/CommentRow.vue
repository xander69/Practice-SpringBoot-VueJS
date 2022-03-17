<template>
  <v-list-item>
    <v-list-item-avatar>
      <v-avatar :image="comment.author.userpic" size="24"></v-avatar>
    </v-list-item-avatar>
    <v-list-item-title class="ml-4">
      <v-list-item-title>
        {{ comment.text }}
      </v-list-item-title>
      <v-list-item-subtitle>
        <v-list-item-title class="text-caption">
          {{ comment.author.name }}, {{ comment.createDt }}
        </v-list-item-title>
      </v-list-item-subtitle>
    </v-list-item-title>
  </v-list-item>
</template>

<script>
export default {
  name: 'CommentRow',
  props: ['comment']
}
</script>

<style scoped>

</style>