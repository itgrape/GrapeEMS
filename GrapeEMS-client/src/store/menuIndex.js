import { defineStore } from 'pinia'



export const useStore = defineStore('menuIndex', {
    state: () => {
        return {
            menuIndex: 'index',
        }
    }
})


