import request from '../utils/request';

export const getClasses = () => {
  return request({
    url: '/api/getClasses',
    method: 'post'
  });
};

export const findClassesList = (current,size,userVO) => {
  return request({
    url: '/api/findClassesPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  });
};

export const findClassesTongjiList = (current,size,userVO) => {
  return request({
    url: '/api/findClassesPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  });
};
export const findClassesTongjiList3 = (current,size) => {
  return request({
    url: '/api/findClassesPage3',
    method: 'post',
    params: {
      current,
      size
    }
  });
};

export const findClassesTongjiList2 = (current,size,phone) => {
  return request({
    url: '/api/findClassesPage2',
    method: 'post',
    params: {
      current,
      size,
      phone
    }
  });
};

export const saveClasses= (user) => {
  return request({
    url: '/api/saveClasses',
    method: 'post',
    data: user
  });
};

export const updateClasses= (user) => {
  return request({
    url: '/api/updateClasses',
    method: 'post',
    data: user
  });
};

export const deleteClasses= (id) => {
  return request({
    url: '/api/deleteClasses',
    method: 'post',
    params: {
      id
    }
  });
};

