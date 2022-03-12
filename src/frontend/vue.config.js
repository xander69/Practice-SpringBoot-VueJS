const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  pluginOptions: {
    vuetify: {
      // https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
    }
  }
})
module.exports = {
  devServer: {
    port: 8080,
    proxy: {
      '^/api/': {
        target: 'http://localhost:8282'
      },
      '^/messenger-websocket/': {
        target: 'http://localhost:8282'
      }
    }
  },
  pluginOptions: {
    vuetify: {}
  }
}
