import SockJS from 'sockjs-client'
import {Stomp} from '@stomp/stompjs'

let stompClient = null
const handlers = []

export function connectWs() {
    stompClient = Stomp.over(() => new SockJS('/messenger-websocket'))
    stompClient.debug = () => {
    }
    stompClient.connect({}, () => {
        stompClient.subscribe('/topic/activity',
            message => {
                handlers.forEach(handler => handler(JSON.parse(message.body)))
            },
            err => {
                console.error(err)
            })
    })
}

export function addHandler(handler) {
    handlers.push(handler)
}

export function sendMessage(message) {
    // console.log('save message: ' + message)
    stompClient.send('/app/changeMessage', {}, JSON.stringify(message))
}

export function disconnectWs() {
    console.log('disconnect')
    if (stompClient != null) {
        stompClient.disconnect()
        console.log('disconnected')
    }
}