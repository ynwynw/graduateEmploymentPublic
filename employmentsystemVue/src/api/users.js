import request from '../utils/request';

export const findUserList = (current,size,userVO) => {
  return request({
    url: '/api/findUserPage',
    method: 'post',
    params: {
      current,
      size
    },
    data: userVO
  });
};
export const findUserList2 = (current,size) => {
  return request({
    url: '/api/findUserPage2',
    method: 'post',
    params: {
      current,
      size
    }
  });
};
export const findUserOneList = (phone) => {
  return request({
    url: '/api/findOneUserByUtel',
    method: 'post',
    params: {
      phone
    }
  });
};
export const findInstructorsUserList = (current,size,userVO,phone) => {
  return request({
    url: '/api/findInstructorsUserPage',
    method: 'post',
    params: {
      current,
      size,
      phone
    },
    data: userVO
  });
};

export const findUserInfo = (current,size,phone) => {
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

export const saveUser= (user,simg,imgtype,imgName) => {
  return request({
    url: '/api/addStuUser',
    method: 'post',
    params: {
      simg,
      imgtype,
      imgName
    },
    data: user
  });
};

export const updateUser= (user,simg,imgtype,imgName) => {
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
export const passUsers= (id) => {
  return request({
    url: '/api/passUsers',
    method: 'post',
    params: {
      id
    }
  });
};

export const nopassUsers= (id) => {
  return request({
    url: '/api/nopassUsers',
    method: 'post',
    params: {
      id
    }
  });
};
export const updateUser2= (user,tripartiteagreement,imgtype,imgName) => {
  return request({
    url: '/api/updateUser2',
    method: 'post',
    params: {
      tripartiteagreement,
      imgtype,
      imgName
    },
    data: user
  });
};

export const updateOneUser= (user,simg,imgtype,imgName) => {
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

export const loginUser= (phone_number,password,usertype) => {
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

export const findUserById= (id) => {
  return request({
    url: '/api/findUserById',
    method: 'post',
    params: {
      id
    }
  });
};

export const deleteUser= (id) => {
  return request({
    url: '/api/deleteUser',
    method: 'post',
    params: {
      id
    }
  });
};

export const selectUser= (id,phone) => {
  return request({
    url: '/api/selectUser',
    method: 'post',
    params: {
      id,
      phone
    }
  });
};

