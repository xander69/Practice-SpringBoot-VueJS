<template>
  <v-list-item two-line>
    <v-list-item-header>
      <v-list-item-title>
        <v-list-item-title>
          {{ comment.text }}
        </v-list-item-title>
      </v-list-item-title>
      <v-list-item-subtitle>
        <v-list-item-title class="text-caption">
          {{ comment.author.name }}, {{ comment.createDt }}
        </v-list-item-title>
      </v-list-item-subtitle>
    </v-list-item-header>
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