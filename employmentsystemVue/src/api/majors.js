import request from '../utils/request';

export const getMajors = () => {
  return request({
    url: '/api/getMajors',
    method: 'post'
  });
};
export const findTongjiList = (current,size,userVO) => {
  return request({
    url: '/api/findMajorsPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  });
};
export const findMajorTongjiList = (current,size) => {
  return request({
    url: '/api/findMajorsPage3',
    method: 'post',
    params: {
      current,
      size
    }
  });
};
export const findMajorsList = (current,size,userVO) => {
  return request({
    url: '/api/findMajorsPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  });
};

export const saveMajors= (user) => {
  return request({
    url: '/api/saveMajors',
    method: 'post',
    data: user
  });
};

export const updateMajors= (user) => {
  return request({
    url: '/api/updateMajors',
    method: 'post',
    data: user
  });
};

export const deleteMajors= (id) => {
  return request({
    url: '/api/deleteMajors',
    method: 'post',
    params: {
      id
    }
  });
};

