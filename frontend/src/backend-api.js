import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `http://localhost:8098/api`,
  timeout: 1000
});


export default {
  hello() {
    return AXIOS.get(`/questions/all`);
  },

  addSubject(postData) {
    return AXIOS.post(`/subjects/add`, postData, {
      headers: {
        'Content-Type': 'application/json',
      }
    });
  },
}