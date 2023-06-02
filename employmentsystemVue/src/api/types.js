import request from '../utils/request';

export const getTypes = () => {
  return request({
    url: '/api/getTypes',
    method: 'post'
  });
};

export const findTypesList = (current,size,userVO) => {
  return request({
    url: '/api/findTypesPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  });
};

export const saveTypes= (user) => {
  return request({
    url: '/api/saveTypes',
    method: 'post',
    data: user
  });
};

export const updateTypes= (user) => {
  return request({
    url: '/api/updateTypes',
    method: 'post',
    data: user
  });
};

export const deleteTypes= (id) => {
  return request({
    url: '/api/deleteTypes',
    method: 'post',
    params: {
      id
    }
  });
};

