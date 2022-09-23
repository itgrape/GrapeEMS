import { defineStore } from 'pinia'



export const useUserStore = defineStore('userStore', {
    state: () => {
        return {
            isPlainUser: false,
            isAdmin: false
        }
    }
})


