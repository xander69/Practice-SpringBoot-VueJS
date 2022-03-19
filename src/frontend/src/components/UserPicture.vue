<template>
  <span>
    <v-avatar v-if="user.userpic"
              :image="user.userpic"
              :size="size"></v-avatar>
    <v-avatar v-else
              color="indigo"
              :size="size">
      <v-icon :size="size"
              dark>mdi-account</v-icon>
    </v-avatar>
  </span>
</template>

<script>
export default {
  name: 'UserPicture',
  props: ['user', 'size']
}
</script>

<style scoped>

</style>