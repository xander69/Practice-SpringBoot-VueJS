import {createApp} from 'vue'
import App from './App.vue'
import Keycloak from 'keycloak-js'

const keycloakOptions = {
    url: 'http://localhost:3100/',
    realm: 'Demo',
    clientId: 'spring-vuejs'
}

const keycloak = Keycloak(keycloakOptions)

keycloak.init({onLoad: 'login-required'}).then((auth) => {
    if (!auth) {
        window.location.reload();
    } else {
        createApp(App, {keycloak: keycloak}).mount('#app')
    }
})
