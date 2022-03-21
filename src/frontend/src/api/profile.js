import axios from 'axios'

export default {
    getCurrent: async () => axios('/api/profile'),
    get: id => axios.get(`/api/profile/${id}`),
    changeSubscription: channelId => axios.post(`/api/profile/change-subscription/${channelId}`),
    subscriberList: channelId => axios.get(`/api/profile/get-subscribers/${channelId}`),
    changeSubscriptionStatus: subscriberId => axios.put(`/api/profile/change-status/${subscriberId}`)
}