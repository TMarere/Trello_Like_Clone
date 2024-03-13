const storage = {};


//get data from local storage
storage.get = (key) => {
  const value = localStorage.getItem(key);
  try {
    return JSON.parse(value);
  } catch (e) {
    return value;
  }
};

//put data to local storage
storage.put = (key, value) => {
  //localStorage belongs to js engine
  localStorage.setItem(key, JSON.stringify(value));
};

//remove
storage.remove = (key) => {
  localStorage.removeItem(key);
};

export default storage;
