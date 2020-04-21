import axios from 'axios'

window.axios = axios.create({
  baseURL: `/api`,
  timeout: 1000
});

window.axiosJson = axios.create({
  baseURL: `/api`,
  timeout: 1000,
  headers: {
    'Content-Type': 'application/json',
  }
});

export default {
  name: 'Api',

  hello() {
    return window.axios.get(`/hello`);
  },

  addSubject(postData) {
    return window.axiosJson.post(`/subjects/add`, postData);
  },

  addQuestion(postData) {
    return window.axiosJson.post(`/questions/add`, postData);
  },

  getSubjects() {
    return window.axios.get(`/subjects/map`);
  },

  getAllSubjects() {
    return window.axios.get(`/subjects/all`);
  },

  getAllQuestion() {
    return window.axios.get(`/questions/all`);
  },

  generateTest(url) {
    return window.axios.get(url);
  }
}