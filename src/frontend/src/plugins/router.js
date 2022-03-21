import {createRouter, createWebHistory} from 'vue-router'
import MessageList from '@/components/message/MessageList'
import ProfilePage from '@/components/profile/ProfilePage'
import NotFound from '@/components/NotFound'
import SubscriptionPage from '@/components/profile/SubscriptionPage'

export default createRouter({
    history: createWebHistory(),
    routes: [{
        path: '/',
        name: 'Home',
        component: MessageList
    }, {
        path: '/user/:id?',
        name: 'Profile',
        component: ProfilePage
    }, {
        path: '/subscriptions/:id',
        name: 'Subscriptions',
        component: SubscriptionPage
    }, {
        path: '/404',
        name: 'Not found',
        component: NotFound
    }, {
        path: '/:catchAll(.*)',
        redirect: '/404'
    }]
})