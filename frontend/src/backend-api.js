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

  addQuestion(postData) {
    return AXIOS_JSON.post(`/questions/add`, postData);
  },

  getSubjects() {
    return AXIOS.get(`/subjects/map`);
  },

  getAllSubjects() {
    return AXIOS.get(`/subjects/all`);
  },

  getAllQuestion() {
    return AXIOS.get(`/questions/all`);
  },

  generateTest(url) {
    return AXIOS.post(url);
  }
}