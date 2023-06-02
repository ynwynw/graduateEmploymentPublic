import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8081',
  timeout: 30000
});

export default instance;

