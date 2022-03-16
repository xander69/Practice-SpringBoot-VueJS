import axios from 'axios'

export default {
    add: comment => axios.post('api/comments', comment)
}