<template>
  <v-list>
    <div class="text-h6">
      Comments
    </div>
    <template v-for="(item, index) in comments" :key="index">
      <v-divider v-if="index > 0"></v-divider>
      <CommentRow :comment="item"></CommentRow>
    </template>
    <CommentForm :messageId="messageId"></CommentForm>
  </v-list>
</template>

<script>
import CommentForm from '@/components/comment/CommentForm'
import CommentRow from '@/components/comment/CommentRow'

export default {
  name: 'CommentList',
  components: {CommentForm, CommentRow},
  props: ['comments', 'messageId']
}
</script>

<style scoped>

</style>