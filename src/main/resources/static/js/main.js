function getMessageIndex(list, id) {
    for (let i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

const app = Vue.createApp({
    template:
        '<div>' +
        '<h1>Messenger</h1>' +
        '<div v-if="!profile">' +
        'You need to sign in with <a href="/login">Keycloak</a>' +
        '</div>' +
        '<div v-else>' +
        '<div>' +
        '<img :src="profile.userpic" alt="userpic" width="64" height="64"/>' +
        '{{profile.name}} <a href="/logout">Exit</a>' +
        '</div>' +
        '<br/>' +
        '<MessageList :messages="messages"/>' +
        '</div>' +
        '</div>',
    data() {
        return {
            messages: frontendData.messages,
            profile: frontendData.profile
        }
    },
})

app.component('MessageList', {
    props: ['messages'],
    template:
        '<div>' +
        '<MessageForm :messages="messages" :messageToEdit="messageToEdit"/>' +
        '<br/>' +
        '<MessagesRow v-for="message in messages" :key="message.id" :message="message"' +
        ' :messages="messages" :editMethod="editMethod"/>' +
        '</div>',
    data() {
        return {
            messageToEdit: null
        }
    },
    methods: {
        editMethod: function (message) {
            this.messageToEdit = message;
        }
    }
})

app.component('MessagesRow', {
    props: ['message', 'messages', 'editMethod'],
    template: '<div>' +
        '{{ message.id }}. <i>{{ message.text }}</i>' +
        '<span style="position: absolute; right: 0;">' +
        '<input type="button" value="Edit" @click="edit"/> ' +
        '<input type="button" value="X" @click="del"/> ' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.message);
        },
        del: function () {
            axios.delete(`/message/${this.message.id}`).then(response => {
                if (response.status === 200) {
                    this.messages.splice(this.messages.indexOf(this.message), 1);
                }
            })
        }
    }
});

app.component('MessageForm', {
    props: ['messages', 'messageToEdit'],
    data: function () {
        return {
            text: '',
            id: ''
        }
    },
    watch: {
        messageToEdit: function (newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Write something" v-model="text"/> ' +
        '<input type="button" value="Save" @click="save"/> ' +
        '</div>',
    methods: {
        save: function () {
            const message = {text: this.text};
            if (this.id) {
                axios.put(`/message/${this.id}`, message).then(response => {
                    const data = response.data;
                    const index = getMessageIndex(this.messages, data.id);
                    this.messages.splice(index, 1, data);
                    this.text = '';
                    this.id = '';
                })
            } else {
                axios.post('/message', message).then(response => {
                    this.messages.push(response.data);
                    this.text = '';
                })
            }
        }
    }
});

app.mount('#app')