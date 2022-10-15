import { defineStore } from 'pinia'



export const useAdminStore = defineStore('adminStore', {
    state: () => {
        return {
            depts: []
        }
    }
})


