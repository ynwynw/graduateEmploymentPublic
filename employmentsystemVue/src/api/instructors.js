import request from '../utils/request';

export const findInstructorsList = (current,size,userVO) => {
  return request({
    url: '/api/findInstructorsPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  });
};
export const findInstructorsOneList = (phone) => {
  return request({
    url: '/api/findOneInstructorsByUtel',
    method: 'post',
    params: {
      phone
    }
  });
};
export const saveInstructors= (user,simg,imgtype,imgName) => {
  return request({
    url: '/api/addInstructorsUser',
    method: 'post',
    params: {
      simg,
      imgtype,
      imgName
    },
    data: user
  });
};

export const updateInstructors= (user,simg,imgtype,imgName) => {
  return request({
    url: '/api/updateInstructors',
    method: 'post',
    params: {
      simg,
      imgtype,
      imgName
    },
    data: user
  });
};

export const loginInstructors= (phone_number,password,usertype) => {
  return request({
     url: '/api/loginUser',
     method: 'post',
     params: {
       phone_number,
       password,
       usertype
    }
  });
};

export const deleteInstructors= (id) => {
  return request({
    url: '/api/deleteInstructors',
    method: 'post',
    params: {
      id
    }
  });
};

