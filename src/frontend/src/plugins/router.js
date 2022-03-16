import {createRouter, createWebHistory} from 'vue-router'
import MessageList from '@/components/message/MessageList'
import ProfilePage from '@/components/profile/ProfilePage'
import NotFound from '@/components/NotFound'

export default createRouter({
    history: createWebHistory(),
    routes: [{
        path: '/',
        name: 'Home',
        component: MessageList
    }, {
        path: '/profile',
        name: 'Profile',
        component: ProfilePage
    }, {
       path: '/404',
       name: 'Not found',
       component: NotFound
    }, {
        path: '/:catchAll(.*)',
        redirect: '/404'
    }]
})