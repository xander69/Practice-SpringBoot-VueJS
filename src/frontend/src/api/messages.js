import axios from 'axios'

export default {
    getAll: async () => axios('/api/message'),
    add: message => axios.post('api/message', message),
    update: message => axios.put('api/message/' + message.id, message),
    remove: id => axios.delete('api/message/' + id),
    page: page => axios.get('api/message', {params: {page: page}})
}