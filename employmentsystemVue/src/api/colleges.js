import request from '../utils/request';

export const getColleges = () => {
  return request({
    url: '/api/getColleges',
    method: 'post'
  });
};
export const findTongjiList = (current,size,userVO) => {
  return request({
    url: '/api/findCollegesPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  });
};
export const findCollegesTongjiList = (current,size) => {
  return request({
    url: '/api/findCollegesPage3',
    method: 'post',
    params: {
      current,
      size
    }
  });
};
export const findCollegesList = (current,size,userVO) => {
  return request({
    url: '/api/findCollegesPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  });
};

export const saveColleges= (user) => {
  return request({
    url: '/api/addColleges',
    method: 'post',
    data: user
  });
};

export const updateColleges= (user) => {
  return request({
    url: '/api/updateColleges',
    method: 'post',
    data: user
  });
};

export const deleteColleges= (id) => {
  return request({
    url: '/api/deleteColleges',
    method: 'post',
    params: {
      id
    }
  });
};

