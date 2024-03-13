import {createSlice} from '@reduxjs/toolkit'
import { authenticateUser } from './UserThunk';
import { fetchAuthenticatedUser } from './UserThunk';


const initialState = {
    details: {
      data: {},
      isFetching: false,
    },
    authenticate: {
      data: null,
      isFetching: false,
    },
  };
  

const UserSlice = createSlice({
    name: "user",
    initialState,
    reducers: {
      setAuthenticate: (state,action) =>{
          state.authenticate.date = action.payload;
      }
    },

    extraReducers: (builder) => {
        //authenticateUser is a action creator
        //connect userThunk with slice, and store data back into store
        //store in state, why? used for authGuard
        builder.addCase(authenticateUser.fulfilled, (state, action) => {
            state.authenticate.data = action.payload["data"]["token"]
        })

        builder.addCase(authenticateUser.pending, (state, action) => {
          state.authenticate.isFetching = true
          console.log(action.payload)
      })
    
        builder.addCase(fetchAuthenticatedUser.fulfilled, (state, action) => {
          state.details.data = action.payload["data"]
          state.details.isFetching = false
      })

        builder.addCase(fetchAuthenticatedUser.pending, (state, action) => {
          state.details.isFetching = true
    })
    },
    
    

})

export const {setAuthenticate} = UserSlice.actions

export default UserSlice.reducer
