const app = Vue.createApp({
    template: '<MessageList/>'
})

app.component('MessageList', {
    template:
        '<div>' +
        '<MessagesRow v-for="message in messages" :key="message.id" :message="message"/>' +
        '</div>',
    created() {
        axios.get('/message').then(response =>
            response.data.forEach(message => this.messages.push(message))
        )
    },
    data() {
        return {
            messages: []
        }
    }
})

app.component('MessagesRow', {
    props: ['message'],
    template: '<div>{{ message.id }}. <i>{{ message.text }}</i></div>'
});

app.mount('#app')