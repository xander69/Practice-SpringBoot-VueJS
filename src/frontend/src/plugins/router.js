import {createRouter, createWebHistory} from 'vue-router'
import MessageList from '@/components/MessageList'
import ProfilePage from '@/components/ProfilePage'
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