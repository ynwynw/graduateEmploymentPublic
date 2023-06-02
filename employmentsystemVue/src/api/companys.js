import request from '../utils/request';

export const findCompanysList = (current,size,userVO) => {
  return request({
    url: '/api/findCompanysPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  });
};
export const findCompanysList2 = (current,size) => {
  return request({
    url: '/api/findCompanysPage2',
    method: 'post',
    params: {
      current,
      size
    }
  });
};
export const findTongjiList = (current,size,userVO) => {
  return request({
    url: '/api/findCompanysPage2',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  });
};
export const passCompanys= (id) => {
  return request({
    url: '/api/passCompanys',
    method: 'post',
    params: {
      id
    }
  });
};

export const nopassCompanys= (id) => {
  return request({
    url: '/api/nopassCompanys',
    method: 'post',
    params: {
      id
    }
  });
};

export const findCompanysOneList = (phone) => {
  return request({
    url: '/api/findOneCompanysByUtel',
    method: 'post',
    params: {
      phone
    }
  });
};

export const findCompanysInfo = (current,size,phone) => {
  return request({
    url:'/api/findUserInfo',
    method: 'post',
    params:{
      current,
      size,
      phone
    }
  });
};

export const saveCompanys= (user,simg,imgtype,imgName) => {
  return request({
    url: '/api/addCompanys',
    method: 'post',
    params: {
      simg,
      imgtype,
      imgName
    },
    data: user
  });
};

export const updateCompanys= (user,simg,imgtype,imgName) => {
  return request({
    url: '/api/updateCompanys',
    method: 'post',
    params: {
      simg,
      imgtype,
      imgName
    },
    data: user
  });
};

export const updateOneCompanys= (user,simg,imgtype,imgName) => {
  return request({
    url: '/api/updateUser',
    method: 'post',
    params: {
      simg,
      imgtype,
      imgName
    },
    data: user
  });
};

export const loginCompanys= (phone_number,password,usertype) => {
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

export const findCompanysById= (id) => {
  return request({
    url: '/api/findUserById',
    method: 'post',
    params: {
      id
    }
  });
};

export const deleteCompanys= (id) => {
  return request({
    url: '/api/deleteCompanys',
    method: 'post',
    params: {
      id
    }
  });
};

export const selectCompanys= (id,phone) => {
  return request({
    url: '/api/selectUser',
    method: 'post',
    params: {
      id,
      phone
    }
  });
};

