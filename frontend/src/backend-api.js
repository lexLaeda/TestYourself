import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 1000
});


export default {
  hello() {
    return AXIOS.get(`/hello`);
  },

  addSubject(postData) {
    return AXIOS.post(`/subjects/add`, postData, {
      headers: {
        'Content-Type': 'application/json',
      }
    });
  },
}