import {createStore} from 'vuex'
import axios from 'axios'
import messageApi from '@/api/messages'
import commentApi from '@/api/comments'

export default createStore({
    state() {
        return {
            messages: [],
            profile: {},
            currentPage: -1,
            totalPages: 0
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
            // check to avoid race condition problem
            if (!message.comments.find(item => item.id === comment.id)) {
                state.messages = [
                    ...state.messages.slice(0, updateIndex),
                    {
                        ...message,
                        comments: [
                            ...message.comments || [],
                            comment
                        ]
                    },
                    ...state.messages.slice(updateIndex + 1)
                ]
            }
        },
        addMessagePageMutation(state, messages) {
            const targetMessages = state.messages
                .concat(messages)
                // to avoid duplications
                .reduce((res, val) => {
                    res[val.id] = val
                    return res
                }, {})
            state.messages = Object.values(targetMessages)
        },
        updateCurrentPageMutation(state, currentPage) {
            state.currentPage = currentPage
        },
        updateTotalPagesMutation(state, totalPages) {
            state.totalPages = totalPages
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
            const response = await messageApi.update(message)
            const data = response.data
            commit('updateMessageMutation', data)
        },
        async removeMessage({commit}, message) {
            await messageApi.remove(message.id)
            commit('deleteMessageMutation', message)
        },
        async addComment({commit}, comment) {
            const response = await commentApi.add(comment)
            const data = response.data
            commit('addCommentMutation', data)
        },
        async loadPage({commit, state}) {
            const response = await messageApi.page(state.currentPage + 1)
            const data = response.data
            commit('addMessagePageMutation', data.messages)
            commit('updateCurrentPageMutation', Math.min(data.currentPage, data.totalPages - 1))
            commit('updateTotalPagesMutation', data.totalPages)
        }
    }
})