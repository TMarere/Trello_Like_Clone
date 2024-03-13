import {configureStore} from '@reduxjs/toolkit'
import logger from 'redux-logger'

import UserReducer from './slices/user/UserSlice'


//?: check Redux Ess2 about root store
export const store = configureStore({
    reducer: {
        user: UserReducer
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(logger),
    devTools: process.env.NODE_ENV !== 'production',
})

