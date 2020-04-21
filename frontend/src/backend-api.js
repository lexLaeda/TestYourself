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

  getSubject(id) {
    return window.axios.get(`/subjects/` + id);
  },

  getQuestionBySubject(id) {
    return window.axios.get(`/questions/subject?sub_id=` + id);
  },

  generateTest(id, number) {
    return window.axios.get(`/test/generate?id=`+ id + `&number=` + number);
  },

  getTest(id) {
    return window.axios.get(`/test/` + id);
  }
}