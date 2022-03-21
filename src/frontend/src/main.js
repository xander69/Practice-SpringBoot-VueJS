import {createApp} from 'vue'
import App from './App.vue'
import Keycloak from 'keycloak-js'
import vuetify from './plugins/vuetify'
import store from './plugins/store'
import {loadFonts} from './plugins/webfontloader'
import router from '@/plugins/router'
import * as Sentry from '@sentry/vue'
import {BrowserTracing} from '@sentry/tracing'

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

        const app = createApp(App, {keycloak: keycloak})
            .use(vuetify)
            .use(store)
            .use(router)

        Sentry.init({
            app,
            dsn: 'https://205e9559e8d54ea689fadc1dcf4da816@o349028.ingest.sentry.io/6269957',
            integrations: [new BrowserTracing({
                routingInstrumentation: Sentry.vueRouterInstrumentation(router),
                tracingOrigins: ['localhost']
            })]
        })

        app.mount('#app')
    }
})
