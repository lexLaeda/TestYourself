import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 1000
});

const AXIOS_JSON = axios.create({
  baseURL: `/api`,
  timeout: 1000,
  headers: {
    'Content-Type': 'application/json',
  }
});

export default {
  hello() {
    return AXIOS.get(`/hello`);
  },

  addSubject(postData) {
    return AXIOS_JSON.post(`/subjects/add`, postData);
  },

  getSubjects() {
    return AXIOS.get(`/subjects/map`);
  }
}