import {createStore} from 'vuex'
import axios from 'axios'
import messageApi from '@/api/messages'
import commentApi from '@/api/comments'

export default createStore({
    state() {
        return {
            messages: [],
            profile: {}
        }
    },
    getters: {
        profile: state => state.profile,
        sortedMessages: state => state.messages.sort((a, b) => -(a.id - b.id))
    },
    mutations: {
        setProfileMutation(state, profile) {
            state.profile = profile
        },
        setMessagesMutation(state, messages) {
            state.messages = messages
        },
        addMessageMutation(state, message) {
            state.messages = [
                ...state.messages,
                message
            ]
        },
        updateMessageMutation(state, message) {
            const updateIndex = state.messages.findIndex(item => item.id === message.id)
            state.messages = [
                ...state.messages.slice(0, updateIndex),
                message,
                ...state.messages.slice(updateIndex + 1)
            ]
        },
        deleteMessageMutation(state, message) {
            const deleteIndex = state.messages.findIndex(item => item.id === message.id)
            if (deleteIndex > -1) {
                state.messages = [
                    ...state.messages.slice(0, deleteIndex),
                    ...state.messages.slice(deleteIndex + 1)
                ]
            }
        },
        addCommentMutation(state, comment) {
            const updateIndex = state.messages.findIndex(item => item.id === comment.message.id)
            const message = state.messages[updateIndex]
            state.messages = [
                ...state.messages.slice(0, updateIndex),
                {
                    ...message,
                    comments: [
                        ...message.comments,
                        comment
                    ]
                },
                ...state.messages.slice(updateIndex + 1)
            ]
        }
    },
    actions: {
        async setProfile({commit}) {
            axios.get('/api/user/current').then(response => {
                commit('setProfileMutation', response.data)
            })
        },
        async setMessages({commit}) {
            messageApi.getAll().then(response => {
                commit('setMessagesMutation', response.data)
            })
        },
        async addMessage({commit, state}, message) {
            const response = await messageApi.add(message)
            const data = response.data
            // check to avoid race condition problem
            const index = state.messages.findIndex(item => item.id === data.id)
            if (index > -1) {
                commit('updateMessageMutation', data)
            } else {
                commit('addMessageMutation', data)
            }
        },
        async updateMessage({commit}, message) {
            await messageApi.update(message)
            commit('updateMessageMutation', message)
        },
        async removeMessage({commit}, message) {
            await messageApi.remove(message.id)
            commit('deleteMessageMutation', message)
        },
        async addComment({commit}, comment) {
            const response = await commentApi.add(comment)
            const data = {
                ...response.data,
                message: comment.message
            }
            commit('addCommentMutation', data)
        }
    }
})