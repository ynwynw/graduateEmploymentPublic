import request from '../utils/request';

export const findAdminsPage= () => {
  return request({
    url: "/api/findAdminsPage",
    method: 'get'
  });
};

export const updateAdmins= (admins) => {
  return request({
    url: '/api/updateAdmins',
    method: 'post',
    data: admins
  });
};
