import './App.css';
import {BrowserRouter} from 'react-router-dom';
import {Provider} from 'react-redux';
import {store} from "./store";
import Router from "./routes";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import storage from './lib/localStorage';
import { setAuthenticate } from './store/slices/user/UserSlice';
import Navigation from './components/Navigation';

function App() {

  //this feature failed 
  const token = storage.get("token")
  if (token) {
    store.dispatch(setAuthenticate(token))
  }




  return (
   <>
      <Provider store={store}>
       
        <ToastContainer />
        <BrowserRouter>
          <Navigation />
          <Router />
        </BrowserRouter>
      </Provider>
   </>

  );
}

export default App;
