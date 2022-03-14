import {createApp} from 'vue'
import App from './App.vue'
import Keycloak from 'keycloak-js'
import vuetify from './plugins/vuetify'
import store from './plugins/store'
import {loadFonts} from './plugins/webfontloader'
import router from '@/plugins/router'

const keycloak = Keycloak({
    url: 'http://localhost:3100/',
    realm: 'Demo',
    clientId: 'spring-vuejs'
})

keycloak.init({onLoad: 'login-required'}).then((auth) => {
    if (!auth) {
        window.location.reload();
    } else {
        loadFonts()
        createApp(App, {keycloak: keycloak})
            .use(vuetify)
            .use(store)
            .use(router)
            .mount('#app')
    }
})
