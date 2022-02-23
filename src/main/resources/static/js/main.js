const app = Vue.createApp({
    template: '<MessageList :messages="messages"/>',
    data() {
        return {
            messages: [
                {id: '1', text: 'First message'},
                {id: '2', text: 'Second message'},
                {id: '3', text: 'Third message'}
            ]
        }
    }
})

app.component('MessageList', {
    props: ['messages'],
    template:
        '<div>' +
        '<MessagesRow v-for="message in messages" :key="message.id" :message="message"/>' +
        '</div>'
})

app.component('MessagesRow', {
    props: ['message'],
    template: '<div>{{ message.id }}. <i>{{ message.text }}</i></div>'
});

app.mount('#app')